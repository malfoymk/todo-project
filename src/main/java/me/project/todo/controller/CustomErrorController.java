package me.project.todo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;


@Api(tags = "3.End Points Error")
@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    
    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return "error.html"; // Retorna o nome da página HTML de erro
    }
}
