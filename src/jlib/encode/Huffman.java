package jlib.encode;

import jlib.util.FrequencyMap;

/**
 * Created by Moop on 2016-01-01.
 *
 */
public class Huffman implements Encoder {

    @Override
    public byte[] decode(byte[] text) {
        return new byte[0];
    }

    @Override
    public byte[] encode(byte[] text) {
        FrequencyMap<Byte> map = new FrequencyMap<>();
        for (byte b : text)
            map.add(b);
        return new byte[0];
    }
}
