import java.util.Arrays;

/**
 * A class to demonstrate some of the most commonly used sorting algorithms.
 * For the purpose of demonstration and simplicity,
 * we shall only implement the algorithms on integer arrays.
 */
public class SortingAlgorithms {

    /**
     * Prohibit the user from instantiate the class.
     */
    private SortingAlgorithms() {}

    /**
     * Sort the given array into a new array in ascending order with insertion sort.
     * @param original the original array
     * @return sorted new array
     */
    public static int[] insertionSort(int[] original) {
        if (original == null || original.length == 0) {
            return original;
        }
        int[] sorted = Arrays.copyOf(original, original.length);

        // Going through every element left to right
        for (int i = 1; i < sorted.length; i++) {
            // Going from right to left to check
            for (int j = i; j > 0; j--) {
                // If out of place
                if (sorted[j - 1] > sorted[j]) {
                    // Swap
                    int temp = sorted[j - 1];
                    sorted[j - 1] = sorted[j];
                    sorted[j] = temp;
                } else {
                    // Did not swap means correct order, break
                    break;
                }
            }
        }

        return sorted;
    }

    /**
     * Sort the given array into a new array in ascending order with merge sort.
     * @param original the original array
     * @return new sorted array
     */
    public static int[] mergeSort(int[] original) {
        if (original == null || original.length < 2) {
            return original;
        }
        // Base case
        if (original.length == 2) {
            return merge(Arrays.copyOfRange(original, 0, 1),
                    Arrays.copyOfRange(original, 1, 2));
        }
        // Recursive step and combine results
        return merge(mergeSort(Arrays.copyOfRange(original, 0, original.length / 2)),
                mergeSort(Arrays.copyOfRange(original, original.length / 2, original.length)));
    }
    
    /**
     * An auxiliary function to merge two ordered arrays.
     * @param first first ordered array
     * @param second second orderedd array
     * @return new sorted array
     */
    private static int[] merge(int[] first, int[] second) {
        if (first == null && second == null) {
            return null;
        } else if (first == null || first.length == 0) {
            return second;
        } else if (second == null || second.length == 0) {
            return first;
        }
        int firstIndex = 0, secondIndex = 0;
        int[] sorted = new int[first.length + second.length];
        for (int i = 0; i < sorted.length; i++) {
            // We don't have to worry about both index going out,
            // because that will be when the loop ends.
            if (firstIndex == first.length) {
                sorted[i] = second[secondIndex++];
            } else if (secondIndex == second.length) {
                sorted[i] = first[firstIndex++];
            } else if (first[firstIndex] < second[secondIndex]) {
                sorted[i] = first[firstIndex++];
            } else {
                sorted[i] = second[secondIndex++];
            }
        }
        return sorted;
    }
}