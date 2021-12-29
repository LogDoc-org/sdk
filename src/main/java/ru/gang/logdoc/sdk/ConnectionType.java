package ru.gang.logdoc.sdk;

import ru.gang.logdoc.structs.enums.Proto;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
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
