package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.enums.Proto;

/**
 * Connection type is a pair of protocol and unique name
 */
public final class ConnectionType implements Comparable<ConnectionType> {
    public Proto proto;
    public String name;

    @Override
    public int compareTo(final ConnectionType o) {
        final int res = name.compareTo(o.name);

        return res != 0 ? res : proto.name().compareTo(o.proto.name());
    }
}
