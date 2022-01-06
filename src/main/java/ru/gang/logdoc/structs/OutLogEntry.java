package ru.gang.logdoc.structs;

import ru.gang.logdoc.structs.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 15:40
 * sdk ☭ sweat and blood
 */
public final class OutLogEntry {
    public LocalDateTime srcTime, rcvTime;
    public String ip, pid, source;
    public LogLevel level;
    public String entry;
    public Map<String, String> fields;
}
