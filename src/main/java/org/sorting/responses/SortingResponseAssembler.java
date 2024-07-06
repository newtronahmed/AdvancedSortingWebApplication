package org.sorting.responses;
import org.sorting.controllers.SortingController;
import org.sorting.requests.SortingRequest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.Arrays;
import java.util.List;

@Component
public class SortingResponseAssembler implements RepresentationModelAssembler<SortingResponse, SortingResponse> {


    @Override
    public SortingResponse toModel(SortingResponse entity) {

        // Add self link
        entity.add(WebMvcLinkBuilder.linkTo(methodOn(SortingController.class).sortArray(entity.getAlgorithm(), new SortingRequest())).withSelfRel());

        // List of all algorithms
        List<String> algorithms = Arrays.asList("quickSort", "mergeSort", "bucketSort", "radixSort", "heapSort");

        // Add links to other algorithms
        for (String algo : algorithms) {
            if (!algo.equals(entity.getAlgorithm())) {
                entity.add(linkTo(methodOn(SortingController.class).sortArray(algo, new SortingRequest())).withRel(algo));
            }
        }
        return entity;
    }
}

