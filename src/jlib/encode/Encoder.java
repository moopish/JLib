package jlib.encode;

/**
 * Created by Moop on 2016-01-01.
 *
 */
public interface Encoder {
    byte[] decode(byte[] text);
    byte[] encode(byte[] text);
}
