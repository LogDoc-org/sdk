package ru.gang.logdoc.sdk;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 11:42
 * sdk ☭ sweat and blood
 */
public abstract class PluginParameter<T> implements Comparable<PluginParameter<?>> {
    public abstract String name();

    public abstract T eval(String stringValue) throws Exception;

    public String description() {
        return name();
    }

    public T defaultValue() {
        return null;
    }

    public boolean isRequired() {
        return false;
    }

    public boolean isMultiple() {
        return false;
    }

    @Override
    public final int compareTo(final PluginParameter<?> o) {
        if (o == null || name() == null || name().trim().isEmpty())
            return 1;

        if (o.name() == null || o.name().trim().isEmpty())
            return -1;

        return name().compareTo(o.name());
    }
}
