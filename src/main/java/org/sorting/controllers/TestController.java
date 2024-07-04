//package org.sorting.controllers;
//
//import org.sorting.algorithms.MergeSort;
//import org.sorting.algorithms.QuickSort;
//import org.sorting.algorithms.SortingAlgorithm;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/algorithms")
//public class SortingAlgorithmController {
//
//    private Map<String, SortingAlgorithm> algorithmMap = new HashMap<>();
//
//    public SortingAlgorithmController() {
//        // Initialize some algorithms
//        algorithmMap.put("quickSort", new QuickSort("quickSort", "Quick Sort Description"));
//        algorithmMap.put("mergeSort", new MergeSort("mergeSort", "Merge Sort Description"));
//        // Add more algorithms as needed
//    }
//
//    @GetMapping
//    public List<SortingAlgorithm> getAllAlgorithms() {
//        return new ArrayList<>(algorithmMap.values());
//    }
//
//    @GetMapping("/{name}")
//    public ResponseEntity<SortingAlgorithm> getAlgorithm(@PathVariable String name) {
//        SortingAlgorithm algorithm = algorithmMap.get(name);
//        if (algorithm == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(algorithm, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<SortingAlgorithm> createAlgorithm(@RequestBody SortingAlgorithm algorithm) {
//        if (algorithmMap.containsKey(algorithm.getName())) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        algorithmMap.put(algorithm.getName(), algorithm);
//        return new ResponseEntity<>(algorithm, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{name}")
//    public ResponseEntity<SortingAlgorithm> updateAlgorithm(@PathVariable String name, @RequestBody SortingAlgorithm algorithm) {
//        if (!algorithmMap.containsKey(name)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        algorithmMap.put(name, algorithm);
//        return new ResponseEntity<>(algorithm, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{name}")
//    public ResponseEntity<Void> deleteAlgorithm(@PathVariable String name) {
//        if (!algorithmMap.containsKey(name)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        algorithmMap.remove(name);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
