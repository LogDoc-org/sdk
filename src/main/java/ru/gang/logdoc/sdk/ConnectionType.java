package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.enums.Proto;

import java.util.Objects;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 *
 * Named tag fully describe one socket connection
 */
public final class ConnectionType implements Comparable<ConnectionType> {
    /**
     * Mid-level type of connection, more logic term than technical
     */
    public Proto proto;
    /**
     * Unique name
     */
    public String name;

    public ConnectionType() {
    }

    public ConnectionType(final Proto proto, final String name) {
        this.proto = proto;
        this.name = name;
    }

    @Override
    public String toString() {
        return '[' + name + "' {" + proto + "}]";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ConnectionType that = (ConnectionType) o;
        return proto == that.proto && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proto, name);
    }

    @Override
    public int compareTo(final ConnectionType o) {
        final int res = name.compareTo(o.name);

        return res != 0 ? res : proto.name().compareTo(o.proto.name());
    }
}
