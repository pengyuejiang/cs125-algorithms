import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

/**
 * A class to demonstrate some of the most commonly used sorting algorithms.
 * For the purpose of demonstration and simplicity,
 * we shall only implement the algorithms with integer arrays.
 * The demonstration list is: [4, 15, 6, 0, -1, 2, -20, 5].
 */
public class SortingAlgorithms {

    /**
     * Privatize the construtor to prohibit the user from instantiate the class.
     */
    private SortingAlgorithms() {}

    /**
     * Sort the given array and return a new array in ascending order with insertion sort.
     * @param original the original array
     * @return sorted new array
     */
    /*
     * The idea: Expand the ordered sublist at the front once at a time,
     * by checking where to insert a value in the unsorted region
     * 
     * 4        15, 6, 0, -1, 2, -20, 5
     * 4, 15        6, 0, -1, 2, -20, 5
     * 4, 6, 15        0, -1, 2, -20, 5
     * 0, 4, 6, 15        -1, 2, -20, 5
     * -1, 0, 4, 6, 15        2, -20, 5
     * -1, 0, 2, 4, 6, 15        -20, 5
     * -20, -1, 0, 2, 4, 6, 15        5
     * -20, -1, 0, 2, 4, 5, 6, 15        DONE!!!
     */
    public static int[] insertionSort(int[] original) {
        if (original == null || original.length < 2) return original;
        // Make a copy so that the original array will not be damaged
        int[] sorted = Arrays.copyOf(original, original.length);

        // Run the outer loop to go through every element
        for (int i = 1; i < sorted.length; i++) {
            // Gradually expand the ordered sublist at the front
            // once a time
            for (int j = i; j > 0; j--) {
                // If out of place in the order sublist
                if (sorted[j - 1] > sorted[j]) {
                    // Swap
                    int temp = sorted[j - 1];
                    sorted[j - 1] = sorted[j];
                    sorted[j] = temp;
                } else {
                    // Did not swap means correct order
                    // in the ordered sublist, break
                    break;
                }
            }
        }

        return sorted;
    }

