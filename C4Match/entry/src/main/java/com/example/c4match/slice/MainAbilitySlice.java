package com.example.c4match.slice;

import com.example.c4match.Log;
import com.example.c4match.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.bundle.IBundleManager;

import static ohos.security.SystemPermission.DISTRIBUTED_DATASYNC;

public class MainAbilitySlice extends AbilitySlice {
    private static final int PERMISSION_CODE = 10000000; // 授权许可code
    Button study_btu;
    Button game_btu;
    Button introduction_btu;
    Button expect_btu;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main); // 加载页面
        applyPermission(); // 申请权限
        initFunction(); // 初始化功能

    }

    private void initFunction(){
        study_btu = (Button) this.findComponentById(ResourceTable.Id_study_btu);
        game_btu = (Button) findComponentById(ResourceTable.Id_game_btu);
        introduction_btu = (Button) findComponentById(ResourceTable.Id_introduction_btu);
        expect_btu = (Button) findComponentById(ResourceTable.Id_expect_btu);

        study_btu.setClickedListener(this::clickButton);
        game_btu.setClickedListener(this::clickButton);
        introduction_btu.setClickedListener(this::clickButton);
        expect_btu.setClickedListener(this::clickButton);
    }

    /**
     * 申请授权,
     */
    private void applyPermission(){

        if (verifySelfPermission(DISTRIBUTED_DATASYNC) != IBundleManager.PERMISSION_GRANTED) {
            if (canRequestPermission(DISTRIBUTED_DATASYNC)) {
                requestPermissionsFromUser(new String[] {DISTRIBUTED_DATASYNC}, PERMISSION_CODE);
                Log.info("获取权限成功");
            }
        }
    }

    private void clickButton(Component component){

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
