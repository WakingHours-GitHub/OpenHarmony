package com.example.c4project.slice;


import com.example.c4project.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.OnClickListener;
import ohos.aafwk.content.Intent;


public class select_mode_slice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_select_mode);

    }


}
