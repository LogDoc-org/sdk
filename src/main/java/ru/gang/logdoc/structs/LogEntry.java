package ru.gang.logdoc.structs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.gang.logdoc.LogDocConstants;
import ru.gang.logdoc.structs.enums.LogLevel;
import ru.gang.logdoc.utils.Tools;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ru.gang.logdoc.LogDocConstants.*;
import static ru.gang.logdoc.utils.Tools.isEmpty;
import static ru.gang.logdoc.utils.Tools.notNull;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 12.01.2022 17:39
 * sdk ☭ sweat and blood
 */
public final class LogEntry {
    public String ip;
    public String srcTime;
    public String rcvTime;
    public String pid;
    public String source;
    public String entry;
    public LogLevel level;

    private final Map<String, String> fields;

    public LogEntry() {
        fields = new HashMap<>(0);
        rcvTime = LocalDateTime.now().format(Tools.logTimeFormat);
    }

    public LogEntry(final Map<String, String> map) {
        this();

        if (map.containsKey(FieldTimeStamp)) srcTime = map.remove(FieldTimeStamp);
        if (map.containsKey(FieldTimeRcv)) rcvTime = map.remove(FieldTimeRcv);
        if (map.containsKey(FieldIp)) ip = map.remove(FieldIp);
        if (map.containsKey(FieldProcessId)) pid = map.remove(FieldProcessId);
        if (map.containsKey(FieldLevel)) level = LogLevel.valueOf(map.remove(FieldLevel));
        if (map.containsKey(FieldSource)) source = map.remove(FieldSource);

        entry = map.remove(FieldMessage);

        if (!map.isEmpty())
            fields.putAll(map);
    }

    public Map<String, String> asMap() {
        final Map<String, String> map = new HashMap<>(fields);

        if (!isEmpty(ip)) map.put(LogDocConstants.FieldIp, ip);
        if (!isEmpty(level)) map.put(LogDocConstants.FieldLevel, level.name());
        if (!isEmpty(entry)) map.put(LogDocConstants.FieldMessage, entry);
        if (!isEmpty(pid)) map.put(LogDocConstants.FieldProcessId, pid);
        if (!isEmpty(source)) map.put(LogDocConstants.FieldSource, source);
        if (!isEmpty(rcvTime)) map.put(LogDocConstants.FieldTimeRcv, rcvTime);
        if (!isEmpty(srcTime)) map.put(LogDocConstants.FieldTimeStamp, srcTime);

        return map;
    }

    @JsonIgnore
    public boolean isValid() {
        return srcTime != null && rcvTime != null && !isEmpty(entry) && level != null;
    }

    public Collection<Map.Entry<String, String>> fields() {
        return fields.entrySet();
    }

    public void field(final String name, final String value0) {
        if (isEmpty(name) || isEmpty(value0))
            return;

        final String value = notNull(value0);

        switch (name.trim()) {
            case FieldLevel:
                level = LogLevel.valueOf(value);
                break;
            case FieldIp:
                ip = value;
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
            case FieldTimeStamp:
                srcTime = value;
                break;
            case FieldTimeRcv:
                rcvTime = value;
                break;
            default:
                fields.put(name, value);
                break;
        }
    }

    public String field(final String name) {
        return fields.get(name);
    }

    public String fieldRemove(final String name) {
        return fields.remove(name);
    }

    @Override
    public String toString() {
        return "SinkLogEntry{" +
                "srcTime='" + srcTime + '\'' +
                ", rcvTime='" + rcvTime + '\'' +
                ", ip='" + ip + '\'' +
                ", pid='" + pid + '\'' +
                ", source='" + source + '\'' +
                ", level=" + level +
                ", entry='" + entry + '\'' +
                ", fields=" + fields +
                '}';
    }
}
