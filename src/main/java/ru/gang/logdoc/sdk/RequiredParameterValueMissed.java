package ru.gang.logdoc.sdk;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 12:29
 * sdk ☭ sweat and blood
 */
public final class RequiredParameterValueMissed extends Exception {
    public RequiredParameterValueMissed(final String paramName) {
        super("Required parameter value missed: " + paramName);
    }
}
