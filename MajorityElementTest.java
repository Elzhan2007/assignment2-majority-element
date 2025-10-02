package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MajorityElementTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> MajorityElement.majorityElementOrNone(arr));
    }

    @Test
    public void testSingleElement() {
        int[] arr = {5};
        assertEquals(5, MajorityElement.majorityElementOrNone(arr));
    }

    @Test
    public void testWithDuplicates() {
        int[] arr = {3, 3, 4, 2, 3, 3, 3};
        assertEquals(3, MajorityElement.majorityElementOrNone(arr));
    }

    @Test
    public void testNoMajority() {
        int[] arr = {1, 2, 3, 4};
        assertEquals(Integer.MIN_VALUE, MajorityElement.majorityElementOrNone(arr));
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(Integer.MIN_VALUE, MajorityElement.majorityElementOrNone(arr));
    }

    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(Integer.MIN_VALUE, MajorityElement.majorityElementOrNone(arr));
    }
}