package com.example.demo02.slice;

import com.example.demo02.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.backgroundtaskmgr.BackgroundTaskManager;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);


    }

    private void page_jumps(Component component) {
        Intent intent = new Intent();
        // 创建一个intent操作
        // 可以理解为我们的操作意图
        Intent.OperationBuilder operationBuilder = new Intent.OperationBuilder();
        Operation operation = operationBuilder.withDeviceId("") // 设置设备id, 如果是当前设备, 则传空字符串
                .withBundleName("") // 每个应用的唯一表示, BundName
                .withAbilityName("") // 启动哪个Ability
                .build();
                // 我们可以启动不同设备上的不同应用
        // 或者一句完成:
//        Operation operation1 = new Intent.OperationBuilder().withDeviceId("").withBundleName("").withAbilityName("").build();


        // intent对象, 包含operation和parameters
                // parameters用于数据传递
                // operation表示Ability和Slice目标
                // 将我们的意图操作, 添加给意图
        intent.setOperation(operation);

        // 启动另外一个PageAbility, 启动其他Ability
        this.startAbility(intent);

        // 1. 在一个鸿蒙应用中可以启动当前设备的当前应用的其他Ability
        // 2. 也可也启动当前设计的其他应用中的PageAbility (需要授权)
        // 3. 还可以启动其他设备的应用中的PageAbility
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
