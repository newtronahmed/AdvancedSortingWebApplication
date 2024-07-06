package org.sorting.controllers;

import org.sorting.algorithms.SortingAlgorithm;
import org.sorting.requests.SortingRequest;
import org.sorting.responses.SortingResponse;
import org.sorting.responses.SortingResponseAssembler;
import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SortingController {

    private final Map<String, SortingAlgorithm> algorithmMap;
    private final SortingService sortingService;
    private final SortingResponseAssembler assembler;

    @Autowired
    public SortingController(Map<String, SortingAlgorithm> algorithmMap, SortingService sortingService, SortingResponseAssembler assembler) {
        this.algorithmMap = algorithmMap;
        this.sortingService = sortingService;
        this.assembler = assembler;
    }

    @GetMapping
    public List<SortingAlgorithm> getAllAlgorithms() {
        return List.copyOf(algorithmMap.values());
    }

    @PostMapping("/sort/{name}")
    public ResponseEntity<SortingResponse> sortArray(@PathVariable("name") String name, @RequestBody SortingRequest request) {
        String algorithm = name;
        if (!isValidAlgorithm(algorithm)) {
            return badRequestResponse("Invalid algorithm: " + algorithm);
        }

        int[] array = request.getArray();
        if (array == null || array.length == 0) {
            return badRequestResponse("Array to sort is null or empty");
        }

        try {
            int[] sortedArray = performSorting(algorithm, array);
            SortingResponse response = new SortingResponse(sortedArray, algorithm);
            return ResponseEntity.ok(assembler.toModel(response));
        } catch (Exception e) {
            return internalServerErrorResponse("Internal server error");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<SortingAlgorithm> getAlgorithm(@PathVariable("name") String name) {
        SortingAlgorithm algorithm = algorithmMap.get(name);
        if (algorithm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(algorithm, HttpStatus.OK);
    }

    private boolean isValidAlgorithm(String algorithm) {
        return algorithmMap.containsKey(algorithm);
    }

    private int[] performSorting(String algorithm, int[] array) {
        return switch (algorithm) {
            case "quickSort" -> sortingService.quickSort(array);
            case "mergeSort" -> sortingService.mergeSort(array);
            case "bucketSort" -> sortingService.bucketSort(array);
            case "radixSort" -> sortingService.radixSort(array);
            case "heapSort" -> sortingService.heapSort(array);
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        };
    }

    private ResponseEntity<SortingResponse> badRequestResponse(String message) {
        return ResponseEntity.badRequest().body(assembler.toModel(new SortingResponse(message)));
    }

    private ResponseEntity<SortingResponse> internalServerErrorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(assembler.toModel(new SortingResponse(message)));
    }
}
