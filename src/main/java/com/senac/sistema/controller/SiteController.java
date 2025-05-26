
package com.senac.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    
     @GetMapping("/")
        public String inicio(){
            return "index";
    }
        
    @GetMapping("/login")
        public String login() {
            return "login"; // Mostra login.html
    }
}
