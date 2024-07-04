package org.sorting.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int n = array.length;
        int maxVal = getMaxValue(array);

        List<Integer>[] buckets = new List[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int value : array) {
            int bucketIndex = (value * n) / (maxVal + 1);
            buckets[bucketIndex].add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                array[index++] = value;
            }
        }
    }

    private int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}

