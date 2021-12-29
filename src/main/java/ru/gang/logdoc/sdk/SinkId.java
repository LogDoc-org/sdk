package ru.gang.logdoc.sdk;

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
