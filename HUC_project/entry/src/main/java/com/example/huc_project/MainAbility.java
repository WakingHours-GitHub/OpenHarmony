package com.example.huc_project;

import com.example.huc_project.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent); // 初始化
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
