package tech.nocountry.eco_rent.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.model.TipoBicicleta;
import org.springframework.ui.Model;


@Controller
public class AlquilerBicicletaController {

    @GetMapping("/alquiler-bicicleta")
    public String alquilerBicicleta(Model model){
        model.addAttribute("tiposBicicleta", TipoBicicleta.values());
        model.addAttribute("alquiler", new Alquiler());
        return "alquiler-bicicleta";
    }
    
    @PostMapping("/alquiler-bicicleta")
    public String alquilerBicicletaForm(@Valid Alquiler alquiler, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            model.addAttribute("tiposBicicleta", TipoBicicleta.values());
            model.addAttribute("alquiler", alquiler);
            return "alquiler-bicicleta";
        }
        
        return "redirect:/exito";
    }
}
