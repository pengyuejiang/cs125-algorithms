import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

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
        if (original == null || original.length < 2) return original;
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
        if (original == null || original.length < 2) return original;
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

    /**
     * Sort the given array into a new array in ascending order with sleep sort.
     * Of course this is not tought in class, it's just for fun.
     * @param original the original array
     * @return new sorted array
     */
    public static int[] sleepSort(int[] original) {
        if (original == null || original.length < 2) return original;
        int[] sorted = new int[original.length];
        CountDownLatch signal = new CountDownLatch(original.length);
        // To be accessible by all threads
        ArrayList<Integer> list = new ArrayList<>();
        // Check for negative values
        int shift = 0;
        for (int i = 0; i < original.length; i++) {
            shift = original[i] < shift ? original[i] : shift;
        }
        final int SHIFT = shift * -1;
        // Determines how fast your sorting runs,
        // decreasing this number does have a risk
        final int LAG_INDEX = 5;
        // Run a thread for each element
        for (int element : original) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    signal.countDown();
                    try {
                        signal.await();
                        // Go to sleep
                        // When you wake up, you'll be sorted
                        Thread.sleep((element + SHIFT) * LAG_INDEX);
                        list.add(element);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // Main method need to wait for the threads to finish
        int sum = 0;
        for (int i = 0; i < original.length; i++) {
            sum += original[i];
        }
        try {
            Thread.sleep((SHIFT * original.length + sum) * LAG_INDEX);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Turn the list into array
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = list.get(i);
        }
        return sorted;
    }

    public static int[] quickSort(int[] original) {
        if (original == null || original.length < 2) return original;
        int[] sorted = new int[original.length];

        

        return sorted;
    }
}