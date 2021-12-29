package ru.gang.logdoc.sdk;

import com.typesafe.config.Config;
import ru.gang.logdoc.structs.SinkLogEntry;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:21
 * sdk ☭ sweat and blood
 */
public interface PipePlugin {
    void configure(Config config) throws Exception;
    void handle(SinkLogEntry entry) throws Exception;

    void setManager(PipePluginManager manager);
}
