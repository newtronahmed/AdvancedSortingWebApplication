package org.sorting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/api")
public class PagesController {

    @GetMapping("/algorithmDetails")
    public String sayHello() {
//        return new Algorithm("Insertion sort");
        return "algorithmDetails";
    }




}
