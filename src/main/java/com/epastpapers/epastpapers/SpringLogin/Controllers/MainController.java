package com.epastpapers.epastpapers.SpringLogin.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }
    
    @GetMapping("/adminPage")
    public String getAdminPage() {
        return "index";
    }

    @GetMapping("/loginAdmin")
    public String getAdminLoginPage() {
        return "loginAdmin";
    }

}
