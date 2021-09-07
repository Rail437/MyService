package com.example.myservice.controller;

import com.example.myservice.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    FindService findService;

    @GetMapping
    public String hello(Model model){
        model.addAttribute("gifurl", findService.getHelloGif());
        return "Hello";
    }

    @GetMapping(params = "find-gif")
    public String findRate(Model model){
        findService.finder();
        model.addAttribute("gifurl", findService.getHelloGif());
        return "Hello";
    }
}
