package org.sorting.controllers;

import org.sorting.algorithms.SortingAlgorithm;
import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/algorithms")
public class SortingController {

    private Map<String, SortingAlgorithm> algorithmMap = new HashMap<>();
//    private final SortingService sortingService;
@Autowired
private SortingService sortingService;

    public SortingController(SortingService sortingService) {
//        this.sortingService = sortingService;
        // Initialize some algorithms
        algorithmMap.put("quickSort", new SortingAlgorithm("quickSort", "Quick Sort Description"));
        algorithmMap.put("mergeSort", new SortingAlgorithm("mergeSort", "Merge Sort Description"));
        // Add more algorithms as needed
    }
    @GetMapping
    public List<SortingAlgorithm> getAllAlgorithms() {
        return new ArrayList<>(algorithmMap.values());
    }
//    @GetMapping("/hello")
//    public String sayHello() {
////        return new Algorithm("Insertion sort");
//        return "hello";
//    }

    @PostMapping("/sort")
    public ResponseEntity<SortingResponse> sortArray(@RequestBody org.sorting.controllers.SortingRequest request) {
        String algorithm = request.getAlgorithm();
        int[] sortedArray = new int[0];

        switch (algorithm) {
            case "quickSort":
                sortedArray = sortingService.quickSort(request.getArray());
                break;
            case "mergeSort":
                sortedArray = sortingService.mergeSort(request.getArray());
                break;
            case "bucketSort":
                sortedArray = sortingService.bucketSort(request.getArray());
                break;
            case "radixSort":
                sortedArray = sortingService.radixSort(request.getArray());
                break;
            // Add cases for other sorting algorithms here
        }

        return new ResponseEntity<>(new SortingResponse(sortedArray), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<SortingAlgorithm> getAlgorithm(@PathVariable("name") String name) {
        SortingAlgorithm algorithm = algorithmMap.get(name);
        if (algorithm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(algorithm, HttpStatus.OK);
    }


    // Add your RESTful endpoints here

}
