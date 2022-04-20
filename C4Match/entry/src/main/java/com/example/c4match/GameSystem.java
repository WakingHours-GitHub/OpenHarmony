package com.example.c4match;

import com.example.c4match.slice.GameSystemSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class GameSystem extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(GameSystemSlice.class.getName());
    }
}
