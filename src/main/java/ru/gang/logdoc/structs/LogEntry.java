package ru.gang.logdoc.structs;

import ru.gang.logdoc.structs.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public final class LogEntry {
    public LocalDateTime dateTime;
    public String pid;
    public String source;
    public String entry;
    public LogLevel level;
    public final Map<String, String> fields = new HashMap<>(0);

    public boolean isValid() {
        return dateTime != null && entry != null && !entry.trim().isEmpty() && level != null;
    }
}
