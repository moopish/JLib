package jlib.math;

/**
 * Created by Moop on 2015-12-31.
 *
 * TODO comment, much untested
 */
public class Fraction implements Comparable<Fraction> {

    /**
     * The denominator of the fraction
     */
    private final int     denom;

    /**
     * The numerator of the fraction
     */
    private final int     numer;

    /**
     * If the fraction is negative or not
     */
    private final boolean   neg;

    /**
     *  Creates a new fraction with the given
     * numerator and denominator. Determines
     * if the fraction is negative based on
     * the given parameters.
     * @param numer the given numerator
     * @param denom the given denominator
     */
    public Fraction(int numer, int denom) {
        this(Math.abs(numer), Math.abs(denom), (numer < 0 && denom >= 0) || (numer >= 0 && denom < 0));
    }

    /**
     *  Creates a new fraction with the given
     * numerator and denominator. Negativity
     * is given as the third parameter. This
     * constructor is only to be used
     * internally and only non-negative
     * values should be given as the
     * numerator and denominator.
     * @param numer the numerator of the fraction (non-negative)
     * @param denom the denominator of the fraction (non-negative)
     * @param neg if the fraction is negative or not
     */
    protected Fraction(int numer, int denom, boolean neg) {
        this.numer = numer;
        this.denom = denom;
        this.neg = neg;
    }

    /**
     *  Adds two fractions together and returns the result as
     * new fraction.
     * @param o the fraction to add
     * @return the result of the addition
     */
    public Fraction add(Fraction o) {
        int lcm = Functions.lcm(denom, o.denom);
        int new_numer = signed_numer() * lcm / denom + o.signed_numer() * lcm / o.denom;
        return (new Fraction(new_numer, lcm));
    }

    /**
     *  Adds a fraction and an integer and returns the result as
     * new fraction.
     * @param i the integer to add to the fraction
     * @return the result of the addition
     */
    public Fraction add(int i) {
        return (new Fraction(signed_numer() + i * denom, denom));
    }

    @Override
    public final int compareTo(Fraction o) {
        int lcm = Functions.lcm(denom, o.denom);
        return (signed_numer() * lcm / denom - o.signed_numer() * lcm / o.denom);
    }

    public final int compareTo(int i) {
        return (numer - i * denom);
    }

    public final double decimal() {
        return (numer/(double)denom);
    }

    public final int denominator() {
        return (denom);
    }

    /**
     *  Divides the given fraction.
     * @param o the divisor fraction
     * @return the result of the division
     */
    public Fraction divide(Fraction o) {
        return (new Fraction(signed_numer() * o.denom, o.signed_numer() * denom));
    }

    public Fraction divide(int i) {
        return (new Fraction(signed_numer(), denom * i));
    }

    public final boolean equal(int i) {
        return (compareTo(i) == 0);
    }

    /**
     *  Multiplies two fractions together and returns the result as
     * new fraction.
     * @param o the fraction to multiply
     * @return the result of the multiplication
     */
    public Fraction multiply(Fraction o) {
        return (new Fraction(signed_numer() * o.signed_numer(), denom * o.denom));
    }

    public Fraction multiply(int i) {
        return (new Fraction(signed_numer() * i, denom));
    }

    /**
     *  Negates the fraction
     * @return the negated fraction
     */
    public final Fraction negate() {
        return (new Fraction(numer, denom, !neg));
    }

    /**
     *
     * @return
     */
    public final int numerator() {
        return (numer);
    }

    public Fraction power(int i) {
        return (new Fraction((int)Math.pow(signed_numer(), i), (int)Math.pow(denom, i)));
    }

    public final Fraction simplify() {
        int denom = this.denom;
        int numer = this.numer;
        int gcd;

        while ((gcd = Functions.gcd(numer, denom)) != 1) {
            denom /= gcd;
            numer /= gcd;
        }

        return (new Fraction(numer, denom));
    }

    protected final int signed_numer() {
        return (neg ? -numer : numer);
    }

    /**
     *  Subtracts two fractions together and returns the result as
     * new fraction.
     * @param o the fraction to subtract
     * @return the result of the subtraction
     */
    public Fraction subtract(Fraction o) {
        int lcm = Functions.lcm(denom, o.denom);
        int new_numer = signed_numer() * lcm / denom - o.signed_numer() * lcm / o.denom;
        return (new Fraction(new_numer, lcm));
    }

    public Fraction subtract(int i) {
        return (this.add(-i));
    }

    @Override
    public final String toString() {
        return (signed_numer() + "/" + denom);
    }

}
