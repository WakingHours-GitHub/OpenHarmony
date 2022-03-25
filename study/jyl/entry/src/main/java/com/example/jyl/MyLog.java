package com.example.jyl;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MyLog {
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "mAPP");

    public static void info(String info){
        HiLog.info(LABEL, info);
    }

    public static void debug(String debug){
        HiLog.debug(LABEL, debug);
    }
}
