package org.logdoc.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class Tools {
    public static final SecureRandom rnd;

    static {
        rnd = new SecureRandom();
        rnd.setSeed(System.currentTimeMillis());
    }

    public static void writeShort(final short sh, final OutputStream os) throws IOException {
        os.write((sh >>> 8) & 0xff);
        os.write((sh) & 0xff);
    }

    public static void writeInt(final int in, final OutputStream os) throws IOException {
        os.write((in >>> 24) & 0xff);
        os.write((in >>> 16) & 0xff);
        os.write((in >>> 8) & 0xff);
        os.write((in) & 0xff);
    }

    public static void writeLong(final long in, final OutputStream os) throws IOException {
        os.write((byte) (in >>> 56));
        os.write((byte) (in >>> 48));
        os.write((byte) (in >>> 40));
        os.write((byte) (in >>> 32));
        os.write((byte) (in >>> 24));
        os.write((byte) (in >>> 16));
        os.write((byte) (in >>> 8));
        os.write((byte) (in));
    }

    public static void writeUtf(final String s, final OutputStream os) throws IOException {
        final byte[] data = Tools.notNull(s).getBytes(StandardCharsets.UTF_8);

        writeShort((short) data.length, os);
        os.write(data);
    }

    public static long asLong(final byte[] buf) {
        if (isEmpty(buf) || buf.length < 8)
            return 0;

        return ((long) buf[0] << 56) +
                ((long) (buf[1] & 255) << 48) +
                ((long) (buf[2] & 255) << 40) +
                ((long) (buf[3] & 255) << 32) +
                ((long) (buf[4] & 255) << 24) +
                ((buf[5] & 255) << 16) +
                ((buf[6] & 255) << 8) +
                ((buf[7] & 255));
    }

    public static int asInt(final byte[] buf) {
        if (isEmpty(buf) || buf.length < 4)
            return 0;

        return ((buf[0] & 0xff) << 24) + ((buf[1] & 0xff) << 16) + ((buf[2] & 0xff) << 8) + (buf[3] & 0xff);
    }

    public static short asShort(final byte[] buf) {
        if (isEmpty(buf) || buf.length < 2)
            return 0;

        return (short) ((buf[0] & 0xFF) << 8 | (buf[1] & 0xFF));
    }

    public static String tokenString() {
        return generateUuid().toString();
    }

    public static byte[] token() {
        return asBytes(generateUuid());
    }

    public static String tokenOf(final byte[] array) {
        final ByteBuffer bb = ByteBuffer.wrap(array);

        return new UUID(bb.getLong(), bb.getLong()).toString();
    }

    public static byte[] tokenOf(final String token) {
        return asBytes(UUID.fromString(token));
    }

    private static byte[] asBytes(final UUID uuid) {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);

        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return bb.array();
    }

    private static long tol(final byte[] buf, final int shift) {
        return (toi(buf, shift) << 32) + ((toi(buf, shift + 4) << 32) >>> 32);
    }

    private static long toi(final byte[] buf, int shift) {
        return (buf[shift] << 24)
                + ((buf[++shift] & 0xFF) << 16)
                + ((buf[++shift] & 0xFF) << 8)
                + (buf[++shift] & 0xFF);
    }

    public static UUID generateUuid() {
        final byte[] buffer = new byte[16];
        rnd.nextBytes(buffer);

        return generateUuid(buffer);
    }

    private static UUID generateUuid(final byte[] buffer) {
        long r1, r2;

        r1 = tol(buffer, 0);
        r2 = tol(buffer, 1);

        r1 &= ~0xF000L;
        r1 |= 4 << 12;
        r2 = ((r2 << 2) >>> 2);
        r2 |= (2L << 62);

        return new UUID(r1, r2);
    }

    public static String quoteEscape(final String s) {
        return quoteEscape(s, "");
    }

    public static String quoteEscape(final String s, final String nullHolder) {
        if (isEmpty(s))
            return nullHolder;
        final char[] chars = s.toCharArray();
        final StringBuilder o = new StringBuilder(s.length());

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\'' && (i == 0 || chars[i - 1] != '\\'))
                o.append('\\');
            o.append(chars[i]);
        }

        return o.toString();
    }

    public static boolean getBoolean(final Object o) {
        if (o == null)
            return false;

        if (o instanceof Boolean)
            return (Boolean) o;

        return notNull(o).equalsIgnoreCase("true") || !notNull(o).equals("0");
    }

    public static long getLong(final Object value, final int radix) {
        try {
            return Long.parseLong(String.valueOf(value), radix);
        } catch (Exception ee) {
            return 0;
        }
    }

    public static long getLong(final Object value) {
        try {
            return Long.decode(String.valueOf(value));
        } catch (Exception e) {
            try {
                return Long.parseLong(value.toString().replaceAll("([^0-9-])", ""));
            } catch (Exception ee) {
                return 0;
            }
        }
    }

    public static int getInt(final Object parameter, final int max, final int min) {
        final int i = getInt(parameter);

        return i > max ? max : Math.max(i, min);
    }

    public static int getInt(final Object parameter) {
        final String param = notNull(parameter);
        try {
            return Integer.decode(param);
        } catch (Exception e) {
            try {
                return Integer.parseInt(param.replaceAll("([^0-9-])", ""));
            } catch (Exception ee) {
                return 0;
            }
        }
    }


    public static String notNull(final Object o, final String def) {
        if (o == null)
            return def == null ? "" : def.trim();

        if (o instanceof String)
            return ((String) o).trim();

        return String.valueOf(o).trim();
    }

    public static String notNull(final Object o) {
        return notNull(o, "");
    }

    public static String md5String(final String value) {
        return byteArrayToHexString(md5(value));
    }

    public static String md5String(final byte[] value) {
        return byteArrayToHexString(md5(value));
    }

    public static byte[] md5(final byte[] value) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value);
            return md.digest();
        } catch (NoSuchAlgorithmException e) { // немыслимое
            return new byte[0];
        }
    }

    public static byte[] md5(final String value) {
        return md5(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String byteArrayToHexString(final byte[] raw) {
        final byte[] HEX_CHAR_TABLE = {
                (byte) '0', (byte) '1', (byte) '2', (byte) '3',
                (byte) '4', (byte) '5', (byte) '6', (byte) '7',
                (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
                (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
        };
        final byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, StandardCharsets.US_ASCII);
    }

    public static String hash256(String data) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            return byteArrayToHexString(md.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(final Object o) {
        if (o == null)
            return true;

        if (o.getClass().isArray())
            return Array.getLength(o) == 0;

        if (o instanceof Collection)
            return ((Collection) o).isEmpty();

        if (o instanceof Map)
            return ((Map) o).isEmpty();

        if (o.getClass().isEnum())
            return false;

        return notNull(o).isEmpty();
    }
}
