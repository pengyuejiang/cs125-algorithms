import java.security.InvalidAlgorithmParameterException;

public class Algorithms {

    private Algorithms() {}

    public static int ascendingBruteForceGcd(int m, int n) {
        int gcd = 1;
        for (int i = 1; i <= n; i++) {
            if (m % i == 0 && n % i == 0) gcd = i;
        }
        return gcd;
    }

    public static int descendingBruteForceGcd(int m, int n) {
        for (int i = n; i > 0; i--) {
            if (m % i == 0 && n % i == 0) return i;
        }
        return 1;
    }

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

    public static long recursiveFibonacci(int n) throws InvalidAlgorithmParameterException {
        if (n < 1) throw new InvalidAlgorithmParameterException("Fibonacci sequence starts from the first term!");
        return n == 1 || n == 2 ? 1 : recursiveFibonacci(n - 2) + recursiveFibonacci(n - 1);
    }

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

    public static long recursiveFactorial(int n) throws InvalidAlgorithmParameterException {
        if (n < 0) throw new InvalidAlgorithmParameterException("Factorial cannot be less than 0!");
        return n == 0 || n == 1 ? 1 : n * recursiveFactorial(n - 1);
    }

    public static long iterativeFactorial(int n) throws InvalidAlgorithmParameterException {
        if (n < 0) throw new InvalidAlgorithmParameterException("Factorial cannot be less than 0!");
        long result = 1L;
        for (int i = 0; i <= n; i++) result *= i;
        return result;
    }

}
