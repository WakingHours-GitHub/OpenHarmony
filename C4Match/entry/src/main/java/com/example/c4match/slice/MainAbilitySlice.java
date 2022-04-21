package com.example.c4match.slice;

import com.example.c4match.GameSystem;
import com.example.c4match.MainAbility;
import com.example.c4match.utils.CommonData;
import com.example.c4match.utils.Log;
import com.example.c4match.ResourceTable;
import com.example.c4match.StudySystem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.bundle.IBundleManager;

import static ohos.security.SystemPermission.DISTRIBUTED_DATASYNC;

/**
 * 主页面, 实现各类功能跳转
 */

public class MainAbilitySlice extends AbilitySlice {
    private static final int PERMISSION_CODE = 10000000; // 授权许可code
    // 主页面组件
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
//        ohos.permission.DISTRIBUTED_DEVICE_STATE_CHANGE：用于允许监听分布式组网内的设备状态变化。
//        ohos.permission.GET_DISTRIBUTED_DEVICE_INFO：用于允许获取分布式组网内的设备列表和设备信息。
//        ohos.permission.GET_BUNDLE_INFO：用于查询其他应用的信息。
//        ohos.permission.DISTRIBUTED_DATASYNC：用于允许不同设备间的数据交换。

        if (verifySelfPermission(DISTRIBUTED_DATASYNC) != IBundleManager.PERMISSION_GRANTED) {
            if (canRequestPermission(DISTRIBUTED_DATASYNC)) {
                requestPermissionsFromUser(new String[] {DISTRIBUTED_DATASYNC}, PERMISSION_CODE);
                Log.info("获取权限成功");
            }
        }
    }

    private void clickButton(Component component){
        switch (component.getId()) {
            case ResourceTable.Id_study_btu: // 如果是游戏按钮,
                studySystem();
                break;
            case ResourceTable.Id_game_btu: //
                gameSystem();
                break;
            case ResourceTable.Id_introduction_btu:

                break;

            default:
                Log.info("更多功能敬请期待");

                break;
        }
    }

    private void studySystem(){
        Log.info("点击[学习系统]模块");
        Intent studyIntent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName(this.getBundleName())
                .withAbilityName(StudySystem.class.getName())
                .withAction(CommonData.MATH_DRAW_EVENT)
                .build();
        studyIntent.setOperation(operation);
        startAbility(studyIntent);
    }

    private void gameSystem(){
        Log.info("点击[游戏系统]模块");
        Intent gameIntent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName(this.getBundleName())
                .withAbilityName(MainAbility.class.getName())
                .withAction(CommonData.PICTURE_PAGE)
                .build();
        gameIntent.setOperation(operation);
        startAbility(gameIntent);

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
