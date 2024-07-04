package org.sorting.models;

//package org.sorting.models;

import org.springframework.hateoas.RepresentationModel;

//public interface SortingAlgorithm {
//    void sort(int[] array);
//}
//import org.springframework.hateoas.RepresentationModel;
//
//public class SortingAlgorithm extends RepresentationModel<SortingAlgorithm> {
//    private String name;
//    private String description;
//
//    // Getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
//
//
//
public abstract class SortingAlgorithm extends RepresentationModel<SortingAlgorithm> {
    public abstract void sort(int[] array);
    private String name;
    private String description;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}