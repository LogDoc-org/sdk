package ru.gang.logdoc.sdk;

import com.typesafe.config.Config;
import ru.gang.logdoc.structs.LogEntry;

import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:21
 * sdk ☭ sweat and blood
 */
public interface PipePlugin {
    void configure(Config config) throws Exception;

    void fire(String watcherId, LogEntry entry, WatcherMetrics metrics, Map<String, ?> ctx) throws Exception;

    default void watcherStarted(String watcherId) {}

    default void watcherCycled(String watcherId) {}

    default void watcherStopped(String watcherId) {}
}
