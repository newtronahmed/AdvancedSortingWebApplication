package org.sorting.services;

public interface SortingService {
    int[] quickSort(int[] array);
    int[] mergeSort(int[] array);
    int[] bucketSort(int[] array);
    int[] radixSort(int[] array);
    int[] heapSort(int[] array);
    // Add other sorting algorithms here
}
