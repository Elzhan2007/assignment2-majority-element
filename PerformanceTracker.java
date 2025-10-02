package metrics;

public class PerformanceTracker {
    public static int comparisons = 0;
    public static int swaps = 0;
    public static int arrayAccesses = 0;

    // Increment comparison count
    public static void incrementComparisons() {
        comparisons++;
    }

    // Increment swap count (if applicable)
    public static void incrementSwaps() {
        swaps++;
    }

    // Increment array access count
    public static void incrementArrayAccesses() {
        arrayAccesses++;
    }

    // Print performance metrics
    public static void printMetrics() {
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Array accesses: " + arrayAccesses);
    }

    // Reset metrics after each run
    public static void resetMetrics() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
    }
}