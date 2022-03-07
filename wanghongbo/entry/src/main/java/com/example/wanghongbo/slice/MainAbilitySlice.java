package com.example.wanghongbo.slice;

import com.example.wanghongbo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.element.Element;
import ohos.agp.render.ColorMatrix;
import ohos.agp.text.richstyles.BackgroundColorRichStyle;
import ohos.agp.utils.Color;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);


        Button but1 = (Button) findComponentById(ResourceTable.Id_but_1);
        Text text2 = (Text) findComponentById(ResourceTable.Id_text_2);
        but1.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        but1.setText("我被点了");
                        text2.setTextColor(Color.GREEN);
                        text2.setText("波波爱曲英");
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
