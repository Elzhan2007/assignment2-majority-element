package algorithms;
public class MajorityElement {

    /**
     * Finds the majority element in the array, if any.
     * @param nums the array of integers
     * @return the majority element or Integer.MIN_VALUE if no majority element exists
     */
    public static int majorityElementOrNone(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        
        int candidate = 0, count = 0, occurrences = 0;
        
        // First pass: Find the candidate
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
            PerformanceTracker.incrementComparisons(); // Track comparisons
        }

        // Second pass: Verify the candidate
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate) occurrences++;
            PerformanceTracker.incrementArrayAccesses(); // Track array accesses
        }

        if (occurrences > nums.length / 2) {
            return candidate;
        } else {
            return Integer.MIN_VALUE;  // No majority element
        }
    }
}