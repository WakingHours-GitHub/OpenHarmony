package com.example.demo01_taskdispatcher;

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

    }
}
