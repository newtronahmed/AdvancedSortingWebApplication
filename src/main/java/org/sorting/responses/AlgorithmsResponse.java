package org.sorting.responses;

import org.springframework.hateoas.RepresentationModel;

public class AlgorithmsResponse extends RepresentationModel<AlgorithmsResponse> {
    private String name;

    public AlgorithmsResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
