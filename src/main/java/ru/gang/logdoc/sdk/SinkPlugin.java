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
 */
public interface SinkPlugin {
    default void configure(final Config config, Consumer<LogEntry> entryConsumer) { }
    default long maxReadBuf() {
        return 10485760L;
    }

    Set<ConnectionType> sinkTypes();

    byte[] chunk(byte[] data, DataAddress source);

    /**
     * Marks if this plugin should act like http actor - close connection after handling request.
     *
     * @return boolean - true, if connection should be closed right after handling request
     */
    default boolean isDeterminated() {
        return false;
    }
}
