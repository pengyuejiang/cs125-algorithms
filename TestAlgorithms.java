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
            int[] test = new int[] {4, 15, 6, 0, -1, 2, -20, 5};
            System.out.println("Insertion Sort: " + Arrays.toString(SortingAlgorithms.insertionSort(test)));
            System.out.println("Merge Sort: " + Arrays.toString(SortingAlgorithms.mergeSort(test)));
            System.out.println("Bubble Sort: " + Arrays.toString(SortingAlgorithms.bubbleSort(test)));
            System.out.println("Sleep Sort: " + Arrays.toString(SortingAlgorithms.sleepSort(test)));
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
