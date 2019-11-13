import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;

public class TestAlgorithms {
    public static void main(String[] args) {
        try {
            System.out.println(Algorithms.ascendingBruteForceGcd(9, 4));
            System.out.println(Algorithms.descendingBruteForceGcd(132, 77));
            System.out.println(Algorithms.recursiveEuclideanAlgorithmGcd(36, 24));
            System.out.println(Algorithms.IterativeEuclideanAlgorithmGcd(8, 4));
            System.out.println("Algorithms!");
            for (int i = 1; i < 10; i++) {
                System.out.println(Algorithms.iterativeFibonacci(i));
            }
            System.out.println("Sorting algorithms!");
            int[] test = new int[] {4, 15, 6, 0, -1, 2};
            System.out.println(Arrays.toString(SortingAlgorithms.insertionSort(test)));
            System.out.println(Arrays.toString(SortingAlgorithms.mergeSort(test)));
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
