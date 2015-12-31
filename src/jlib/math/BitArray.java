package jlib.math;

/**
 * <p><b>
 *     <tt>BitArray</tt> class
 * </b></p>
 *
 * <p>
 *     Date: Dec. 30, 2015
 * </p>
 *
 * <p>
 *     Holds an array of bits. The array of bits
 * is represented by an array of integers and each
 * bit of the integers (unless excess) are used in
 * the representation of the bit array.
 * </p>
 *
 * <p><b>NOTE: array indices begin at zero like other arrays</b></p>
 *
 * @author Michael van Dyk
 * @version 1.0
 */
public final class BitArray {

    /**
     * Each in represents a bit of the array.
     *
     *   The first int represents bits 0 to 31, second
     * represents 32 to 63, etc.
     */
    private final int[] bits;

    /**
     * The number of bits in the array.
     */
    private final int length;

    /**
     * <p>
     *     Creates an array of bits represented by an
     *    array of integers.
     * </p>
     * <p><b>
     *     To use this class most efficiently, create
     *    with lengths that are multiples of 32 as ints
     *    in java have 32 bits.
     * </b></p>
     * @param length the number of bits in the newly created array
     */
    public BitArray(int length) {
        if (length <= 0)
            throw new RuntimeException();
        bits = new int[(int)Math.ceil(length/(double)Integer.SIZE)];
        this.length = length;
    }

    /**
     *  Sets the value at the given index to what
     * value is given.
     * @param index the index to set the value of
     * @param value the value to set to
     */
    public void assign(int index, boolean value) {
        if (value)
            set(index);
        else
            clear(index);
    }

    /**
     *  Sets the bit at the given index to 0/false.
     * If the index is out of range an exception is thrown.
     * @param index the given index
     */
    public void clear(int index) {
        if (index >= length || index < 0)
            throw new RuntimeException();
        bits[index / Integer.SIZE] = Functions.bit_clr(bits[index / Integer.SIZE], index % Integer.SIZE);
    }

    /**
     *  Get the bit value at the given index.
     * If the index is out of range an exception is thrown.
     * @param index the given index
     * @return the value of the bit in boolean form
     */
    public boolean get(int index) {
        if (index >= length || index < 0)
            throw new RuntimeException();
        return (Functions.bit_get(bits[index / Integer.SIZE], index % Integer.SIZE));
    }

    /**
     * The number of bits stored in the array
     * @return the number of bits in the array
     */
    public int length() {
        return (length);
    }

    /**
     *  Sets the bit at the given index to 1/true
     * @param index the index to set
     */
    public void set(int index) {
        if (index >= length || index < 0)
            throw new RuntimeException();
        bits[index / Integer.SIZE] = Functions.bit_set(bits[index / Integer.SIZE], index % Integer.SIZE);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i=length-1; i>=0; --i)
            sb.append(get(i) ? '1' : '0');
        return (sb.toString());
    }
}
