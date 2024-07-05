package org.sorting.controllers;

import org.sorting.algorithms.SortingAlgorithm;
import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SortingController {

    private Map<String, SortingAlgorithm> algorithmMap = new HashMap<>();
//    private final SortingService sortingService;
@Autowired
private SortingService sortingService;
private SortingResponseAssembler assembler;

    public SortingController(SortingService sortingService, SortingResponseAssembler assembler) {
        this.assembler = assembler;
        this.sortingService = sortingService;
        initializeAlgorithms();
    }
    @GetMapping
    public List<SortingAlgorithm> getAllAlgorithms() {
        return new ArrayList<>(algorithmMap.values());
    }

    @PostMapping("/sort/{name}")
    public ResponseEntity<SortingResponse> sortArray(@PathVariable("name") String name, @RequestBody SortingRequest request) {
//        String algorithm = request.getAlgorithm();
        String algorithm = name;
        int[] sortedArray;

        try {
            // Check if algorithm is valid
            if (!isValidAlgorithm(algorithm)) {
                return ResponseEntity.badRequest().body(new SortingResponse("Invalid algorithm: " + algorithm));
            }

            // Check if array to sort is provided
            if (request.getArray() == null || request.getArray().length == 0) {
                return ResponseEntity.badRequest().body(new SortingResponse("Array to sort is null or empty"));
            }

            // Perform sorting based on selected algorithm
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

                case "heapSort":
                    sortedArray = sortingService.heapSort(request.getArray());
                    break;
                default:
                    return ResponseEntity.badRequest().body(new SortingResponse("Unsupported algorithm: " + algorithm));
            }

            // Return sorted array
            return ResponseEntity.ok(assembler.toModel(new SortingResponse(sortedArray, algorithm)).getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SortingResponse("Internal server error"));
        }
    }

    private boolean isValidAlgorithm(String algorithm) {
        // Add validation logic for supported algorithms
        return algorithm.equals("quickSort") || algorithm.equals("mergeSort") || algorithm.equals("bucketSort") || algorithm.equals("radixSort");
    }

    @GetMapping("/{name}")
    public ResponseEntity<SortingAlgorithm> getAlgorithm(@PathVariable("name") String name) {
        SortingAlgorithm algorithm = algorithmMap.get(name);
        if (algorithm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(algorithm, HttpStatus.OK);
    }
    private void initializeAlgorithms() {
        algorithmMap.put("quickSort", new SortingAlgorithm("quickSort", "<p><strong>Description:</strong> Quick Sort is a divide-and-conquer algorithm that works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively.</p>"));
        algorithmMap.put("mergeSort", new SortingAlgorithm("mergeSort", "<p><strong>Description:</strong> Merge Sort is a divide-and-conquer algorithm that divides the array into two halves, sorts each half, and then merges the two sorted halves into a single sorted array.</p>"));
        algorithmMap.put("radixSort", new SortingAlgorithm("radixSort", "<p><strong>Description:</strong> Radix Sort is a non-comparative sorting algorithm that sorts integers by processing individual digits. It processes each digit from the least significant to the most significant (or vice versa).</p>"));
        algorithmMap.put("bucketSort", new SortingAlgorithm("bucketSort", "<p><strong>Description:</strong> Bucket Sort is a distribution sorting algorithm that distributes elements into several buckets. Each bucket is then sorted individually, either using a different sorting algorithm or recursively applying the bucket sort.</p>"));
        // Add more algorithms as needed
    }


    // Add your RESTful endpoints here

}
