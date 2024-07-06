package org.sorting.services;

import org.sorting.algorithms.*;
import org.springframework.stereotype.Service;

@Service
public class SortingServiceImpl implements SortingService {

    @Override
    public int[] quickSort(int[] array) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        return array;
    }

    @Override
    public int[] mergeSort(int[] array) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array);
        return array;
    }

    @Override
    public int[] bucketSort(int[] array) {
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(array);
        return array;
    }

    @Override
    public int[] radixSort(int[] array) {
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
}
