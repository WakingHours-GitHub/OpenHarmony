package com.example.study.slice;

import com.example.study.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.miscservices.download.IDownloadListener;

/*

小作业：
    能否按照抖音的业务去写代码呢？
    双击屏幕之后，点赞。再次双击屏幕之后，不会取消点赞
    单击红心的操作，单击红心也可以点赞，再次单击红心就取消点赞。
 */
public class MainAbility4_homework_double_clickSlice extends AbilitySlice {
    /*
    标志是否点赞:
    true则为点赞, false为不点赞
     */
    boolean flag = true;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 直接使用double_click的那个xml文件即可
        super.setUIContent(ResourceTable.Layout_ability_main_ability4_double_click);


        // 开始编写我们的业务逻辑:
        // 寻找组件:
        DirectionalLayout dl = (DirectionalLayout)                                                      findComponentById(ResourceTable.Id_dl);
        Image img  = (Image) findComponentById(ResourceTable.Id_img);

        // 绑定操作:
        // 为dl绑定双击事件, 为img绑定单击事件
        // 设置标志位:
        dl.setDoubleClickedListener(
                new Component.DoubleClickedListener() {
                    @Override
                    public void onDoubleClick(Component component) {
                        if (flag){ //

                        }
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
