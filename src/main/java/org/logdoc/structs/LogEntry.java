package org.logdoc.structs;

import org.logdoc.structs.enums.LogLevel;
import org.logdoc.LogDocConstants;
import org.logdoc.utils.Tools;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.logdoc.utils.Tools.notNull;

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
    public String appName;
    public LogLevel level;

    private final Map<String, String> fields;

    public LogEntry() {
        fields = new HashMap<>(0);
        rcvTime = LocalDateTime.now().format(LogDocConstants.logTimeFormat);
    }

    public LogEntry(final Map<String, String> map) {
        this();

        if (map.containsKey(LogDocConstants.Fields.TimeSrc)) srcTime = map.remove(LogDocConstants.Fields.TimeSrc);
        if (map.containsKey(LogDocConstants.Fields.TimeRcv)) rcvTime = map.remove(LogDocConstants.Fields.TimeRcv);
        if (map.containsKey(LogDocConstants.Fields.Ip)) ip = map.remove(LogDocConstants.Fields.Ip);
        if (map.containsKey(LogDocConstants.Fields.Pid)) pid = map.remove(LogDocConstants.Fields.Pid);
        if (map.containsKey(LogDocConstants.Fields.Level)) level = LogLevel.valueOf(map.remove(LogDocConstants.Fields.Level));
        if (map.containsKey(LogDocConstants.Fields.Source)) source = map.remove(LogDocConstants.Fields.Source);
        if (map.containsKey(LogDocConstants.Fields.AppName)) appName = map.remove(LogDocConstants.Fields.AppName);

        entry = map.remove(LogDocConstants.Fields.Message);

        if (!map.isEmpty())
            fields.putAll(map);
    }

    public Map<String, String> asMap() {
        final Map<String, String> map = new HashMap<>(fields);

        if (!Tools.isEmpty(ip)) map.put(LogDocConstants.Fields.Ip, ip);
        if (!Tools.isEmpty(level)) map.put(LogDocConstants.Fields.Level, level.name());
        if (!Tools.isEmpty(entry)) map.put(LogDocConstants.Fields.Message, entry);
        if (!Tools.isEmpty(pid)) map.put(LogDocConstants.Fields.Pid, pid);
        if (!Tools.isEmpty(source)) map.put(LogDocConstants.Fields.Source, source);
        if (!Tools.isEmpty(rcvTime)) map.put(LogDocConstants.Fields.TimeRcv, rcvTime);
        if (!Tools.isEmpty(srcTime)) map.put(LogDocConstants.Fields.TimeSrc, srcTime);
        if (!Tools.isEmpty(appName)) map.put(LogDocConstants.Fields.AppName, appName);

        return map;
    }

    public Collection<Map.Entry<String, String>> fields() {
        return fields.entrySet();
    }

    public void field(final String name, final String value0) {
        if (Tools.isEmpty(name) || Tools.isEmpty(value0))
            return;

        final String value = Tools.notNull(value0);

        switch (name.trim()) {
            case LogDocConstants.Fields.Level:
                level = LogLevel.asNullLog(value);
                break;
            case LogDocConstants.Fields.Ip:
                ip = value;
                break;
            case LogDocConstants.Fields.Message:
                entry = value;
                break;
            case LogDocConstants.Fields.Pid:
                pid = value;
                break;
            case LogDocConstants.Fields.Source:
                source = Tools.notNull(value, "no_source");
                break;
            case LogDocConstants.Fields.TimeSrc:
                srcTime = value;
                break;
            case LogDocConstants.Fields.TimeRcv:
                rcvTime = value;
                break;
            case LogDocConstants.Fields.AppName:
                appName = value;
                break;
            default:
                fields.put(name, value);
                break;
        }
    }

    public String field(final String name) {
        if (Tools.isEmpty(name))
            return "";

        switch (name) {
            case LogDocConstants.Fields.AppName:
                return appName;
            case LogDocConstants.Fields.Ip:
                return ip;
            case LogDocConstants.Fields.Message:
                return entry;
            case LogDocConstants.Fields.Source:
                return source;
            case LogDocConstants.Fields.Level:
                return level.name();
            case LogDocConstants.Fields.Pid:
                return pid;
            case LogDocConstants.Fields.TimeRcv:
                return rcvTime;
            case LogDocConstants.Fields.TimeSrc:
                return srcTime;
        }

        return fields.get(name);
    }

    public String fieldRemove(final String name) {
        return fields.remove(name);
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "srcTime='" + srcTime + '\'' +
                ", rcvTime='" + rcvTime + '\'' +
                ", ip='" + ip + '\'' +
                ", pid='" + pid + '\'' +
                ", source='" + source + '\'' +
                ", level=" + level +
                ", entry='" + entry + '\'' +
                ", appName='" + appName + '\'' +
                ", fields=" + fields +
                '}';
    }
}
