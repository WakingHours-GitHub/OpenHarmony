/*
 * Copyright (c) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License,Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.codelab.point;

import ohos.agp.components.Component;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.Point;
import ohos.app.Context;
import ohos.multimodalinput.event.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * DrawPoint
 *
 * @since 2021-01-11
 */
public class DrawPoint extends Component implements Component.DrawTask { // 本质上还是一个Component也就是一个组件

    private static final int STROKE_WIDTH = 15;

    private float[] pointXs;

    private float[] pointYs;

    private boolean[] isLastPoints;

    private Paint paint; // 画笔

    private OnDrawCallBack callBack; // 回调函数的变量.

    private boolean isLocal;

    private List<MyPoint> localPoints = new ArrayList<>(); // 点的集合
    // 定义一个回调接口, 获取屏幕当中移动的坐标
    private List<MyPoint> remotePoints = new ArrayList<>();

    /**
     * DrawPoint
     *
     * @param context context
     * @param isLocal is local or not
     */
    public DrawPoint(Context context, boolean isLocal) {
        super(context);

        this.isLocal = isLocal;
        // 设定画笔
        // 确定画图任务
        // 通过屏幕监听器, 来判断是否进行画图操作.
        init();
    }

    /**
     * setDrawParams
     *
     * @param isLastPoint isLastPoint
     * @param pointsX pointsX
     * @param pointsY pointsY
     */
    public void setDrawParams(boolean[] isLastPoint, float[] pointsX, float[] pointsY) {
        pointXs = pointsX;
        pointYs = pointsY;
        isLastPoints = isLastPoint;
        invalidate();
    }

    /**
     * setOnDrawBack
     *
     * @param drawCallBack setOnDrawBack
     */
    public void setOnDrawBack(OnDrawCallBack drawCallBack) {
        callBack = drawCallBack;
    }

    private void init() {
        // 设定画笔
        paint = new Paint(); // 初始化画笔,
        paint.setAntiAlias(true);
        // 设置样式:
        paint.setStyle(Paint.Style.STROKE_STYLE);
        paint.setStrokeWidth(STROKE_WIDTH);
        // 添加绘画任务
        addDrawTask(this); // 在OnDraw中确定绘画的图形

        // 屏幕监听器, 监听画笔, 进行绘画
        setTouchEventListener((component, touchEvent) -> {
            // touchEvent, 就是触发事件
            // 获取点的坐标:
            int crtX = (int) touchEvent.getPointerPosition(touchEvent.getIndex()).getX();
            int crtY = (int) touchEvent.getPointerPosition(touchEvent.getIndex()).getY();
            MyPoint point = new MyPoint(crtX, crtY); // 使用x坐标和y坐标, 进行生成点,

            // 接下来通过姿势来判断点的开始和结束:
            if (touchEvent.getAction() == TouchEvent.PRIMARY_POINT_UP) { // 抬起来了, 则说明是结束点
                point.setLastPoint(true); // 设置结束点, 为true
                localPoints.add(point); // 加进来本地点,
                callBack.callBack(localPoints); // 然后获取
                System.out.println("up:" + crtY);
            }

            if (touchEvent.getAction() == TouchEvent.PRIMARY_POINT_DOWN) { // 开始点,
                localPoints.add(point); // 也需要记录下来
                System.out.println("down:" + crtY);
            }

            if (touchEvent.getAction() == TouchEvent.POINT_MOVE) { // 移动的时候
                localPoints.add(point);
                System.out.println("move:" + crtY);
            }

            invalidate(); // 更新主线程
            return true; // 返回状态.
        });
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        // 主要实现绘图操作.
        // 需要有点的一个坐标, 和一个容器/
        remotePoints.clear();
        if (pointXs != null && pointXs.length > 1) {
            for (int i = 0; i < pointXs.length; i++) {
                float finalX = pointXs[i];
                float finalY = pointYs[i];
                boolean isLast = isLastPoints[i];
                remotePoints.add(new MyPoint(finalX, finalY, isLast));
            }

            // draw remote points
            drawPoints(canvas, remotePoints, false);
        }

        // draw local points
        drawPoints(canvas, localPoints, true);
    }

    private void drawPoints(Canvas canvas, List<MyPoint> points, boolean isStudent) {
        if (points.size() < 1) {
            return;
        }
        if (isStudent) {
            paint.setColor(isLocal ? Color.BLACK : Color.RED);
        } else {
            paint.setColor(isLocal ? Color.RED : Color.BLACK);
        }
        Point first = null;
        Point last = null;
        for (MyPoint myPoint : points) { // 遍历每个point, 后一个点的开始, 是前一个点的结束, 那么此时就应该画线
            float finalX = myPoint.getPositionX();
            float finalY = myPoint.getPositionY();
            Point finalPoint = new Point(finalX, finalY);
            if (myPoint.isLastPoint()) { // 如果不是最后一个点
                first = null;
                last = null;
                continue;
            }
            if (first == null) { // 初始点
                first = finalPoint;
//                continue;
            } else {
                if (last != null) { // 如果是结束点
                    first = last;
                }
                last = finalPoint; // 最后点是最终的点.
                canvas.drawLine(first, last, paint); // 然后画出来线
            }
        }
    }

    /**
     * OnDrawCallBack
     * 定义一个接口, 以此来不断监听屏幕的信息
     * @since 2021-01-11
     */
    public interface OnDrawCallBack {
        /**
         * callBack
         *
         * @param points List
         */
        // 定义一个回调函数, 用来去屏幕监听器取出屏幕上点的集合.
        void callBack(List<MyPoint> points);
        // 所谓回调函数, 就是屏幕监听器自己自己定义自己调用, 然后使用其结果的返回值.

    }
}
