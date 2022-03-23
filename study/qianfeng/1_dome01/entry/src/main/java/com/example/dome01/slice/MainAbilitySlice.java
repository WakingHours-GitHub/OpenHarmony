package com.example.dome01.slice;

import com.example.dome01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 在当前slice中渲染视图组件有两种方式
        // 1. 基于Java代码的渲染
        // 2. 基于xml文件渲染(default) -> setUIContent(xml文件引用)

        // 1. 获取id=btn1的按钮插件
        Button btn1 = (Button) this.findComponentById(ResourceTable.Id_btn1);

        // 2. 设置按钮事件监听
        // a. 创建事件监听器
        Component.ClickedListener listener = new Component.ClickedListener() {
            @Override // 直接重载
            public void onClick(Component component) {
                // component就是表示监听的组件
                if (component == btn1){
                    System.out.println("---------->>>AAA");
                }
            }
        };
        // b. 设置组件的事件监听
        btn1.setClickedListener(listener);


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
