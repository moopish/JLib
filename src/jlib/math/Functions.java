package jlib.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * <p><b>
 *     Math Functions
 * </b></p>
 *
 * <p>
 *     Date: Dec. 27, 2015
 * </p>
 *
 * <p>
 *     Just a simple class that contains many functions
 * that are related to maths. Have used several to
 * complete problems on the Project Euler website. Many
 * functions have versions for primitive and arbitrary
 * numbers.
 * </p>
 *
 * @author Michael van Dyk
 * @version 1.0 {incomplete}
 *
 * TODO NEED TO DO MUCH TESTING
 */
public final class Functions {

    /**
     * BigInteger instance of zero for quick use
     */
    public static final BigInteger ZERO = BigInteger.ZERO;

    /**
     * BigInteger instance of one for quick use
     */
    public static final BigInteger ONE = BigInteger.ONE;

    /**
     * BigInteger instance of two for quick use
     */
    public static final BigInteger TWO = new BigInteger("2");

    /**
     * BigInteger instance of three for quick use
     */
    public static final BigInteger THREE = new BigInteger("3");

    /**
     * BigInteger instance of four for quick use
     */
    public static final BigInteger FOUR = new BigInteger("4");

    /**
     * BigInteger instance of five for quick use
     */
    public static final BigInteger FIVE = new BigInteger("5");

    /**
     * BigInteger instance of ten for quick use
     */
    public static final BigInteger TEN = BigInteger.TEN;

    /**
     * Cannot be initialized, why would you?
     */
    private Functions() {
        throw new RuntimeException();
    }

    /**
     * Checks if a number is a palindrome in base 2.
     * A palindrome if the same both forward and backwards.
     * 1001001 is a binary palindrome.
     * @param n the number to check
     * @return if the number is a palindrome in base 2
     */
    public static boolean binary_palindrome(int n) {
        int top = (int)log2(n) + 1;
        for (int i=0; i<top/2; ++i) {
            if (bit_get(n, top-1-i) != bit_get(n, i))
                return (false);
        }
        return (true);
    }

    //TODO finish
    public static boolean binary_palindrome(BigInteger n) {
        return (false);
    }

    /**
     * Clears (sets to 0) the bit at the specified
     * location
     * @param n the number to modify
     * @param b the bit position
     * @return the modified number
     */
    public static int bit_clr(int n, int b) {
        return (n & ~(1 << b));
    }

    /**
     * Clears (sets to 0) the bit at the specified
     * location
     * @param n the number to modify
     * @param b the bit position
     * @return the modified number
     */
    public static BigInteger bit_clr(BigInteger n, int b) {
        return (n.clearBit(b));
    }

    /**
     * Gets the value of the specified bit
     * @param n the number to get the bit from
     * @param b the bit position to get
     * @return the value of the bit
     */
    public static boolean bit_get(int n, int b) {
        return (((n & (1 << b)) >>> b) == 1);
    }

    /**
     * Gets the value of the specified bit
     * @param n the number to get the bit from
     * @param b the bit position to get
     * @return the value of the bit
     */
    public static boolean bit_get(BigInteger n, int b) {
        return (n.testBit(b));
    }

    /**
     * Sets the bit at the specified location
     * @param n the number to modify
     * @param b the bit position to modify
     * @return the modified number
     */
    public static int bit_set(int n, int b) {
        return (n | (1 << b));
    }

    /**
     * Sets the bit at the specified location
     * @param n the number to modify
     * @param b the bit position to modify
     * @return the modified number
     */
    public static BigInteger bit_set(BigInteger n, int b) {
        return (n.setBit(b));
    }

    /**
     * Converts the number to its bit string form
     * without leading digits
     * @param n the number to convert to string
     * @return a bit string format of the number
     */
    public static String bit_string(int n) {
        if (n == 0)
            return ("0");

        StringBuilder sb = new StringBuilder("");
        int top = (int)log2(n) + 1;

        for (int i=0; i<top; ++i) {
            sb.insert(0, bit_get(n, i));
        }
        return (sb.toString());
    }

