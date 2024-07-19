package tech.nocountry.eco_rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SobreNosotrosController {
    
    @GetMapping("/sobre-nosotros")
    public String sobreNosotros(){
        return "sobre-nosotros";
    }
}
