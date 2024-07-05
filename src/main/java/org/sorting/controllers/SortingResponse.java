package org.sorting.controllers;
import org.springframework.hateoas.RepresentationModel;

public class SortingResponse extends RepresentationModel<SortingResponse>  {
    private int[] sortedArray;

    public SortingResponse(String error) {}

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
