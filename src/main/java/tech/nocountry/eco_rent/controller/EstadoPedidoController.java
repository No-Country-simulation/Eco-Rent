package tech.nocountry.eco_rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstadoPedidoController {
    
    @GetMapping("/estado-pedido")
    public String EstadoPedido() {
        return "estado-pedido";
    }
}
