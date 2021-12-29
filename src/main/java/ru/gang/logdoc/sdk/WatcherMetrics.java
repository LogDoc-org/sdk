package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.SinkLogEntry;

import java.time.LocalDateTime;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:30
 * structs-lib ☭ sweat and blood
 */
public final class WatcherMetrics {
    public boolean entryCountable, cycleRepeatable;
    public int totalEntryCounter, cycleEntryCounter, cycleCounter, cycleEntryLimit, cycleLimit;
    public SinkLogEntry lastMatchedEntry;
    public LocalDateTime watcherCreated, lastMatched;
}
