package com.example.dome01;

import com.example.dome01.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.render.render3d.Task;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

/*
启动流程
 */

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

        // 疯狂匿名实现Thread并且直接开启线程.
        new Thread( // .start直接开启
                new Runnable() { // 重载接口中的方法
                    @Override
                    public void run() {

                    }
                }
        ).start();

        /*
        鸿蒙的方式:
        // 获取全局任务分配程序
        TaskDispatcher taskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        taskDispatcher.asyncDispatch(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }
        );*/


    }

}


