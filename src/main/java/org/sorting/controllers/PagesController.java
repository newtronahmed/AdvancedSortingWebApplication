package org.sorting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagesController {

    @GetMapping("/algorithms/{name}")
    public String showAlgorithmDetailsPage(@PathVariable("name") String name, Model model) {
      model.addAttribute("name", name);
     return "algorithmDetail";
    }

}
