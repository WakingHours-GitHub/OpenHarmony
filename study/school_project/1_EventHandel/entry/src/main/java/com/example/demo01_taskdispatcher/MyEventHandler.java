package com.example.demo01_taskdispatcher;

import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;

public class MyEventHandler extends EventHandler {
    // 构造函数
    public MyEventHandler(EventRunner runner) throws IllegalArgumentException {
        super(runner);
    }

    // 重写eventHandler处理函数
    @Override
    protected void processEvent(InnerEvent event) {
        super.processEvent(event);
        // 下面就是重写我们自己的处理过程.
        Object enentObject = event.object;
        int eventId = event.eventId; // 接受消息
        Log.info("来自主线程的消息标号: "+eventId);
    }
}
