package com.example.study;

import com.example.study.slice.MainAbility2_touchSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility2_touch extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbility2_touchSlice.class.getName());
    }
}
