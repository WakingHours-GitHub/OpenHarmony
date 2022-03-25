package com.example.demo03_serviceability.slice;

import com.example.demo03_serviceability.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

public class PlayAbilitySlice extends AbilitySlice {
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        this.setUIContent(ResourceTable.Layout_ability_play);

        Text text = (Text) findComponentById(ResourceTable.Id_musicNameText);
        // 这里我们要获取我们歌曲的名字
        if (intent != null){
            String music = (String) intent.getParams().getParam("musicName");
            text.setText(music);

        }
    }
}
