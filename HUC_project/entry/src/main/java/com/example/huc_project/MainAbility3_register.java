package com.example.huc_project;

import com.example.huc_project.slice.MainAbility3_registerSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility3_register extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbility3_registerSlice.class.getName());
    }
}
