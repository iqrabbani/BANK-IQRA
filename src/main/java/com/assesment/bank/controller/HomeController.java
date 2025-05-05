package com.assesment.bank.controller;

import com.assesment.bank.service.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController{
    private final NasabahService service;

    @Autowired
    public HomeController(NasabahService service){
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("menu", "Nasabah");
        model.addAttribute("grid", service.getAll());
        return "home/home";
    }
}

