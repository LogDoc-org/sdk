package ru.gang.logdoc.sdk;

import com.typesafe.config.Config;
import ru.gang.logdoc.structs.LogEntry;

import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:21
 * sdk ☭ sweat and blood
 *
 * PipePlugin is an "output" plugin, which handle each event passing through listener and/or watcher.
 * Plugin supposed to be a singleton, e.g. it borns when main LogDoc service starts and then just called on each listener/watcher reaction.
 *
 * Plugin may differ its logic for each watcher or may handle every event equally.
 */
public interface PipePlugin {
    /**
     * Plugin is configured one time, when main LogDoc service starts.
     * Config is defined in main config file (application.conf).
     * Plugin's section is defined by its full signature, e.g. if plugin class is com.company.custom.MyOutputPlugin, then its section in config must be:
     * logdoc.plugins.pipe.com.company.custom.MyOutputPlugin
     *
     * @param config plugin's config section as a standalone config
     * @throws Exception any configuration error leads to plugin unload
     */
    void configure(Config config) throws Exception;

    /**
     * Method invoked on each logging event, which applies to listener/watcher requirements
     *
     * @param watcherId ID of a listener or watcher which requirements are met by entry
     * @param entry Logging event, caused listener or watcher to apply
     * @param metrics Current state of the listener or watcher
     * @param ctx Current context of the listener or watcher
     * @throws Exception plugin may throw any error, it's going to be handled by self LogDoc's logger
     */
    void fire(String watcherId, LogEntry entry, WatcherMetrics metrics, Map<String, ?> ctx) throws Exception;

    /**
     * Invoked on each listener/watcher creation
     *
     * @param watcherId ID of a listener or watcher
     */
    default void watcherStarted(String watcherId) {}

    /**
     * Invoked on each watcher's cycle iteration, even for one-turn watchers
     *
     * @param watcherId ID of a listener or watcher
     */
    default void watcherCycled(String watcherId) {}

    /**
     * Invoked on each listener/watcher destruction
     *
     * @param watcherId ID of a listener or watcher
     */
    default void watcherStopped(String watcherId) {}
}
