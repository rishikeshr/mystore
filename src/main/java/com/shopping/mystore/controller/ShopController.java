package com.shopping.mystore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/checkout")
    public String checkout() {
        return "checkout";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/addToCart")
    public String addToCart() {
        log.info(" Added Item To Cart ");
        return "login";
    }

}
