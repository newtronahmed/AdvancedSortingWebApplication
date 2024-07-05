package org.sorting.controllers;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SortingResponseAssembler implements RepresentationModelAssembler<SortingResponse, EntityModel<SortingResponse>> {

    @Override
    public EntityModel<SortingResponse> toModel(SortingResponse entity) {
        EntityModel<SortingResponse> model = EntityModel.of(entity);
        System.out.println(entity + "entity");
        // Add self link
        model.add(linkTo(methodOn(SortingController.class).sortArray(entity.getAlgorithm(), null)).withSelfRel());

        // List of all algorithms
        List<String> algorithms = Arrays.asList("quickSort", "mergeSort", "bucketSort", "radixSort", "heapSort");

        // Add links to other algorithms
        for (String algo : algorithms) {
            if (!algo.equals(entity.getAlgorithm())) {
                model.add(linkTo(methodOn(SortingController.class).sortArray(algo, null)).withRel(algo));
            }
        }
        return model;
    }
}

