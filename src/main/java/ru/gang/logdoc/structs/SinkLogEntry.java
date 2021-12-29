package ru.gang.logdoc.structs;

import ru.gang.logdoc.structs.enums.LogLevel;

import java.util.HashMap;
import java.util.Map;

import static ru.gang.logdoc.LogDocConstants.*;
import static ru.gang.logdoc.LogDocConstants.FieldSource;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public final class SinkLogEntry {
    public String srcTime, rcvTime;
    public String ip, pid, source;
    public LogLevel level;
    public String entry;
    public Map<String, String> fields;

    public SinkLogEntry() {
        fields = new HashMap<>(0);
    }

    public void field(final String name, final String value0) {
        if (notNull(name).isEmpty() || notNull(value0).isEmpty())
            return;

        final String value = notNull(value0);

        switch (name.trim()) {
            case FieldLevel:
                level = LogLevel.valueOf(value);
                break;
            case FieldIp:
                ip = value;
                break;
            case FieldTimeRcv:
                rcvTime = value;
                break;
            case FieldTimeStamp:
                srcTime = value;
                break;
            case FieldMessage:
                entry = value;
                break;
            case FieldProcessId:
                pid = value;
                break;
            case FieldSource:
                source = notNull(value, "no_source");
                break;
            default:
                fields.put(name, value);
                break;
        }
    }

    private String notNull(final String value0) {
        return notNull(value0, "");
    }

    private String notNull(final String value0, final String def) {
        return value0 == null || value0.trim().isEmpty() ? def : value0.trim();
    }
}
