package com.example.study.slice;

import com.example.study.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

/*
    案例1: 多按钮案例
 */
public class MainAbility3_more_button_caseSlice extends AbilitySlice implements Component.ClickedListener {
    // 组件
    Text text1 = null;
    Button login = null;
    Button register = null;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_ability3_more_button_case);

        // 开始编写我们的案例:
        // 找到所需组件:
        text1 = findComponentById(ResourceTable.Id_text1);
        login = findComponentById(ResourceTable.Id_login);
        register = findComponentById(ResourceTable.Id_register);


        // 对什么组件, 进行什么操作
        // 设置这两个点击事件均为本类事件
        // 那么如何区分呢, 就需要再onClick中,对component进行判断了
        login.setClickedListener(this);
        register.setClickedListener(this);


    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        if (component == login){
            // 执行点击登录时候的操作:
            text1.setText("点击登录按钮了");
        }else if (component == register){
            // 执行点击注册时候的操作
            text1.setText("点击注册按钮了");
        }
    }
}
