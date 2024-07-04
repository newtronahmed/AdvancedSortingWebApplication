package org.sorting.controllers;

import org.sorting.models.Algorithm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class SortingController {

    @GetMapping("/hello")
    public String sayHello() {
//        return new Algorithm("Insertion sort");
        return "hello";
    }

    @GetMapping("/hi")
    public String justice(){
        return "justice";
    }


    // Add your RESTful endpoints here

}
