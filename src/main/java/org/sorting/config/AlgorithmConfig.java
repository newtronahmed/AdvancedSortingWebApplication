package org.sorting.config;

import org.sorting.algorithms.SortingAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AlgorithmConfig {

    @Bean
    public Map<String, SortingAlgorithm> algorithmMap() {
        Map<String, SortingAlgorithm> algorithmMap = new HashMap<>();
        algorithmMap.put("quickSort", new SortingAlgorithm("quickSort", "<p><strong>Description:</strong> Quick Sort is a divide-and-conquer algorithm that works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively.</p>"));
        algorithmMap.put("mergeSort", new SortingAlgorithm("mergeSort", "<p><strong>Description:</strong> Merge Sort is a divide-and-conquer algorithm that divides the array into two halves, sorts each half, and then merges the two sorted halves into a single sorted array.</p>"));
        algorithmMap.put("radixSort", new SortingAlgorithm("radixSort", "<p><strong>Description:</strong> Radix Sort is a non-comparative sorting algorithm that sorts integers by processing individual digits. It processes each digit from the least significant to the most significant (or vice versa).</p>"));
        algorithmMap.put("bucketSort", new SortingAlgorithm("bucketSort", "<p><strong>Description:</strong> Bucket Sort is a distribution sorting algorithm that distributes elements into several buckets. Each bucket is then sorted individually, either using a different sorting algorithm or recursively applying the bucket sort.</p>"));
        algorithmMap.put("heapSort", new SortingAlgorithm("heapSort", "<p><strong>Description:</strong> Heap Sort is a comparison-based sorting algorithm that builds a heap from the input data, then repeatedly extracts the maximum element from the heap and reconstructs the heap until all elements are sorted.</p>"));
        return algorithmMap;
    }
}
