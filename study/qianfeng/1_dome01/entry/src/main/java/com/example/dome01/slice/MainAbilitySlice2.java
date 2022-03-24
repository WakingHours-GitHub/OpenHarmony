package com.example.dome01.slice;

import com.example.dome01.ResourceTable;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class MainAbilitySlice2 extends MainAbilitySlice{


    @Override // 重载我们的onStart方法
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 定义slice试图组件 -> 有两种方式: java, xml
        // setUIContent(int) -> 加载应用的布局文件(xml) 来作为当前slice的视图.
        // setUIContent(compoent) -> 加载java代码, 作为当前slice的视图

        // 加载我们的写好的XML文件
        setUIContent(ResourceTable.Layout_ability_main_slice2);

        // 在跳转后的slice的onStart方法中, 从Intent对象中获取数据
        // onStart的intent方法中, 没有传值时, intent为null, 所以为了保证代码的健壮性
        if (intent != null){
            IntentParams params = intent.getParams(); // 返回
            String productId = params.getParam("productId").toString();

            Text text1 = (Text) findComponentById(ResourceTable.Id_text1);
            text1.setText(productId);
        }


        /*
        // ComponentContainer
        DirectionalLayout directionalLayout = new DirectionalLayout(this);
        directionalLayout.setAlignment(Component.HORIZONTAL);
        // Component
        Text text = new Text(this);
        text.setText("Hello Java");
        text.setHeight(50);
        text.setTextColor(Color.BLACK);
        text.setTextSize(100);
        // 将组建添加到组件容器中
        directionalLayout.addComponent(text);
        // 将组建容器渲染到slice
        setUIContent(directionalLayout);*/
    }




}
