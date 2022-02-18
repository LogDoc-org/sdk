package ru.gang.logdoc.structs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.gang.logdoc.structs.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ru.gang.logdoc.LogDocConstants.Fields;
import static ru.gang.logdoc.LogDocConstants.logTimeFormat;
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
        rcvTime = LocalDateTime.now().format(logTimeFormat);
    }

    public LogEntry(final Map<String, String> map) {
        this();

        if (map.containsKey(Fields.TimeSrc)) srcTime = map.remove(Fields.TimeSrc);
        if (map.containsKey(Fields.TimeRcv)) rcvTime = map.remove(Fields.TimeRcv);
        if (map.containsKey(Fields.Ip)) ip = map.remove(Fields.Ip);
        if (map.containsKey(Fields.Pid)) pid = map.remove(Fields.Pid);
        if (map.containsKey(Fields.Level)) level = LogLevel.valueOf(map.remove(Fields.Level));
        if (map.containsKey(Fields.Source)) source = map.remove(Fields.Source);

        entry = map.remove(Fields.Message);

        if (!map.isEmpty())
            fields.putAll(map);
    }

    public Map<String, String> asMap() {
        final Map<String, String> map = new HashMap<>(fields);

        if (!isEmpty(ip)) map.put(Fields.Ip, ip);
        if (!isEmpty(level)) map.put(Fields.Level, level.name());
        if (!isEmpty(entry)) map.put(Fields.Message, entry);
        if (!isEmpty(pid)) map.put(Fields.Pid, pid);
        if (!isEmpty(source)) map.put(Fields.Source, source);
        if (!isEmpty(rcvTime)) map.put(Fields.TimeRcv, rcvTime);
        if (!isEmpty(srcTime)) map.put(Fields.TimeSrc, srcTime);

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
            case Fields.Level:
                level = LogLevel.asNullLog(value);
                break;
            case Fields.Ip:
                ip = value;
                break;
            case Fields.Message:
                entry = value;
                break;
            case Fields.Pid:
                pid = value;
                break;
            case Fields.Source:
                source = notNull(value, "no_source");
                break;
            case Fields.TimeSrc:
                srcTime = value;
                break;
            case Fields.TimeRcv:
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
