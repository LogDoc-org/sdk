package org.logdoc.sdk;

import org.logdoc.structs.LogEntry;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:30
 * sdk ☭ sweat and blood
 *
 * Aggregated instant state of a watcher
 */
public final class WatchdogFire {
    public final String watchdogName;
    public final Collection<LogEntry> matchedEntries;

    public WatchdogFire(final String watchdogName, final Collection<LogEntry> matchedEntries) {
        this.watchdogName = watchdogName;
        this.matchedEntries = matchedEntries;
    }
}
