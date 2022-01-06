package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.InLogEntry;

import java.time.LocalDateTime;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:30
 * sdk ☭ sweat and blood
 */
public final class WatcherMetrics {
    public boolean entryCountable, cycleRepeatable;
    public int totalEntryCounter, cycleEntryCounter, cycleCounter, cycleEntryLimit, cycleLimit;
    public InLogEntry lastMatchedEntry;
    public LocalDateTime watcherCreated, lastMatched;

    public WatcherMetrics() {
    }

    public WatcherMetrics(final int totalEntryCounter, final int cycleEntryCounter, final int cycleCounter, final int cycleEntryLimit, final int cycleLimit, final InLogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
        this.totalEntryCounter = totalEntryCounter;
        this.cycleEntryCounter = cycleEntryCounter;
        this.cycleCounter = cycleCounter;
        this.cycleEntryLimit = cycleEntryLimit;
        this.cycleLimit = cycleLimit;
        this.lastMatchedEntry = lastMatchedEntry;
        this.watcherCreated = watcherCreated;
        this.lastMatched = lastMatched;

        entryCountable = true;
        cycleRepeatable = true;
    }

    public WatcherMetrics(final int cycleEntryCounter, final int cycleEntryLimit, final InLogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
        this.cycleEntryCounter = cycleEntryCounter;
        this.cycleEntryLimit = cycleEntryLimit;
        this.lastMatchedEntry = lastMatchedEntry;
        this.watcherCreated = watcherCreated;
        this.lastMatched = lastMatched;

        entryCountable = true;
    }

    public WatcherMetrics(final InLogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
        this.lastMatchedEntry = lastMatchedEntry;
        this.watcherCreated = watcherCreated;
        this.lastMatched = lastMatched;
    }
}