    /**
     * Converts the number to its bit string
     * @param n the number to convert to string
     * @return a bit string format of the number
     */
    public static String bit_string(BigInteger n) {
        return (n.toString(2));
    }

    /**
     * Function for 'n choose k', the combinatorics operation
     * @param n the total amount to choose from
     * @param k the number chosen from the available n
     * @return the ways to choose k from n items
     */
    public static int choose(int n, int k) {
        return (fact(n) / (fact(k) * fact(n - k)));
    }

    /**
     * Function for 'n choose k', the combinatorics operation
     * @param n the total amount to choose from
     * @param k the number chosen from the available n
     * @return the ways to choose k from n items
     */
    public static BigInteger choose_big(int n, int k) {
        return (fib_big(n).divide(fact_big(k).multiply(fact_big(n - k))));
    }

    /**
     *   A quick and simple implementation of the 'square and multiply' method
     * to reduce the computation time of (a^b) mod N, where a^b is a to the
     * power of b.
     * @param a the base that is multiplied b times
     * @param b the number of times a is multiplied with itself
     * @param n the modular divisor
     * @return the remainder of the modular division
     */
    public static BigInteger exp_mod(BigInteger a, BigInteger b, BigInteger n)
    {
        BigInteger s = ONE;
        int l = b.bitLength();

        for (int i = l-1; i >= 0; --i) {
            s = s.multiply(s).mod(n);
            if (b.testBit(i)) {
                s = s.multiply(a).mod(n);
            }
        }
        return (s);
    }

    /**
     * <p>
     *   The Euclidean Algorithm for integers,
     * modified the code found at reference, not my own
     * </p>
     * <p>
     *  Can be used to find the greatest common divisor. Can also solve the
     * equation <tt>as + bt = gcd(a, b)</tt>
     * </p>
     * <p>
     *     ref: https://cgi.csc.liv.ac.uk/~martin/teaching/comp202/Java/GCD.html
     * </p>
     *
     * @param a the first value
     * @param b the other value
     * @return an array containing the gcd, the s value of the equation, the t value of the equation
     */
    public static int[] extended_euclidean(int a, int b) {
        int dst[];
        int q;

        if (b == 0) {
            dst = new int[3];
            dst[0] = a;
            dst[1] = 1;
            dst[2] = 0;
        } else {
            q = a / b;
            dst = extended_euclidean(b, a % b);
            int temp = dst[1] - dst[2] * q;
            dst[1] = dst[2];
            dst[2] = temp;
        }

        return (dst);
    }

    /**
     * <p>
     *   The Euclidean Algorithm for BigIntegers,
     * modified the code found at reference, not my own
     * </p>
     * <p>
     *  Can be used to find the greatest common divisor. Can also solve the
     * equation <tt>as + bt = gcd(a, b)</tt>
     * </p>
     * <p>
     *     ref: https://cgi.csc.liv.ac.uk/~martin/teaching/comp202/Java/GCD.html
     * </p>
     *
     * @param a the first value
     * @param b the other value
     * @return an array containing the gcd, the s value of the equation, the t value of the equation
     */
    public static BigInteger[] extended_euclidean(BigInteger a, BigInteger b)
    {
        BigInteger dst[];
        BigInteger q;

        if (b.equals(BigInteger.ZERO)) {
            dst = new BigInteger[3];
            dst[0] = a;
            dst[1] = BigInteger.ONE;
            dst[2] = BigInteger.ZERO;
        } else {
            q = a.divide(b);
            dst = extended_euclidean(b, a.mod(b));
            BigInteger temp = dst[1].subtract(dst[2].multiply(q));
            dst[1] = dst[2];
            dst[2] = temp;
        }

        return (dst);
    }

