package org.sorting.services;

import org.sorting.algorithms.*;
import org.springframework.stereotype.Service;

@Service
public class SortingServiceImpl implements SortingService {

    @Override
    public int[] quickSort(int[] array) {
        // Implement Quick Sort logic here
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        return array;
    }

    @Override
    public int[] mergeSort(int[] array) {
        // Implement Merge Sort logic here
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array);
        return array;
    }

    @Override
    public int[] bucketSort(int[] array) {
        // Implement Bucket Sort logic here
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(array);
        return array;
    }

    @Override
    public int[] radixSort(int[] array) {
        // Implement Radix Sort logic here
        RadixSort radixSort = new RadixSort();
        radixSort.sort(array);
        return array;
    }

    @Override
    public int[] heapSort(int[] array) {
        HeapSort heapSort = new HeapSort();
        heapSort.sort(array);
        return array;

    }
    // Implement other sorting algorithms here
}
