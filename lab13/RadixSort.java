import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        if (asciis == null || asciis.length <= 1) {
            return asciis;
        }
        String[] sorted = Arrays.copyOf(asciis, asciis.length);
        int R = 0;
        for (String s : asciis) {
            if (s != null) {
                R = Math.max(R, s.length());
            }
        }

        for (int i = R - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int asciisRange = 256;
        int[] counts = new int[asciisRange + 1];
        for (String s : asciis) {
            counts[calIndexValue(s, index)]++;
        }

        int[] position = new int[asciisRange + 1];
        position[0] = 0;
        for (int i = 1; i < counts.length; i++) {
            position[i] = counts[i - 1] + position[i - 1];
        }

        String[] tmp = Arrays.copyOf(asciis, asciis.length);
        for (int i = 0; i < asciis.length; i++) {
            int rangeIdx = calIndexValue(tmp[i], index);
            int idx = position[rangeIdx];
            asciis[idx] = tmp[i];
            position[rangeIdx] += 1;
        }

        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    /**
     * Return the index value of the string s.
     * Mapping a to int(a) + 1
     */
    private static int calIndexValue(String s, int index) {
        if (s == null || index >= s.length()) {
            return 0;
        }
        return (int) s.charAt(index) + 1;
    }
}
