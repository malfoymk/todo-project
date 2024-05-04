package me.project.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/home")
    public String index() {
        return "index.html"; // Retorna a página index.html quando acessar /home
    }
}
