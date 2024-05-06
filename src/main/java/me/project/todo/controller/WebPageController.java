package me.project.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;

@Api(tags = "End Points Web Page")
@Controller
public class WebPageController {

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("nomeDoAtributo", "Valor do Atributo");
        return "index.html"; // Retorna o nome do arquivo HTML sem extensão
    }
}
