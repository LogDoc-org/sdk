package org.logdoc.structs.enums;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public enum LogLevel {
    DEBUG, INFO, LOG, WARN, ERROR, SEVERE, PANIC;

    public static LogLevel asNullLog(String s) {
        try {return valueOf(s.trim().toUpperCase());} catch (final Exception ignore) {}

        return null;
    }

    public static LogLevel asLog(String s) {
        if (s != null && !s.trim().isEmpty())
            try {return valueOf(s.trim().toUpperCase());} catch (final Exception ignore) {}

        return LOG;
    }
}
