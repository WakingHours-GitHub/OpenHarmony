package com.example.c4project;

import com.example.c4project.slice.explainSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class explain extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(explainSlice.class.getName());
    }
}
