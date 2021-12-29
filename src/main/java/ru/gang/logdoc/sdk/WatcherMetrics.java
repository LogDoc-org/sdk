package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.LogEntry;

import java.time.LocalDateTime;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:30
 * sdk ☭ sweat and blood
 */
public final class WatcherMetrics {
    public boolean entryCountable, cycleRepeatable;
    public int totalEntryCounter, cycleEntryCounter, cycleCounter, cycleEntryLimit, cycleLimit;
    public LogEntry lastMatchedEntry;
    public LocalDateTime watcherCreated, lastMatched;
}
