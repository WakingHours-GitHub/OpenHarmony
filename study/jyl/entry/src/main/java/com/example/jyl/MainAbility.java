package com.example.jyl;

import com.example.jyl.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        // 只能有一个全局任务分发器
        TaskDispatcher globalTaskDispatcher = this.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        globalTaskDispatcher.asyncDispatch(new MyThread("任务1")); // 任务派发
        MyLog.info("任务1后面");
        globalTaskDispatcher.syncDispatch(new MyThread("任务2"));
        MyLog.info("任务2后面");


        // 获取并发任务分布器, 可以创建多个
        TaskDispatcher parallelTaskDispatcher = createParallelTaskDispatcher("myTask1", TaskPriority.DEFAULT);

        parallelTaskDispatcher.delayDispatch(new MyThread("任务3"),3000); // 3s延迟后面派发

    }
}
