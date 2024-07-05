package org.sorting.controllers;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SortingResponseAssembler extends RepresentationModelAssemblerSupport<SortingResponse, SortingResponse> {

    public SortingResponseAssembler() {
        super(SortingController.class, SortingResponse.class);
    }

    @Override
    public SortingResponse toModel(SortingResponse entity) {
        entity.add(linkTo(methodOn(SortingController.class).sortArray("quickSort", null)).withRel("quickSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray("mergeSort", null)).withRel("mergeSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray("bucketSort", null)).withRel("bucketSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray("radixSort", null)).withRel("radixSort"));
        return entity;
    }
}
