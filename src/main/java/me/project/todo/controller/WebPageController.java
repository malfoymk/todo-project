package me.project.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.annotations.Api;

    @Api(tags = "2.End Points Web Page")
    @Controller
    public class WebPageController {

        @GetMapping("/home")
        public String index(Model model) {
            model.addAttribute("nomeDoAtributo", "Valor do Atributo");
            return "index.html"; // Retorna o nome do arquivo HTML sem extensão
        }
        @GetMapping("/login")
        public String login(Model model) {
            model.addAttribute("nomeDoAtributo", "Valor do Atributo");
            return "login.html"; // Retorna o nome do arquivo HTML sem extensão
        }
        @PostMapping("/sign-up")
        public String register(Model model) {
            model.addAttribute("nomeDoAtributo", "Valor do Atributo");
            return "register.html"; // Retorna o nome do arquivo HTML sem extensão
        }
    }
