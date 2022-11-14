package org.logdoc.sdk;

import java.util.Objects;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 * Sink is a single open socket accepting incoming logs,
 * SinkId is its unique ID
 */
public final class SinkId implements Comparable<SinkId> {
    /**
     * Socket's port
     */
    public int port;
    /**
     * Socket's parameters
     */
    public ConnectionType type;

    public SinkId() { }

    public SinkId(final int port, final ConnectionType type) {
        this.port = port;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + type + " @ " + port + ']';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SinkId sinkId = (SinkId) o;
        return port == sinkId.port && type.equals(sinkId.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, type);
    }

    @Override
    public int compareTo(final SinkId o) {
        int res = Integer.compare(port, o.port);

        if (res != 0)
            return res;

        return type.compareTo(o.type);
    }
}
