package org.sorting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/api")
public class PagesController {

    @GetMapping("/algorithmDetails/{name}")
    public String showAlgorithmDetailsPage(@PathVariable String name, Model model) {
      model.addAttribute("name", name);
     return "algorithmDetail"; // The name of your JSP file
    }




}
