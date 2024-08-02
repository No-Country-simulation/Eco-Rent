package tech.nocountry.eco_rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.model.EstadoAlquiler;
import tech.nocountry.eco_rent.repository.AlquilerRepository;
import tech.nocountry.eco_rent.service.CalcularMontoServicio;

@Controller
public class TokenController {
    private final AlquilerRepository alquilerRepository;
    private final CalcularMontoServicio calculoMontoService;

    @Autowired
    public TokenController(AlquilerRepository alquilerRepository, CalcularMontoServicio calculoMontoService) {
        this.alquilerRepository = alquilerRepository;
        this.calculoMontoService = calculoMontoService;
    }

    @GetMapping("/token")
    public String getTokenPage() {
        return "token";
    }

    @PostMapping("/verificar-token")
    public String verificarToken(@RequestParam("token") String token, Model model) {
        System.out.println("Token recibido: " + token);
        Alquiler alquiler = alquilerRepository.findByToken(token);

        if (alquiler != null && alquiler.getEstado() == EstadoAlquiler.PENDIENTE_PARA_RECOGER) {
            model.addAttribute("valido", true);
            model.addAttribute("nombre", alquiler.getUsuario().getNombre());
            model.addAttribute("apellido", alquiler.getUsuario().getApellido());
            model.addAttribute("email", alquiler.getUsuario().getEmail());
            model.addAttribute("alquilerId", alquiler.getId());
        } else {
            model.addAttribute("valido", false);
            model.addAttribute("mensaje", "Token inválido o alquiler no está pendiente para recoger.");
        }

        return "verificacion-token";
    }




    @PostMapping("/confirmar-pedido")
    public String confirmarPedido(@RequestParam("alquilerId") Long alquilerId, Model model) {
        Alquiler alquiler = alquilerRepository.findById(alquilerId).orElse(null);

        if (alquiler != null && alquiler.getEstado() == EstadoAlquiler.PENDIENTE_PARA_RECOGER) {
            alquiler.setEstado(EstadoAlquiler.EN_USO);
            alquilerRepository.save(alquiler);
            model.addAttribute("valido", true);
            model.addAttribute("monto", calculoMontoService.calcularMonto(alquiler));
        } else {
            model.addAttribute("valido", false);
            model.addAttribute("mensaje", "Alquiler no está pendiente para recoger.");
        }

        return "confirmacion-pedido";
    }
}