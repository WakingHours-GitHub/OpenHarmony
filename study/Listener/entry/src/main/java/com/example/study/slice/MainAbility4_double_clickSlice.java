package com.example.study.slice;

import com.example.study.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;

/*
双击点赞案例
双击屏幕页面, 就可以实现点赞的效果.

 */

public class MainAbility4_double_clickSlice extends AbilitySlice implements Component.DoubleClickedListener {
    Image img = null;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_ability4_double_click);


        /*
        开始编写双击点赞, 和双击取消的案例:
         */
        // 找到组件:
        // 1. 寻找图片土建
         img = (Image) findComponentById(ResourceTable.Id_img);
        // 寻找整个页面的内容
        DirectionalLayout dl = (DirectionalLayout) findComponentById(ResourceTable.Id_dl);


        // 2. 给全局添加双击事件:
        dl.setDoubleClickedListener(this);

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    // 如何双击操作要控制变白, 和变红呢, 我们需要设置一个标志位.

    //如果标记为false，表示没有点赞，此时把白色变成红色
    //如果表示为true，表示已经点赞，再次双击之后，会把红色变回白色
    boolean flag = false;
    @Override
    public void onDoubleClick(Component component) {
        // 修改图片的红心就可以了
        if (flag){ // true, 表示已经点赞, 再次点击应该取消点赞
            img.setImageAndDecodeBounds(ResourceTable.Media_white);
            flag = false;  // 设置为白色, 此时应该为false

        }else{ // 表示为false, 此时为没有点赞, 所以应该点赞
            img.setImageAndDecodeBounds(ResourceTable.Media_red);
            flag = true;
        }

    }


}
