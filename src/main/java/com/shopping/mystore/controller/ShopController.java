package com.shopping.mystore.controller;

import com.shopping.mystore.model.Cart;
import com.shopping.mystore.service.CartService;
import com.shopping.mystore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@SessionAttributes("cart")
public class ShopController {

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

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
    public String root(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "index";
    }

    @PostMapping(value = "/checkout")
    public String checkout(Model model, @ModelAttribute("cart") Cart cart, RedirectAttributes attributes, Authentication authentication) {
        attributes.addFlashAttribute("cart", cart);
        cartService.processCart(cart, authentication.getName());
        return "checkout";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/addToCart", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int addToCart(@RequestBody CartItem item, Model model, @ModelAttribute("cart") Cart cart, Authentication authentication) {
        log.info(" Added Item To Cart --> " + item.getItem());
        cart.getProductList().add(item.getItem());
        log.info(" Product Details: " + productService.getProductByCode(item.getItem()));
        log.info(" For : " + authentication.getName() + " Cart :: " + cart.getProductList());
        return cart.getProductList().size();
    }

    @GetMapping(value = "/getCartCount", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int getCartCount(HttpServletRequest request, Model model, @ModelAttribute("cart") Cart cart) {
        log.info(" Cart :: " + cart.getProductList());
        Principal principal = request.getUserPrincipal();
        log.info(" Checking for existing order of :: " + principal.getName());
        cart = cartService.getPendingOrders(principal.getName());
        return cart.getProductList().size();
    }

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }
}

class CartItem {
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}