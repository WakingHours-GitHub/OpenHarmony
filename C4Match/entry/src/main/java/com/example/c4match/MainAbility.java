package com.example.c4match;

import com.example.c4match.slice.MainAbilitySlice;
import com.example.c4match.utils.CommonData;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

//        addActionRoute(CommonData.MATH_PAGE, StudySystem.class.getName());
        addActionRoute(CommonData.PICTURE_PAGE, GameSystem.class.getName());
//        addActionRoute(CommonData.DRAW_PAGE, MathDrawRemSlice.class.getName());
    }
}
