package com.example.c4project.slice;

import com.example.c4project.ResourceTable;
import com.example.c4project.explain;
import com.example.c4project.utils.CommonData;
import com.example.c4project.utils.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.bundle.BackupBundleInfo;
import ohos.bundle.IBundleManager;

import java.util.ArrayList;
import java.util.List;

import static ohos.security.SystemPermission.DISTRIBUTED_DATASYNC;
import static ohos.security.SystemPermission.ENROLL_BIOMETRIC;

public class MainAbilitySlice extends AbilitySlice {
    private static final String TAG = CommonData.TAG + MainAbilitySlice.class.getSimpleName();
    private static final int PERMISSION_CODE = 10000000; // 授权许可code

    DirectionalLayout study_btu;
    DirectionalLayout game_btu;
    Button introduction_btu;
    Button explain_btu;

    Button select_mode_but1;
    Button select_mode_but2;
    Button select_mode_but3;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
//        super.setUIContent(ResourceTable.Layout_ability_main); // 加载页面
        setUIContent(ResourceTable.Layout_recieve_problem);
//        select_model();

//        applyPermission(); // 申请权限
//        initFunction(); // 初始化功能

    }

    private void select_model(){
        setUIContent(ResourceTable.Layout_select_mode);


        Button select_mode_but1 = findComponentById(ResourceTable.Id_select_mode_1_btu);
        Button select_mode_but2 = findComponentById(ResourceTable.Id_select_mode_2_btu);
        Button select_mode_but3 = findComponentById(ResourceTable.Id_select_mode_3_btu);

        select_mode_but1.setClickedListener(this::select_model_button);
        select_mode_but2.setClickedListener(this::select_model_button);

        select_mode_but3.setClickedListener(this::select_model_button);


    }

    private void select_model_button(Component component) {
        switch (component.getId()){
            case ResourceTable.Id_select_mode_1_btu:

                break;
            case ResourceTable.Id_select_mode_2_btu:

                break;
            case ResourceTable.Id_select_mode_3_btu:

                break;

            default:
                break;
        }

    }

    private void setEnable(Button button){
        List<Button> buttonList = new ArrayList<>();
        if(buttonList.size() == 0){
            buttonList.add(select_mode_but1);
            buttonList.add(select_mode_but2);
            buttonList.add(select_mode_but3);

        }
//        for (int i = 0;)


    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


    private void initFunction(){
        study_btu = (DirectionalLayout) this.findComponentById(ResourceTable.Id_study_btu);
        game_btu = (DirectionalLayout) findComponentById(ResourceTable.Id_game_btu);
        introduction_btu = (Button) findComponentById(ResourceTable.Id_introduction_btu);
        explain_btu = (Button) findComponentById(ResourceTable.Id_explain_btu);

        study_btu.setClickedListener(this::clickButton);
        game_btu.setClickedListener(this::clickButton);
        introduction_btu.setClickedListener(this::clickButton);
        explain_btu.setClickedListener(this::clickButton);
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
                LogUtil.info(TAG,"获取权限成功");
            }
        }
    }

    private void studySystem() {
        LogUtil.info(TAG, "Click ResourceTable Id_math_game");
        Intent mathGameIntent = new Intent();
        Operation operationMath = new Intent.OperationBuilder().withBundleName(getBundleName())
                .withAbilityName(CommonData.ABILITY_MAIN)
                .withAction(CommonData.MATH_PAGE)
                .build();
        mathGameIntent.setOperation(operationMath);
        startAbility(mathGameIntent);
    }

    private void gameSystem() {
        LogUtil.info(TAG, "Click ResourceTable Id_picture_game");
        Intent pictureGameIntent = new Intent();
        Operation operationPicture = new Intent.OperationBuilder().withBundleName(getBundleName())
                .withAbilityName(CommonData.ABILITY_MAIN)
                .withAction(CommonData.PICTURE_PAGE)
                .build();
        pictureGameIntent.setOperation(operationPicture);
        startAbility(pictureGameIntent);
    }

    /**
     * 跳转到介绍鸿蒙分布式页面。
     */
    private void introduction(){
        LogUtil.info(TAG, "Click ResourceTable Id_introduction_btu");
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .build();


    }


    /**
     * 跳转到功能说明页面
     */
    private void explain(){
        LogUtil.info(TAG, "Click ResourceTable Id_explain_btu");
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName(getBundleName())
                .withAbilityName(explain.class.getName())
                .build();
        intent.setOperation(operation);
        startAbility(intent);

    }







    private void clickButton(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_study_btu: // 如果是游戏按钮,
                studySystem();
                break;
            case ResourceTable.Id_game_btu: //
                gameSystem();
                break;
            case ResourceTable.Id_introduction_btu:
                introduction();
                break;
            case ResourceTable.Id_explain_btu:
                explain();
                break;

            default:
                LogUtil.info(TAG, "更多功能敬请期待");

                break;
        }

    }


}
