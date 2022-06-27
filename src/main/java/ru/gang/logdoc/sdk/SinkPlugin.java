package ru.gang.logdoc.sdk;

import com.typesafe.config.Config;
import ru.gang.logdoc.structs.DataAddress;
import ru.gang.logdoc.structs.LogEntry;

import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:37
 * sdk ☭ sweat and blood
 *
 * SinkPlugin is an "input" plugin. It handles incoming streams and constructs LogEntry from bytes.
 *
 * Plugin is called by manager each time, when from socket came more than 0 and not more than maxReadBuf bytes.
 * Data is never repeated, it means when there are some bytes cannot be converted into LogEntry right now - its plugin's responsibility to store it untill tail data is received.
 * Data is not padded. It is breaked by socket flow or by time or by size. No one except of plugin must take care of it.
 */
public interface SinkPlugin {
    /**
     * Plugin is configured one time, when main LogDoc service starts.
     * Config is defined in main config file (application.conf).
     * Plugin's section is defined by its full signature, e.g. if plugin class is com.company.custom.MyInputPlugin, then its section in config must be:
     * logdoc.plugins.sink.com.company.custom.MyInputPlugin
     *
     * @param config plugin's config section as a standalone config
     * @param entryConsumer consumer where plugin must apply constructed LogEntry events
     */
    default void configure(final Config config, Consumer<LogEntry> entryConsumer) { }

    /**
     * Maximum read buffer size
     *
     * @return read buffer size
     */
    default long maxReadBuf() {
        return 10485760L;
    }

    /**
     * Plugin can handle several connection types. One plugin instance will be handling these streams simultaneously
     *
     * @return supported connection types
     */
    Set<ConnectionType> sinkTypes();

    /**
     * Method is called by manager each time, when from socket came more than 0 and not more than maxReadBuf bytes.
     * Data is never repeated, it means when there are some bytes cannot be converted into LogEntry right now - its plugin's responsibility to store it untill tail data is received.
     * Data is not padded. It is breaked by socket flow or by time or by size. No one except of plugin must take care of it.
     *
     * @param data data from an open connection
     * @param source unique source address
     * @return response bytes if any and if source connection is tcp-based. null response means nothing will be sent back to source.
     */
    byte[] chunk(byte[] data, DataAddress source);

    /**
     * Marks if this plugin should act like a http actor - close connection after handling request.
     *
     * @return boolean - true, if connection should be closed right after handling request
     */
    default boolean isDeterminated() {
        return false;
    }
}
