package com.example.huc_project;

import com.example.huc_project.slice.MainAbility2_loginSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility2_login extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbility2_loginSlice.class.getName());
    }
}
