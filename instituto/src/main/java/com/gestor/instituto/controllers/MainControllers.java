package com.gestor.instituto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllers {



    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/acceso-denegado")
    public String accessoDenegado() {
        return "401";
    }


}
