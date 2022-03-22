package com.example.dome01.slice;

import ohos.aafwk.content.Intent;

public class MainAbilitySlice2 extends MainAbilitySlice{
    @Override // 重载我们的onStart方法
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 定义slice试图组件 -> 有两种方式: java, xml
        // setUIContent(int) -> 加载应用的布局文件(xml) 来作为当前slice的视图.

    }
}
