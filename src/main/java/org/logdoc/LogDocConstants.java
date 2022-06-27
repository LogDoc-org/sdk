package org.logdoc;

import java.time.format.DateTimeFormatter;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public interface LogDocConstants {
    byte[] header = new byte[] {(byte) 6, (byte) 3};

    DateTimeFormatter logTimeFormat = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    interface Fields {
        String TimeSrc = "tsrc";
        String Pid = "pid";
        String Source = "src";
        String Level = "lvl";
        String Message = "msg";
        String TimeRcv = "trcv";
        String Ip = "ip";
        String AppName = "app";
    }
}
