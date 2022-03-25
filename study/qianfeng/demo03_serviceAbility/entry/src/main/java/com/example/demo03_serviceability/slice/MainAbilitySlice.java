package com.example.demo03_serviceability.slice;

import com.example.demo03_serviceability.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);


        // 点击歌曲名称, 跳转到对应的PlayAbilitySlice去执行
        Text text1 = (Text) findComponentById(ResourceTable.Id_musicText1);
        Text text2 = (Text) findComponentById(ResourceTable.Id_musicText2);
        Text text3 = (Text) findComponentById(ResourceTable.Id_musicText3);

        // 绑定点击事件:
        text1.setClickedListener(component -> pageJump(component));
        text2.setClickedListener(this::pageJump);
        text3.setClickedListener(this::pageJump);





    }

    private void pageJump(Component component) {
        // 这里不用对每个组件进行判断, 我们只需要传值, 就可以
        Text text = (Text) component;
        String musicName = text.getText();
        Intent intent = new Intent();
        intent.setParam("musicName", musicName);
        this.present(new PlayAbilitySlice(), intent);

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
