package com.example.mytest01;

public class MyTask implements Runnable {
    private String name = null;

    public MyTask(String name) {
        this.name = name;
    }

    @Override // 开始编写我们自己的函数
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("--->>>"+this.name);
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); // 打印消息
            }
        }
    }
}
