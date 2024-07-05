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
        entity.add(linkTo(methodOn(SortingController.class).sortArray(null)).withSelfRel());
        entity.add(linkTo(methodOn(SortingController.class).sortArray(new SortingRequest(null, "quickSort"))).withRel("quickSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray(new SortingRequest(null, "mergeSort"))).withRel("mergeSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray(new SortingRequest(null, "bucketSort"))).withRel("bucketSort"));
        entity.add(linkTo(methodOn(SortingController.class).sortArray(new SortingRequest(null, "radixSort"))).withRel("radixSort"));
        return entity;
    }
}
