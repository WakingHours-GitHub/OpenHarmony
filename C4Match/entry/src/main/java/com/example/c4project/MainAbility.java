package com.example.c4project;

import com.example.c4project.slice.MathDrawRemSlice;
import com.example.c4project.slice.MathGameAbilitySlice;
import com.example.c4project.slice.PictureGameAbilitySlice;
import com.example.c4project.utils.*;
import com.example.c4project.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

        // 添加路由, 也就是页面路由
//        addActionRoute(CommonData.PICTURE_PAGE, PictureGameAbilitySlice.class.getName());
//        addActionRoute(CommonData.MATH_PAGE, MathGameAbilitySlice.class.getName());
//        addActionRoute(CommonData.DRAW_PAGE, MathDrawRemSlice.class.getName());
    }
}
