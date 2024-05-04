package me.project.todo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebPage {
    @GetMapping("/")
    public ModelAndView index(ModelMap model) {
        // Creating a new instance of ModelAndView and specifying the view name
        ModelAndView mv = new ModelAndView("index.html");

        // Adding a model attribute named "nomeDoAtributo" with the value "ToDo-List"
        model.addAttribute("nomeDoAtributo", "ToDo-List");

        // Returning the ModelAndView instance
        return mv;
    }
}