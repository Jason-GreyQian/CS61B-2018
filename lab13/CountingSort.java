/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            min = min < i ? min : i;
            max = max > i ? max : i;
        }

        if (min >= 0) {
            return naiveCountingSort(arr);
        }

        // create counts array
        /**
         *  index       stand value
         *  0           min
         *  1           min + 1
         *  i - min     i
         *  max - min   max
         */
        int[] counts = new int[max - min + 1];
        for (int i : arr) {
            counts[i - min] += 1;
        }

        // create position array
        int[] position = new int[max - min + 1];
        int startPos = 0;
        for (int i = 0; i < counts.length; i += 1) {
            position[i] = startPos;
            startPos += counts[i];
        }

        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int idx = arr[i] - min;
            int pos = position[idx];
            sorted[pos] = arr[i];
            position[idx] += 1;
        }

        return sorted;
    }

    // a faster version
    public static int[] naiveCountingSortFaster(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        // count array size adjusted for negative numbers
        int[] counts = new int[max - min + 1];
        for (int i : arr) {
            counts[i - min]++;
        }

        // positions array to store the starting position of each value
        int[] positions = new int[counts.length];
        int startPos = 0;
        for (int i = 0; i < counts.length; i++) {
            positions[i] = startPos;
            startPos += counts[i];
        }

        // Now we can directly construct the sorted array
        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i] - min;
            sorted[positions[idx]] = arr[i];
            positions[idx]++;
        }

        return sorted;
    }
}
