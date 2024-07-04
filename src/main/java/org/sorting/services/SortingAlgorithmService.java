//package org.sorting.services;
//
//package service;
//
//import model.SortingAlgorithm;
//import org.sorting.models.SortingAlgorithm;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class SortAlgorithmService {
//    private static final int PAGE_SIZE = 5; // Number of algorithms per page
//
//    private List<SortingAlgorithm> algorithms;
//
//    public SortAlgorithmService() {
//        // Initialize with some sorting algorithms
//        algorithms = new ArrayList<>();
//        algorithms.add(new SortingAlgorithm("Bubble Sort"));
//        algorithms.add(new SortingAlgorithm("Selection Sort"));
//        algorithms.add(new SortingAlgorithm("Insertion Sort"));
//        algorithms.add(new SortingAlgorithm("Merge Sort"));
//        algorithms.add(new SortingAlgorithm("Quick Sort"));
//        algorithms.add(new SortingAlgorithm("Heap Sort"));
//        algorithms.add(new SortingAlgorithm("Radix Sort"));
//        algorithms.add(new SortingAlgorithm("Counting Sort"));
//    }
//
//    public List<SortingAlgorithm> getAlgorithms(int page) {
//        int startIndex = (page - 1) * PAGE_SIZE;
//        int endIndex = Math.min(startIndex + PAGE_SIZE, algorithms.size());
//        return algorithms.subList(startIndex, endIndex);
//    }
//
//    public int getTotalPages() {
//        return (int) Math.ceil((double) algorithms.size() / PAGE_SIZE);
//    }
//}
//
