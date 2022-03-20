package com.example.study;

import com.example.study.slice.MainAbility4_double_clickSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility4_double_click extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbility4_double_clickSlice.class.getName());
    }
}
