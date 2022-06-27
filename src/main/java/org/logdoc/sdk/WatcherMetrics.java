package org.logdoc.sdk;

import org.logdoc.structs.LogEntry;

import java.time.LocalDateTime;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:30
 * sdk ☭ sweat and blood
 *
 * Aggregated instant state of a watcher
 */
public final class WatcherMetrics {
    public boolean entryCountable;
    public boolean cycleRepeatable;
    public int totalEntryCounter;
    public int cycleEntryCounter;
    public int cycleCounter;
    public int cycleEntryLimit;
    public int cycleLimit;
    public LogEntry lastMatchedEntry;
    public LocalDateTime watcherCreated;
    public LocalDateTime lastMatched;

    public WatcherMetrics() {
    }

    public WatcherMetrics(final int totalEntryCounter, final int cycleEntryCounter, final int cycleCounter, final int cycleEntryLimit, final int cycleLimit, final LogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
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

    public WatcherMetrics(final int cycleEntryCounter, final int cycleEntryLimit, final LogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
        this.cycleEntryCounter = cycleEntryCounter;
        this.cycleEntryLimit = cycleEntryLimit;
        this.lastMatchedEntry = lastMatchedEntry;
        this.watcherCreated = watcherCreated;
        this.lastMatched = lastMatched;

        entryCountable = true;
    }

    public WatcherMetrics(final LogEntry lastMatchedEntry, final LocalDateTime watcherCreated, final LocalDateTime lastMatched) {
        this.lastMatchedEntry = lastMatchedEntry;
        this.watcherCreated = watcherCreated;
        this.lastMatched = lastMatched;
    }
}
