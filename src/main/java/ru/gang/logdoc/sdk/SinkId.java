package ru.gang.logdoc.sdk;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public final class SinkId implements Comparable<SinkId> {
    public int port;
    public String name;
    public ConnectionType type;

    @Override
    public int compareTo(final SinkId o) {
        int res = name.compareTo(o.name);

        if (res != 0)
            return res;

        res = Integer.compare(port, o.port);

        if (res != 0)
            return res;

        return type.compareTo(o.type);
    }
}
