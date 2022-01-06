package com.loja.loja_oak.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LojaController {

    @GetMapping("")
    public String showHomePage(){
        return "/index";
    }
}
