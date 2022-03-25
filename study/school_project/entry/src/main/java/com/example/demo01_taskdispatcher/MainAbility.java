package com.example.demo01_taskdispatcher;

import com.example.demo01_taskdispatcher.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.app.dispatcher.Group;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.Revocable;
import ohos.app.dispatcher.task.TaskPriority;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        Log.info(this.getClass()+"::onStart");

        // 获取全局并发任务分发器
//        TaskDispatcher globalTaskDispatcher = this.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        // 使用并行
//        globalTaskDispatcher.asyncDispatch(new MyThread("job1_并行")); // 任务派发
//        Log.info("job1后");
        // 使用穿行
//        globalTaskDispatcher.syncDispatch(new MyThread("job2_串行"));
//        Log.info("job2后");


        // 获取并发任务分布器, 可以创建多个
//        TaskDispatcher parallelTaskDispatcher = createParallelTaskDispatcher("并行task", TaskPriority.DEFAULT);
        // 延迟同步派发
//        parallelTaskDispatcher.delayDispatch(new MyThread("job_3"),1000);
        // 同步任务分发器, 同理

        // 任务组派发:
//        Group myGroup = parallelTaskDispatcher.createDispatchGroup();
//        parallelTaskDispatcher.asyncGroupDispatch(myGroup,new MyThread("job_4"));
//        parallelTaskDispatcher.asyncGroupDispatch(myGroup,new MyThread("job_5"));
//        parallelTaskDispatcher.groupDispatchWait(myGroup, 3000); // 等待6s
        Log.info("所有job结束");

        // 设置专用线程
        // 由该分发器分发的所有的任务都是在主线程上按顺序执行,
        TaskDispatcher mainTaskDispatcher = getUITaskDispatcher();
        Log.info("job_6开始前");
        Revocable revocable = mainTaskDispatcher.asyncDispatch(new MyThread("job_6"));
        mainTaskDispatcher.syncDispatch(new MyThread("等会"));
        boolean revoke = revocable.revoke(); // 取消任务
        Log.info(revoke?"取消成功":"取消失败");
        Log.info("job_6后");



    }
}
