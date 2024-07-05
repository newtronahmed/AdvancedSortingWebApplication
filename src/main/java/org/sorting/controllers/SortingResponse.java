package org.sorting.controllers;
import org.springframework.hateoas.RepresentationModel;

public class SortingResponse extends RepresentationModel<SortingResponse>  {
    private int[] sortedArray;
    private String algorithm;
    public SortingResponse(String error) {}

    public SortingResponse(int[] sortedArray, String algorithm) {
        this.sortedArray = sortedArray;
        this.algorithm = algorithm;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }
    public String getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
