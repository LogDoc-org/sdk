package ru.gang.logdoc;

import java.time.format.DateTimeFormatter;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public interface LogDocConstants {
    DateTimeFormatter logTimeFormat = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    String FieldTimeStamp = "time_src";
    String FieldProcessId = "source_id";
    String FieldSource = "source_name";
    String FieldLevel = "level";
    String FieldMessage = "log_message";
    String FieldTimeRcv = "time_rcv";
    String FieldIp = "source_ip";
}
