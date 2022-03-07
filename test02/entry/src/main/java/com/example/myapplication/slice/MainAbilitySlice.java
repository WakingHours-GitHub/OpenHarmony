package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.agp.utils.TextTool;


public class MainAbilitySlice extends AbilitySlice {
    /*
    flag为false时, 是没有被点击
    flag为
     */
    boolean flag = false;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        DirectionalLayout dl =findComponentById(ResourceTable.Id_bl);
        dl.setAlignment(Component.VERTICAL);


        Button but = new Button(this);
        but.setText("单击切换");
        but.setTextSize(100);
        but.setTextColor(Color.CYAN);

        Text text = new Text(this);
        text.setText("初始化");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextSize(100);
        text.setTextColor(Color.BLUE);

        but.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        if(flag){
                            text.setText("按钮取消");
                            flag = false;
                        }else{
                            text.setText("按钮被点击了");
                            flag = true;
                        }

                    }
                }
        );

        // 添加组件
        dl.addComponent(text);
        dl.addComponent(but);
        dl.setAlignment(TableLayout.Alignment.ALIGNMENT_FRONT);

        super.setUIContent(dl);

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
