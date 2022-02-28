package ru.gang.logdoc.structs;

import ru.gang.logdoc.sdk.SinkId;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Objects;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 29.12.2021 11:23
 * sdk ☭ sweat and blood
 */
public final class DataAddress implements Comparable<DataAddress> {
    public final InetSocketAddress address;
    public final SinkId sink;

    private volatile String ip, host;

    public DataAddress(final SocketAddress address, final SinkId sink) {
        this.address = (InetSocketAddress) address;
        this.sink = sink;
    }

    public String ip() {
        if (ip == null)
            ip = address.getAddress().getHostAddress();

        return ip;
    }

    public String host() {
        if (host == null)
            host = address.getHostName();

        return host;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DataAddress logSource = (DataAddress) o;
        return address.equals(logSource.address) && sink.equals(logSource.sink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, sink);
    }

    @Override
    public int compareTo(final DataAddress o) {
        final int res = sink.compareTo(o.sink);

        return  res != 0 ? res : Integer.compare(address.hashCode(), o.address.hashCode());
    }
}
