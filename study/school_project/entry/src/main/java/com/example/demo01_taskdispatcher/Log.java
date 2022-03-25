package com.example.demo01_taskdispatcher;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class Log {
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "Task");

    public static void info(String info){
        HiLog.info(LABEL, info);
    }

    public static void debug(String debug){
        HiLog.debug(LABEL, debug);
    }
}
