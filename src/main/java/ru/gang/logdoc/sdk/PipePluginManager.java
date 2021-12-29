package ru.gang.logdoc.sdk;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 13:28
 * sdk ☭ sweat and blood
 */
public interface PipePluginManager {
    void tellToWebSocket(JsonNode message);

    WatcherMetrics getWatcherMetrics();

    void pluginError(Throwable error, PipePlugin plugin);
}
