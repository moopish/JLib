package jlib.math;

/**
 * Created by Moop on 2015-12-31.
 *
 * TODO comment, untested, no testing done at all
 */
public class ComplexNumber implements Comparable<ComplexNumber> {

    // complex value of form 'a + bi'
    private final Fraction a;
    private final Fraction b;

    public ComplexNumber(int a, int b) {
        this.a = new Fraction(a, 1);
        this.b = new Fraction(b, 1);
    }

    public ComplexNumber(Fraction a, Fraction b) {
        this.a = a;
        this.b = b;
    }

    public ComplexNumber add(ComplexNumber cplx) {
        return (new ComplexNumber(a.add(cplx.a), b.add(cplx.b)));
    }

    @Override
    @SuppressWarnings("all")
    public int compareTo(ComplexNumber o) {
        if (a.subtract(o.a).equals(0))
            return (b.compareTo(o.b));
        return (a.compareTo(o.a));
    }

    public ComplexNumber conjugate() {
        return (new ComplexNumber(a, b.negate()));
    }

    public ComplexNumber divide(ComplexNumber cplx) {
        Fraction denom = cplx.a.power(2).add(cplx.b.power(2));
        Fraction new_a = a.multiply(cplx.a).add(b.multiply(cplx.b));
        Fraction new_b = b.multiply(cplx.a).subtract(a.multiply(cplx.b));
        return (new ComplexNumber(new_a.divide(denom), new_b.divide(denom)));
    }

    // TODO magnitude?

    public ComplexNumber multiply(ComplexNumber cplx) {
        Fraction new_a = a.multiply(cplx.a).subtract(b.multiply(cplx.b));
        Fraction new_b = b.multiply(cplx.a).add(a.multiply(cplx.b));
        return (new ComplexNumber(new_a, new_b));
    }

    public ComplexNumber reciprocal() {
        ComplexNumber conj = conjugate();
        return (conj.divide(this.multiply(conj)));
    }

    //TODO square roots?

    public ComplexNumber subtract(ComplexNumber cplx) {
        return (new ComplexNumber(a.subtract(cplx.a), b.subtract(cplx.b)));
    }

}
