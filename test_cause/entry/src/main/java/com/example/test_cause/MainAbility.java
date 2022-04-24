package com.example.test_cause;

import com.example.test_cause.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.IntentAbility;
import ohos.aafwk.content.Intent;
import ohos.ace.ability.AceInternalAbility;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());


//        internalAbility
        internalAb
    }
}
