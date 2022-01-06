package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.DataAddress;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 05.01.2022 17:57
 * sdk ☭ sweat and blood
 */
public interface SinkSyncPlugin extends SinkPlugin {
    @Override
    default void chunk(byte[] data, DataAddress source) { }

    byte[] request(byte[] data, DataAddress source);

    long maxRequestSize();
    boolean closeOnResponse();
}
