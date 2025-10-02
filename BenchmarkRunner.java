package cli;

import algorithms.MajorityElement;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java BenchmarkRunner <array size>");
            return;
        }

        int size = Integer.parseInt(args[0]);
        int[] arr = new Random().ints(size, 1, 100).toArray(); // Generate random array
        
        long startTime = System.nanoTime();
        
        // Run the Majority Element algorithm
        int result = MajorityElement.majorityElementOrNone(arr);
        
        long endTime = System.nanoTime();
        
        System.out.println("Execution Time for n = " + size + ": " + (endTime - startTime) + " ns");
        System.out.println("Result: " + (result == Integer.MIN_VALUE ? "No majority" : result));
        
        PerformanceTracker.printMetrics();
        
        PerformanceTracker.resetMetrics(); // Reset metrics for the next run
    }
}