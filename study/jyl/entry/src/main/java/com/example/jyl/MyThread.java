package com.example.jyl;

public class MyThread implements Runnable{
    public String taskName = null;

    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        MyLog.info("进入线程"+this.taskName);
        // 执行一个耗时操作:
        int i = 1; // 这里只做演示
        do {
            try {
//                MyLog.info("线程休眠1000ms");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }while (i != 0);

        MyLog.info(this.taskName+" 已经执行完毕");

    }
}
