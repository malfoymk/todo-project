package me.project.todo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseBody
    public String error() {
        return "Error handling";
    }
}
