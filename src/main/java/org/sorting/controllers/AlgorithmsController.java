package org.sorting.controllers;

import org.sorting.algorithms.SortingAlgorithm;
import org.sorting.requests.AddAlgorithmRequest;
import org.sorting.requests.SortingRequest;
import org.sorting.responses.AlgorithmsResponse;
import org.sorting.responses.ErrorResponse;
import org.sorting.responses.SortingResponse;
import org.sorting.responses.SortingResponseAssembler;
import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class AlgorithmsController {

    private final Map<String, SortingAlgorithm> algorithmMap;
    private final SortingService sortingService;
    private final SortingResponseAssembler assembler;

    @Autowired
    public AlgorithmsController(Map<String, SortingAlgorithm> algorithmMap, SortingService sortingService, SortingResponseAssembler assembler) {
        this.algorithmMap = new ConcurrentHashMap<>(algorithmMap);
        this.sortingService = sortingService;
        this.assembler = assembler;
    }
    @GetMapping("/algorithms")
    public CollectionModel<AlgorithmsResponse> getAllAlgorithms() {
        List<String> algorithms = new ArrayList<>(algorithmMap.keySet());
        List<AlgorithmsResponse> algorithmsResponse = new ArrayList<>();

        for (String algorithm : algorithms) {
            AlgorithmsResponse link = new AlgorithmsResponse(algorithm);
            link.add(WebMvcLinkBuilder.linkTo(PagesController.class)
                    .slash("algorithms")
                    .slash(algorithm)
                    .withSelfRel());
            algorithmsResponse.add(link);
        }
        // Add link to add algorithm endpoint
        AlgorithmsResponse addAlgorithmLink = new AlgorithmsResponse("addAlgorithm");
        addAlgorithmLink.add(WebMvcLinkBuilder.linkTo(AlgorithmsController.class)
                .slash("algorithms")
                .withRel("add"));
        algorithmsResponse.add(addAlgorithmLink);

        return CollectionModel.of(algorithmsResponse);
    }
    @PostMapping("/algorithms")
    public ResponseEntity<List<SortingAlgorithm>> addAlgorithm(@RequestBody AddAlgorithmRequest request) {
        String name = request.getName();
        String description = request.getDescription();

        if (name == null || name.isBlank() || description == null || description.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (algorithmMap.containsKey(name)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        SortingAlgorithm newAlgorithm = new SortingAlgorithm(name, description);
        algorithmMap.put(name, newAlgorithm);

        // Construct a new list with all algorithms sorted as desired
        List<SortingAlgorithm> sortedAlgorithms = new ArrayList<>(algorithmMap.values());
        // Append the new algorithm to the end
        sortedAlgorithms.add(newAlgorithm);

        return ResponseEntity.ok(sortedAlgorithms);
    }
    @GetMapping("/{name}")
    public ResponseEntity<SortingAlgorithm> getAlgorithm(@PathVariable("name") String name) {
        SortingAlgorithm algorithm = algorithmMap.get(name);
        if (algorithm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(algorithm, HttpStatus.OK);
    }


    @PostMapping("/sort/{name}")
    public ResponseEntity<?> sortArray(@PathVariable("name") String name, @RequestBody SortingRequest request) {
        if (!isValidAlgorithm(name)) {
            return badRequestResponse("Invalid algorithm: " + name);
        }

        int[] array = request.getArray();
        if (array == null || array.length == 0) {
            return badRequestResponse("Array to sort is null or empty");
        }

        try {
            int[] sortedArray = performSorting(name, array);
            SortingResponse response = new SortingResponse(sortedArray, name);
            return ResponseEntity.ok(assembler.toModel(response));
        } catch (IllegalArgumentException e) {
            return internalServerErrorResponse(e.getMessage());
        }catch (Exception e) {
            return internalServerErrorResponse("Internal server error");

        }
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


private ResponseEntity<ErrorResponse> badRequestResponse(String message) {
    ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}

    private ResponseEntity<ErrorResponse> internalServerErrorResponse(String message) {
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
