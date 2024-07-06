package org.sorting.requests;

public class SortingRequest {
    private int[] array;

    public SortingRequest() {}

    public SortingRequest(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

}
