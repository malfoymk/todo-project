package me.project.todo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Aqui você pode personalizar a página de erro 404 ou redirecionar para uma página específica
        return "error"; // Por exemplo, "error" pode ser o nome de uma página HTML em seus recursos estáticos
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    }
