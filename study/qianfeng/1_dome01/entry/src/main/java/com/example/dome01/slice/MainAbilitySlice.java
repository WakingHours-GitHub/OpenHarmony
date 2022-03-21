package com.example.dome01.slice;

import com.example.dome01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 在当前slice中渲染视图组件有两种方式
        // 1. 基于Java代码的渲染
        // 2. 基于xml文件渲染(default) -> setUIContent(xml文件引用)
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
