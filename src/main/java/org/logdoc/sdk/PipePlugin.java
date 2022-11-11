package org.logdoc.sdk;

import com.typesafe.config.Config;

import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:21
 * sdk ☭ sweat and blood
 * PipePlugin is an "output" plugin, which handle each event passing through watchdog.
 * Plugin supposed to be a singleton, e.g. it borns when main LogDoc service starts and then just called on each watchdog's reaction.
 *
 * Plugin may differ its logic for each watchdog or may handle every event equally.
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
     *
     * @return true if and only if plugin is properly configured and ready to go
     */
    boolean configure(Config config) throws Exception;

    /**
     * Method invoked on each logging event, which applies to watchdog requirements
     *
     * @param watchdogFire Fire fact, that caused this pipe to invoke, contains all matched entries
     * @param ctx Current context of the watchdog (values of the parameters)
     * @throws Exception plugin may throw any error, it's going to be handled by self LogDoc's logger
     */
    void fire(WatchdogFire watchdogFire, Map<String, String> ctx) throws Exception;
}
