# Majority Element Algorithm

## Overview

This project implements an algorithm to find the **majority element** in an array using the **Boyer-Moore Voting Algorithm**. The algorithm identifies the element that appears more than **n/2** times in an array, where `n` is the size of the array. The algorithm works in **O(n)** time complexity and **O(1)** space complexity.

## Features

- **Efficient Majority Element Search**: Finds the majority element in linear time.
- **Performance Tracking**: Tracks performance metrics like comparisons, swaps, and array accesses.
- **Benchmarking**: Command-line interface (CLI) for testing the algorithm with different input sizes.
- **Unit Testing**: Comprehensive unit tests covering edge cases such as empty arrays, single-element arrays, and arrays without a majority element.

## Requirements

To run this project, you need:

- **Java 8** or higher
- **Maven** for building the project and running tests

## Project Structure
```
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
```

## Installation

1. Clone the repository to your local machine:
   ```
   git clone https://github.com/your-username/majority-element-algorithm.git
   ```
Navigate to the project directory:
```
cd majority-element-algorithm
```
Build the project with Maven:

```
mvn clean install
```
Running the Code
Running the Algorithm
You can run the MajorityElement algorithm using the provided CLI. To do so:

Navigate to the target/classes directory where the compiled .class files are located:

```
cd target/classes
```
Run the BenchmarkRunner class with an input size (e.g., size 1000):

```
java cli.BenchmarkRunner 1000
```
This will run the algorithm with an array of size 1000 and display the execution time and performance metrics.

Running Unit Tests
You can run the JUnit 5 tests using Maven:
```
mvn test
```
This will execute the test cases in MajorityElementTest.java, ensuring that the algorithm handles various edge cases correctly.

Performance Benchmarks
To test the performance of the algorithm across different input sizes, use the following command-line interface (CLI) with different array sizes (e.g., n = 100, 1000, 10000, 100000):
```
java cli.BenchmarkRunner 1000
```
The execution time will be printed, along with performance metrics like the number of comparisons and array accesses.
Performance Metrics
The algorithm tracks the following metrics during execution:
Comparisons: The number of comparisons made between elements.
Swaps: The number of times elements are swapped (if applicable).
Array Accesses: The number of times elements are read or written from the array.
Metrics are printed to the console after each run and are reset for each test.
Code Snippets

MajorityElement.java
```
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
```
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
```
java
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
java
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
License
This project is licensed under the MIT License.

Instructions for Usage
```
Run the Algorithm:
Navigate to the target/classes directory.
Run the benchmark using: java cli.BenchmarkRunner <array size>.
Run Unit Tests:
Execute mvn test to run all unit tests for correctness validation.
```
