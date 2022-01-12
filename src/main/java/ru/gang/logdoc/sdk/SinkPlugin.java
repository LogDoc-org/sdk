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
    default void configure(final Config config) { }

    Set<ConnectionType> sinkTypes();

    void setEntryConsumer(Consumer<LogEntry> entryConsumer);

    void chunk(byte[] data, DataAddress source);
}
