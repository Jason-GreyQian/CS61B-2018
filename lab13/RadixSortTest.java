import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class RadixSortTest {
    @Test
    public void testSortNormal() {
        String[] input = {"apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "mango"};
        String[] expected = {"apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "mango"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "The array should be sorted alphabetically.");
    }

    @Test
    public void testSortEmptyArray() {
        String[] input = {};
        String[] expected = {};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "An empty array should remain empty.");
    }

    @Test
    public void testSortSingleElement() {
        String[] input = {"apple"};
        String[] expected = {"apple"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "An array with one element should remain unchanged.");
    }

    @Test
    public void testSortStringsOfDifferentLengths() {
        String[] input = {"apple", "banana", "ch", "date", "fig", "kiwi", "grape"};
        String[] expected = {"apple", "banana", "ch", "date", "fig", "grape", "kiwi"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "Strings of different lengths should be sorted correctly.");
    }

    @Test
    public void testSortWithEmptyString() {
        String[] input = {"apple", "", "banana", "cherry", "date"};
        String[] expected = {"", "apple", "banana", "cherry", "date"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "Array with an empty string should be sorted correctly.");
    }

    @Test
    public void testSortWithDuplicateStrings() {
        String[] input = {"apple", "banana", "banana", "apple", "date", "date"};
        String[] expected = {"apple", "apple", "banana", "banana", "date", "date"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "Array with duplicate strings should maintain order.");
    }

    @Test
    public void testSortWithAllIdenticalStrings() {
        String[] input = {"apple", "apple", "apple"};
        String[] expected = {"apple", "apple", "apple"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "Array with all identical strings should not change.");
    }

    @Test
    public void testSortWithNullValues() {
        String[] input = {"apple", null, "banana"};
        String[] expected = {null, "apple", "banana"};
        String[] result = RadixSort.sort(input);
        assertArrayEquals(expected, result, "Array with null values should be sorted correctly (null comes first).");
    }
}
