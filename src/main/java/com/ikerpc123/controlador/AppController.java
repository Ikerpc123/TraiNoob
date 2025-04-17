package com.ikerpc123.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("/index")
    public String index() {
        return "index";
    }
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }

}
