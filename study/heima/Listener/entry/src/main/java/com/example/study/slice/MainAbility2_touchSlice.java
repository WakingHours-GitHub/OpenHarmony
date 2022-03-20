package com.example.study.slice;

import com.example.study.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.RadioButton;
import ohos.agp.components.Text;
import ohos.multimodalinput.event.MmiPoint;
import ohos.multimodalinput.event.TouchEvent;
import ohos.utils.PlainIntArray;

/*
    我们在这里开始编写我们的滑动事件

 */
public class MainAbility2_touchSlice extends AbilitySlice {
    int count = 0;
    // 按下时, 记录坐标
    float startX = 0;
    float startY = 0;
    // 松开时, 记录坐标

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_ability2_touch);


        // 开始编写滑动事件:
        // 1. 先找到整个的布局对象
        DirectionalLayout dl1 = (DirectionalLayout) findComponentById(ResourceTable.Id_dl1);
        Text text1 = (Text) findComponentById(ResourceTable.Id_text1);
        Text text2 = (Text) findComponentById(ResourceTable.Id_text2);
        Text text3 = (Text) findComponentById(ResourceTable.Id_text3);

        // 2. 给整个布局添加滑动事件
        // 当我们在整个布局上滑动的时候, 就会调用本类中的onTouchEvent方法
        // 并且, 在按下, 移动, 松开的过程中, 代码会不断的去调用本类中的onTouchEvent方法
        // 设置一个count, 不断自增, 来查看运行亲铁矿
        dl1.setTouchEventListener(
                new Component.TouchEventListener() { // 实现接口
                    @Override
                    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
                        /*
                        参数1: component: 表示滑动的那个组件, 实际上DL布局, 也是一种组件
                            实际上就是表示DirectionalLayout, 整个布局
                        参数2: touchEvent动作对象(按下, 滑动, 抬起)
                         */

                        // 获取当前手指对于屏幕进行的操作:(按下, 滑动, 抬起)
                        int action = touchEvent.getAction();
                        // 1: 按下
                        // 2: 松开
                        // 3: 移动
                        // 判断操作:
                        if (action == TouchEvent.PRIMARY_POINT_DOWN){
                            // 表示按下时, 需要写的代码(执行的操作)
                            count++;
                            text1.setText("按下"+count);
                            MmiPoint point = touchEvent.getPointerPosition(0);// 获取手指的索引
                            // 获取(x, y)坐标
                            startX = point.getX();
                            startY = point.getY();
                            text2.setText("("+startX+","+startY+")"); // 展示坐标




                        }else if (action == TouchEvent.POINT_MOVE){
                            // 表示滑动时候, 需要执行的操作
                            count++;
                            text1.setText("移动"+count);
                            MmiPoint point = touchEvent.getPointerPosition(0);// 获取手指的索引
                            // 获取(x, y)坐标
                            float x = point.getX();
                            float y = point.getY();
                            text2.setText("("+x+","+y+")"); // 展示坐标

                        }else if (action == TouchEvent.PRIMARY_POINT_UP){
                            // 表示松开时, 需要写的代码
                            count++;
                            text1.setText("松开"+count);
                            MmiPoint point = touchEvent.getPointerPosition(0);
                            float endX = point.getX();
                            float endY = point.getY();
                            text2.setText("("+endX+","+endY+")"); // 展示坐标

                            // 判断位置:
                            // 使用按下时的位置和松开时的位置进行判断, 并且设置一定的容忍度.
                            if (endX > startX && Math.abs(endY - startY) < 100) {
                                text3.setText("右滑");
                            }else if (endX < startX && Math.abs(endY - startY) < 100) {
                                text3.setText("左滑");
                            }else if (endY > startY && Math.abs(endX - startX) < 100) {
                                text3.setText("下滑");
                            }
                            else if (endY < startY && Math.abs(endX - startX) < 100) {
                                text3.setText("上滑");
                            }else{
                                text3.setText("无效滑动");
                            }



                        }




                        return true;
                        // 返回值是什么意思
                        // true表示所有的动作都会触发当前方法,并执行代码
                        // false, 表示只有第一个动作会触发当前方法, 并且执行代码
                        // 按下 -> 移动 -> 松开, 如果为false则只有按下能够触发事件
                    }
                }
        );

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
