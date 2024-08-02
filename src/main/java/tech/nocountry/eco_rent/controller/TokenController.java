package tech.nocountry.eco_rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.model.EstadoAlquiler;
import tech.nocountry.eco_rent.repository.AlquilerRepository;
import tech.nocountry.eco_rent.service.CalcularMontoServicio;

import java.util.HashMap;
import java.util.Map;

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
    @ResponseBody
    public Map<String, Object> verificarToken(@RequestParam("token") String token) {
        Map<String, Object> response = new HashMap<>();
        Alquiler alquiler = alquilerRepository.findByToken(token);

        if (alquiler != null && alquiler.getEstado() == EstadoAlquiler.PENDIENTE_PARA_RECOGER) {
            response.put("valido", true);
            response.put("nombre", alquiler.getUsuario().getNombre());
            response.put("apellido", alquiler.getUsuario().getApellido());
            response.put("email", alquiler.getUsuario().getEmail());
            response.put("alquilerId", alquiler.getId());
        } else {
            response.put("valido", false);
            response.put("mensaje", "Token inválido o alquiler no está pendiente para recoger.");
        }

        return response;
    }

    @PostMapping("/confirmar-pedido")
    @ResponseBody
    public Map<String, Object> confirmarPedido(@RequestParam("alquilerId") Long alquilerId) {
        Map<String, Object> response = new HashMap<>();
        Alquiler alquiler = alquilerRepository.findById(alquilerId).orElse(null);

        if (alquiler != null && alquiler.getEstado() == EstadoAlquiler.PENDIENTE_PARA_RECOGER) {
            alquiler.setEstado(EstadoAlquiler.EN_USO);
            alquilerRepository.save(alquiler);
            response.put("valido", true);
            response.put("monto", calculoMontoService.calcularMonto(alquiler));
        } else {
            response.put("valido", false);
            response.put("mensaje", "Alquiler no está pendiente para recoger.");
        }

        return response;
    }
}