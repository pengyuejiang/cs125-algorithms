import java.security.InvalidAlgorithmParameterException;

public class Algorithms {

    /**
     * Privatize the construtor to prohibit the user from instantiate the class.
     */
    private Algorithms() {}

    /**
     * Brute force algorithm to find the greatest common divisor by increasing the number.
     * @param m one integer
     * @param n another integer
     * @return their greatest common divisor
     */
    public static int ascendingBruteForceGcd(int m, int n) {
        int gcd = 1;
        for (int i = 1; i <= n; i++) gcd = m % i == 0 && n % i == 0 ? i : gcd;
        return gcd;
    }

    /**
     * Brute force algorithm to find the greatest common divisor by decreasing the number.
     * @param m one integer
     * @param n another integer that is smaller than the first
     * @return their greatest common divisor
     */
    public static int descendingBruteForceGcd(int m, int n) {
        for (int i = n; i > 0; i--) {
            if (m % i == 0 && n % i == 0) return i;
        }
        return 1;
    }

    /**
     * Iterative Euclidean algorithm to find the greatest common divisor.
     * @param m one integer
     * @param n another integer that is smaller than the first
     * @return
     */
    public static int IterativeEuclideanAlgorithmGcd(int m, int n) {
        int remainder = 1;
        while (remainder != 0) {
            remainder = m % n;
            m = n;
            n = remainder;
        }
        return m;
    }

    public static int recursiveEuclideanAlgorithmGcd(int m, int n) {
        return n == 0 ? m : recursiveEuclideanAlgorithmGcd(n, m % n);
    }

    /**
     * A recursive approach to calculate a given Fibonacci term.
     * Not suggested because it's not efficient and it takes space.
     * Although its structure and implementation is clear.
     * @param n which term in the sequence
     * @return the term in the fibonacci sequence
     * @throws InvalidAlgorithmParameterException
     */
    public static long recursiveFibonacci(int n) throws InvalidAlgorithmParameterException {
        if (n < 1) throw new InvalidAlgorithmParameterException("Fibonacci sequence starts from the first term!");
        return n == 1 || n == 2 ? 1 : recursiveFibonacci(n - 2) + recursiveFibonacci(n - 1);
    }

    /**
     * An iterative approach to calculate a given Fibonacci term.
     * @param n which term in the sequence
     * @return the term in the fibonacci sequence
     * @throws InvalidAlgorithmParameterException
     */
    public static long iterativeFibonacci(int n) throws InvalidAlgorithmParameterException {
        if (n < 1) throw new InvalidAlgorithmParameterException("Fibonacci sequence starts from the first term!");
        if (n <= 2) return 1L;
        long a = 1L, b = 1L;
        long c = a + b;
        for (int i = 3; i < n; i++) {
            // Keep 3 variables to go up the spiral
            a = b;
            b = c;
            c = a + b;
        }
        long result = c;
        return result;
    }

    /**
     * A recursive approach to calculating factorial.
     * Not suggested because large numbers might cause <code>StackOverflowError</code>
     * @param n a positive integer
     * @return input number's factorial
     * @throws InvalidAlgorithmParameterException if input is negative
     */
    public static long recursiveFactorial(int n) throws InvalidAlgorithmParameterException {
        if (n < 0) throw new InvalidAlgorithmParameterException("Factorial cannot be less than 0!");
        return n < 2 ? 1 : n * recursiveFactorial(n - 1);
    }

    /**
     * An iterative approach to calculating factorial.
     * Suggested because it's nice and simple.
     * @param n a positive integer
     * @return input number's factorial
     * @throws InvalidAlgorithmParameterException if input is negative
     */
    public static long iterativeFactorial(int n) throws InvalidAlgorithmParameterException {
        if (n < 0) throw new InvalidAlgorithmParameterException("Factorial cannot be less than 0!");
        long result = 1L;
        for (int i = 0; i <= n; i++) result *= i;
        return result;
    }

}
