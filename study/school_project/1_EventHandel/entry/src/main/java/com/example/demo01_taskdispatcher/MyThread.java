package com.example.demo01_taskdispatcher;

import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;

import javax.security.auth.PrivateCredentialPermission;

public class MyThread implements Runnable{
    public String taskName = null;

    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        Log.info("进入到run: "+this.taskName);
        // 执行一个耗时操作:
        int i = 1; // 这里只做演示
        do {
            try {
                Log.info("MyThread的["+taskName+"]线程休眠1000ms");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }while (i != 0);

        Log.info(this.taskName+" 已经执行完毕");

        // 返回消息, 返回给主线程
        //
        Log.info("通知主线程");

        // 构建消息体
        InnerEvent event = InnerEvent.get(1, 0, this.taskName+"子线程工作完成");

        // 通知主线程
        // 获取一个绑定主线程的一个eventHandler
        EventRunner eventRunner = EventRunner.getMainEventRunner();
        EventHandler eventHandler = new EventHandler(eventRunner);
        eventHandler.sendEvent(event);
        Log.info("发送完毕");


    }
}
