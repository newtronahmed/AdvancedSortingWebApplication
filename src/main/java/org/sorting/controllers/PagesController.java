package org.sorting.controllers;

import org.sorting.algorithms.SortingAlgorithm;
import org.sorting.services.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PagesController {

    @Autowired
    private AlgorithmService algorithmService; // Assuming you have a service to fetch algorithms.

    // This method will handle the details of the algorithm on the server-side
    @GetMapping("/algorithms/{name}")
    public String showAlgorithmDetailsPage(@PathVariable("name") String name, Model model) {
        SortingAlgorithm algorithm = algorithmService.getAlgorithmByName(name);

        // If the algorithm is not found, handle it
        if (algorithm == null) {
            return "error/404"; // A custom 404 page or error handling
        }

        // Add the algorithm details to the model
        model.addAttribute("algorithm", algorithm);

        // Assuming the algorithm has related algorithms to display
//        List<SortingAlgorithm> relatedAlgorithms = algorithmService.getRelatedAlgorithms(algorithm);
//        model.addAttribute("relatedAlgorithms", relatedAlgorithms);

        // Return the JSP page
        return "algorithmDetail";
    }

}