    /**
     * Simple factorial function, iteratively done
     * @param n the number to get the factorial of
     * @return the factorial of n
     */
    public static int fact(int n) {
        int sum = 1;
        for (int i=2; i<=n; ++i)
            sum *= i;
        return (sum);
    }

    /**
     * Simple factorial function, iteratively done.
     * For BigInteger returns.
     * @param n the number to get the factorial of
     * @return the factorial of n
     */
    public static BigInteger fact_big(int n) {
        BigInteger sum = BigInteger.ONE;
        for (int i=2; i<=n; ++i)
            sum = sum.multiply(new BigInteger("" + i));
        return (sum);
    }

    public static int fib(int n) {
        if (n <=0) {
            return (0);
        } else if (n <= 2) {
            return (1);
        } else {
            int a = 1, b = 1, temp;

            for (int i=3; i<=n; ++i) {
                temp = a;
                a = a + b;
                b = temp;
            }
            return (a);
        }
    }

    public static BigInteger fib_big(int n) {
        if (n <= 0) {
            return (ZERO);
        } else if (n <= 2) {
            return (ONE);
        } else {
            BigInteger a = ONE, b = ONE, temp;

            for (int i=3; i<=n; ++i) {
                temp = a;
                a = a.add(b);
                b = temp;
            }
            return (a);
        }
    }

    public static int gcd(int a, int b) {
        while (a != 0) {
            int temp = b % a;
            b = a;
            a = temp;
        }
        return (b);
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return (a.gcd(b));
    }

    public static List<Integer> generate_primes(int under) {
        return ((new PrimeSet(under)).toList());
    }

    public static int hepta_num (int n) {
        return (n * (5 * n - 3) / 2);
    }

    public static BigInteger hepta_num_big(int n) {
        return (hepta_num_big(new BigInteger("" + n)));
    }

    public static BigInteger hepta_num_big(BigInteger n) {
        return (n.multiply(FIVE.multiply(n).subtract(THREE)).divide(TWO));
    }

    public static int hex_num(int n) {
        return (n * (2 * n - 1));
    }

    public static BigInteger hex_num_big(int n) {
        return (hex_num_big(new BigInteger("" + n)));
    }

    public static BigInteger hex_num_big(BigInteger n) {
        return (n.multiply(TWO.multiply(n).subtract(ONE)));
    }

    public static int bool2int(boolean b) {
        return (b ? 1 : 0);
    }

    public static int lcm(int a, int b) {
        return (a * b / gcd(a, b));
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return (a.multiply(b).divide(a.gcd(b)));
    }

    public static double log(double a, double base) {
        return (Math.log(a)/Math.log(base));
    }

    public static double log2(double a) {
        return (log(a, 2));
    }

    public static int oct_num(int n) {
        return (n * (3 * n - 2));
    }

    public static BigInteger oct_num_big(int n) {
        return (oct_num_big(new BigInteger("" + n)));
    }

    public static BigInteger oct_num_big(BigInteger n) {
        return (n.multiply(n.multiply(THREE).subtract(TWO)));
    }

    public static boolean palindromic(int n) {
        int top = (int)Math.log10(n) + 1;
        int[] dig = new int[top];
        for (int i=0; i<top; ++i) {
            dig[i] = n % 10;
            n /= 10;
        }
        for (int i=0; i<top/2; ++i) {
            if (dig[top-i-1] != dig[i])
                return (false);
        }
        return (true);
    }

    public static int penta_num(int n) {
        return (n * (3 * n - 1) / 2);
    }

    public static BigInteger penta_num_big(int n) {
        return (penta_num_big(new BigInteger("" + n)));
    }

    public static BigInteger penta_num_big(BigInteger n) {
        return (n.multiply(n.multiply(THREE).subtract(ONE)).divide(TWO));
    }

