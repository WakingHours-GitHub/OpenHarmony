package com.example.study;

import com.example.study.slice.MainAbility3_more_button_caseSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility3_more_button_case extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbility3_more_button_caseSlice.class.getName());
    }
}
