package com.example.huc_project.slice;

import com.example.huc_project.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        Button login = (Button) findComponentById(ResourceTable.Id_but_login);
        Button register = (Button) findComponentById(ResourceTable.Id_but_register);
        Text status = (Text) findComponentById(ResourceTable.Id_text_status);

        login.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        status.setText("请输入用户名");
                    }
                }
        );
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
