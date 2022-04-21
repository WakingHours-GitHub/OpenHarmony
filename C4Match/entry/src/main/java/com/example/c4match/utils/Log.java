package com.example.c4match.utils;

import ohos.global.icu.text.AlphabeticIndex;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class Log {
    private static final String TAG = "log";

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, TAG);

    public static void info(String info) {
        HiLog.info(LABEL, info);
    }

    public static void debug(String debug) {
        HiLog.debug(LABEL, debug);
    }

    public static void error(String error) {
        HiLog.error(LABEL, error);
    }
}
