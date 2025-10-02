# Majority Element Algorithm

## Overview

This project implements an algorithm to find the **majority element** in an array. The **Boyer-Moore Voting Algorithm** is used to efficiently find the element that appears more than **n/2** times in an array, where `n` is the size of the array. The algorithm works in **O(n)** time complexity and **O(1)** space complexity.

## Features

- **Efficient Majority Element Search**: Finds the majority element in linear time.
- **Metrics Tracking**: Tracks performance metrics like comparisons, swaps, and array accesses.
- **Benchmarking**: Command-line interface (CLI) for testing the algorithm with different input sizes.
- **Unit Testing**: Comprehensive unit tests covering edge cases such as empty arrays, single-element arrays, and arrays without a majority element.

## Project Structure

assignment2-majority-element/
├── src/main/java/
│ ├── algorithms/MajorityElement.java # Main algorithm implementation
│ ├── metrics/PerformanceTracker.java # Tracks performance metrics
│ └── cli/BenchmarkRunner.java # Command-line interface for benchmarking
├── src/test/java/
│ └── algorithms/MajorityElementTest.java # Unit tests for the algorithm
├── docs/
│ ├── analysis-report.pdf # Detailed analysis report (PDF)
│ └── performance-plots/ # Folder for performance plot images
├── README.md # Project documentation
└── pom.xml # Maven project configuration

MajorityElement.java 
``` java
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
```

PerformanceTracker.java
``` java
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
``` 
BenchmarkRunner.java
``` java
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
```

MajorityElementTest.java (JUnit 5 Tests)
```
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
```


### **Explanation of Sections in the README:**

1. **Overview**: Provides a brief description of what the algorithm does and how it works.
2. **Features**: Lists key features such as efficiency, metrics tracking, and testing.
3. **Requirements**: Specifies the necessary software to run the project (Java and Maven).
4. **Project Structure**: Gives an overview of the folder structure within the repository.
5. **Installation**: Steps to clone the repository and build the project.
6. **Running the Code**: Instructions to run the algorithm via CLI and to execute unit tests.
7. **Performance Benchmarks**: Describes how to test the performance of the algorithm with different array sizes.
8. **Performance Metrics**: Explains which metrics are collected during execution (comparisons, swaps, etc.).
9. **Analysis Report**: Mentions the availability of a detailed analysis report.
10. **Conclusion**: Summarizes the efficiency and correctness of the algorithm.
11. **License**: Specifies that the project is licensed under the MIT License (you can change this if needed).