    /**
     * Sort the given array and return a new array in ascending order with merge sort.
     * @param original the original array
     * @return new sorted array
     */
    /*
     * The idea: Divide and conquer, then combine ordered parts with auxiliary function
     * 
     * 1. [4, 15, 6, 0, -1, 2, -20, 5]
     * 2. [4, 15, 6, 0], [-1, 2, -20, 5]
     * 3. [4, 15], [6, 0], [-1, 2], [-20, 5]
     * 4. [4], [15], [6], [0], [-1], [2], [-20], [5]
     * 5. [4, 15], [0, 6], [-1, 2], [-20, 5]
     * 6. [0, 4, 6, 15], [-20, -1, 2, 5]
     * 7. [-20, -1, 0, 2, 4, 5, 6, 15]        DONE!!!
     */
    public static int[] mergeSort(int[] original) {
        if (original == null || original.length < 2) return original;
        // This won't damage the original array because they use copies
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
     * Sort the given array and return a new array in ascending order with bubble sort.
     * @param original the original array
     * @return new sorted array
     */
    /*
     * The idea: Confirm the largest element once at a time, like bubble pop up
     * 
     * 4, 15, 6, 0, -1, 2, -20, 5
     * 4, 6, 0, -1, 2, -20, 5        15
     * 4, 0, -1, 2, -20, 5        6, 15
     * 0, -1, 2, -20, 4        5, 6, 15
     * -1, 0, -20, 2        4, 5, 6, 15
     * -1, -20, 0        2, 4, 5, 6, 15
     * -20, -1        0, 2, 4, 5, 6, 15
     * -20        -1, 0, 2, 4, 5, 6, 15
     *       -20, -1, 0, 2, 4, 5, 6, 15        DONE!!!
     */
    public static int[] bubbleSort(int[] original) {
        if (original == null || original.length < 2) return original;
        // Make a copy so that the original array will not be damaged
        int[] sorted = Arrays.copyOf(original, original.length);

        // Run through the outer loop to get the biggest number one at a time
        for (int i = sorted.length - 1; i > 0; i--) {
            // Run through the inner loop
            // to move up the largest number through comparison
            for (int j = 1; j <= i; j++) {
                // If out of place
                if (sorted[j - 1] > sorted[j]) {
                    // Swap
                    int temp = sorted[j - 1];
                    sorted[j - 1] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }

        return sorted;
    }

    /**
     * Wrapper function for quick sort.
     * @param original the original array
     * @return new sorted array
     */
    /*
     * The idea: Divide the problem into smaller parts and finish them
     * Kinda like merge sort, but performance varies
     * 
     * ^ Denotes the chosen pivot
     * A Denotes previous pivot
     * 
     * [4, 15, 6, 0, -1, 2, -20, 5]
     *  ^
     * [-20, 0, -1, 2]    4    [15, 6, 5]
     *  ^                 A     ^
     * -20    [0, -1, 2]    4    [6, 5]    15
     *  A      ^                  ^        A
     * -20    -1    0    2    4    5    6    15
     *              A                   A
     * [-20, -1, 0, 2, 4, 5, 6, 15]        DONE!!!
     */
    public static int[] quickSort(int[] original) {
        if (original == null || original.length < 2) return original;
        // Make a copy so that the original array will not be damaged
        int[] sorted = Arrays.copyOf(original, original.length);
        quickSort(sorted, 0, sorted.length);
        return sorted;
    }

    /**
     * Sort the given range of the array in ascending order with quick sort.
     * @param array the array to sort
     * @param start start index of the sort, inclusive
     * @param end end index of the sort, exclusive
     */
    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            // Find the pivot index to know where to break the problem
            int pivotIndex = partition(array, start, end);
            // Sort the part smaller than the pivot
            quickSort(array, start, pivotIndex);
            // Sort the part larger than the pivot
            quickSort(array, pivotIndex + 1, end);
            // There you go, divide and conquer
        }
        // If start is equal or larger than end, the part contains no element
        // Do nothing then
    }

    /**
     * Auxiliary function for quick sort, partition the array for the given range.
     * THIS WILL ALTER THE ARRAY PASSED.
     * @param array the array to partition for
     * @param start the start index of the partition, inclusive
     * @param end the end index of the partition, exclusive
     * @return index of the pivot
     */
    private static int partition(int[] array, int start, int end) {
        if (array.length < 2 || start >= array.length) return start;
        int pivotIndex = start;
        // We'll use the first element as the pivot
        int pivotValue = array[start];
        // Run through elements in the given range
        for (int i = start + 1; i < end; i++) {
            if (array[i] < pivotValue) {
                // Swap elements smaller than pivotValue to left of the pivot position
                pivotIndex++;
                int temp = array[i];
                array[i] = array[pivotIndex];
                array[pivotIndex] = temp;
            }
        }
        // Swap pivot with the last element of the smaller sublist
        int temp = array[pivotIndex];
        array[pivotIndex] = array[start]; // Same as pivot value
        array[start] = temp;
        return pivotIndex;
    }

    /**
     * Sort the given array and return a new array in ascending order with selection sort.
     * @param original the original array
     * @return new sorted array
     */
    /*
     * The idea: Find a smallest value one at a time and append it
     * to the end of a sorted sublist at the front
     * 
     *       4, 15, 6, 0, -1, 2, -20, 5
     * -20        4, 15, 6, 0, -1, 2, 5
     * -20, -1        4, 15, 6, 0, 2, 5
     * -20, -1, 0        4, 15, 6, 2, 5
     * -20, -1, 0, 2        4, 15, 6, 5
     * -20, -1, 0, 2, 4        15, 6, 5
     * -20, -1, 0, 2, 4, 5        15, 6
     * -20, -1, 0, 2, 4, 5, 6        15
     * -20, -1, 0, 2, 4, 5, 6, 15        DONE!!!
     */
    public static int[] selectionSort(int[] original) {
        if (original == null || original.length < 2) return original;
        // Make a copy so that the original array will not be damaged
        int[] sorted = Arrays.copyOf(original, original.length);

        // Run through each element, confirm a smallest value each time
        for (int i = 0; i < sorted.length; i++) {
            int minIndex = i;
            // Check each element against the smallest value
            for (int j = i; j < sorted.length; j++) {
                // If found smaller value, update its position
                if (sorted[j] < sorted[minIndex]) minIndex = j;
            }
            // Append the smallest value to the ordered sublist
            // through swap the value
            int temp = sorted[minIndex];
            sorted[minIndex] = sorted[i];
            sorted[i] = temp;
        }

        return sorted;
    }

    /**
     * Sort the given array and return a new array in ascending order with sleep sort.
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
        for (int i = 0; i < sorted.length; i++) sorted[i] = list.get(i);

        return sorted;
    }

}