    //TODO better way? does not work
    public static boolean permutation(int a, int b) {
        int top_a = (int)Math.log10(a) + 1;
        if (top_a != (int)Math.log10(b) + 1)
            return (false);
        int[] dig_a = new int[top_a];
        int[] dig_b = new int[top_a];
        for (int i=0; i<top_a; ++i) {
            dig_a[i] = a % 10;
            dig_b[i] = b % 10;
            a /= 10;
            b /= 10;
        }

        for (int i : dig_a) {
            for (int j=0; j<=dig_b.length; ++j) {
                if (j == dig_b.length)
                    return (false);
                if (i == dig_b[j])
                    dig_b[j] = -1;
            }
        }
        return (true);
    }

    /**
     *  Determines if the given number is prime or not.
     * This function should only be used if minimal checks
     * are being made. If multiple checks are being made
     * then it would be better to create a {@link PrimeSet}
     * and used its given methods to check the primality of
     * the given number.
     * @param n the number to determine if prime
     * @return if the number is prime
     */
    public static boolean prime(int n) {
        if (n <= 1) {
            return (false);
        } else if (n == 2 || n == 3 || n == 5 || n == 7) {
            return (true);
        } else if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) {
            return (false);
        } else if (n >= 121) {
            int root = (int)Math.sqrt(n);
            for (int i=11; i<=root; i += 6)
                if (n % i == 0 || n % (i + 2) == 0)
                    return (false);
        }
        return (true);
    }

    //TODO TESTING
    public static double[] quad(double a, double b, double c) {
        double root = Math.pow(b, 2) - 4 * a * c;

        if (root < 0.0)
            throw new RuntimeException();

        root = Math.sqrt(root)/(2 * a);
        double boa = -b/(2*a);
        return (new double[]{boa - root, boa + root});
    }

    /**
     * Squares the number
     * @param n the number to square
     * @return the number square
     */
    public static int sq(int n) {
        return (n * n);
    }

    /**
     * Squares the number
     * @param n the number to square
     * @return the number square
     */
    public static BigInteger sq(BigInteger n) {
        return (n.pow(2));
    }

    /**
     *      A square root function for BigIntegers as one does not exist
     * (at least to my knowledge) within the Java library. It uses
     * Newton's Iteration to estimate the square root to a close integer.
     *
     * References used:
     *     http://mathworld.wolfram.com/NewtonsIteration.html
     *     http://www.java-examples.com/find-square-root-biginteger-example
     *
     * @param n the value to get the square root of
     * @return the square root of the given value
     */
    public static BigInteger sqrt(BigInteger n)
    {
        // Does not try if zero or negative
        if (n.compareTo(ZERO) <= 0)
            return (ZERO);

        //   The difference threshold, if |high - low| is less than
        // end_diff, then close enough
        final BigDecimal end_diff = new BigDecimal("0.000000001");

        //   BigDecimal versions of needed BigInteger values
        final BigDecimal dn = new BigDecimal(n);
        final BigDecimal d2 = new BigDecimal(TWO);

        //   x(k+1) = 1/2(x(k)+(n/x(k)))
        //  gets closer to the square root after each iteration
        BigDecimal x = new BigDecimal(ONE);

        // high and low to determine the threshold difference
        BigDecimal high;
        BigDecimal low;

        // Loops until an acceptable error is reached
        do {
            low = x;
            high = dn.divide(x, 9, BigDecimal.ROUND_HALF_UP);
            x = low.add(high).divide(d2, 9, BigDecimal.ROUND_HALF_UP);
        } while (high.subtract(low).abs().compareTo(end_diff) > 0);

        return (x.toBigInteger());
    }

    /**
     *  Sums the given integer parameters
     * @param ints the values to sum
     * @return the sum
     */
    public static int sum(int... ints) {
        int sum = 0;
        for (int i : ints)
            sum += i;
        return (sum);
    }

    /**
     *  Sums the given long parameters
     * @param longs the values to sum
     * @return the sum
     */
    public static long sum(long... longs) {
        long sum = 0;
        for (long l : longs)
            sum += l;
        return (sum);
    }

    /**
     *  Sums the given float parameters
     * @param floats the values to sum
     * @return the sum
     */
    public static float sum(float... floats) {
        float sum = 0;
        for (float f : floats)
            sum += f;
        return (sum);
    }

    /**
     *  Sums the given double parameters
     * @param doubles the values to sum
     * @return the sum
     */
    public static double sum(double... doubles) {
        double sum = 0;
        for (double d : doubles)
            sum += d;
        return (sum);
    }

    /**
     *  Sums the given BigDecimal parameters
     * @see BigDecimal
     * @param bigDecs the values to sum
     * @return the sum
     */
    public static BigDecimal sum(BigDecimal... bigDecs) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal bd : bigDecs)
            sum = sum.add(bd);
        return (sum);
    }

    /**
     * Sums the parameters that are passed to the function
     * @see BigInteger
     * @param bigInts the BigIntegers to sum
     * @return the sum
     */
    public static BigInteger sum(BigInteger... bigInts) {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger bi : bigInts)
            sum = sum.add(bi);
        return (sum);
    }

    /**
     * Sums the digits of the given number
     * @param n the number to sum the digits of
     * @return the sum of the digits
     */
    public static int sum_digits(int n) {
        int top = (int)(Math.log10(n)) + 1;
        int sum = 0;
        for (int i=0; i<top; ++i) {
            sum += n % 10;
            n /= 10;
        }
        return (sum);
    }

    /**
     * Sums the digits of the given number
     * @param n the number to sum the digits of
     * @return the sum of the digits
     */
    public static BigInteger sum_digits(BigInteger n) {
        BigInteger sum = ZERO;
        BigInteger tmp[];
        while (!n.equals(ZERO)) {
            sum = sum.add((tmp = n.divideAndRemainder(TEN))[1]);
            n = tmp[0];
        }
        return (sum);
    }

    /**
     *  Sums up the multiples of the number under a given limit.
     * (Negative value will give untested results)
     * @param n the number to get multiples of
     * @param limit the max value (multiples must be under this value)
     * @return the sum of the multiples
     */
    public static int sum_multiples(int n, int limit) {
        int max = limit/n;
        limit = max * n;
        return (((limit + n) * max)/2);
    }

    // TODO http://www.geeksforgeeks.org/eulers-totient-function/
    // TODO comment
    public static int totient(int n) {
        int result = n;
        int root = (int)Math.sqrt(n);
        List<Integer> p = generate_primes(root + 1);

        for (Integer pi : p) {
            if (n % pi == 0) {
                while (n % pi == 0)
                    n /= pi;
                result -= result / pi;
            }
        }

        if (n > 1)
            result -= result / n;
        return (result);
    }

    /**
     *  Returns the nth triangle number.
     * @param n the index of the triangle number to get
     * @return the nth triangle number
     */
    public static int tri_num(int n) {
        return (n * (n + 1) / 2);
    }

    /**
     *  Returns a BigInteger of the nth triangle number.
     * @param n the index of the triangle number to get
     * @return the nth triangle number
     */
    public static BigInteger tri_num_big(int n) {
        return (tri_num_big(new BigInteger(n + "")));
    }

    /**
     *  Returns a BigInteger of the nth triangle number.
     * @param n the index of the triangle number to get
     * @return the nth triangle number
     */
    public static BigInteger tri_num_big(BigInteger n) {
        return (n.multiply(n.add(ONE)).divide(TWO));
    }

    /**
     *  A simple function to xor each character of a string with
     * a corresponding character from the given key.
     * @param text the text to xor
     * @param key the key to use
     * @return the xor'd value
     */
    public static String xor_key(String text, String key) {
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<text.length(); ++i) {
            sb.append(Character.toChars(text.charAt(i) ^ key.charAt(i % key.length())));
        }
        return (sb.toString());
    }

    public static void main(String[] args) {

    }

}
