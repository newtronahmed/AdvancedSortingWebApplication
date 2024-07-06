package org.sorting.responses;
import org.springframework.hateoas.RepresentationModel;

public class SortingResponse extends RepresentationModel<SortingResponse>  {
    private int[] sortedArray;
    private String algorithm;
    private String errorMessage;
    public SortingResponse(String error) {
        this.errorMessage = error;
    }

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
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
