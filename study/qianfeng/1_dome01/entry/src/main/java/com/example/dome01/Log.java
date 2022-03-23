package com.example.dome01;

import ohos.global.icu.text.AlphabeticIndex;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.ObjectOutputStream;

// 封装一下HiLog
public class Log {
    // 定义静态标识
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00021, "mAPP");
    // 参数type：用于指定输出日志的类型。HiLog中当前只提供了一种日志类型，即应用/服务日志类型LOG_APP。
    // 参数domain：用于指定输出日志所对应的业务领域，取值范围为0x0~0xFFFFF，开发者可以根据需要进行自定义。不过这里我们规定是0x00201
    // 参数tag：用于指定日志标识，可以为任意字符串，建议标识调用所在的类或者业务行为。

    // 定义静态方法
    public static void info(String info){
        HiLog.info(LABEL, info);
    }

    public static void debug(String debug){
        HiLog.debug(LABEL, debug);
    }

    public static void error(String error){
        HiLog.error(LABEL, error);
    }

    public static void fatal(String fatal){
        HiLog.fatal(LABEL, fatal);
    }

}
