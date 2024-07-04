package org.sorting.controllers;

public class SortingResponse {
    private int[] sortedArray;

    public SortingResponse() {}

    public SortingResponse(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }
}
