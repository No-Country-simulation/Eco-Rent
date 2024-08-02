package tech.nocountry.eco_rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.repository.AlquilerRepository;

@Controller
public class EstadoPedidoController {

  private final AlquilerRepository alquilerRepository;

  @Autowired
  public EstadoPedidoController(AlquilerRepository alquilerRepository) {
    this.alquilerRepository = alquilerRepository;
  }

  @GetMapping("/ingreso-pedido")
  public String ingresoPedido() {
    return "ingresoCliente";
  }

  @GetMapping("/estado-pedido")
  public String estadoPedido(@RequestParam("token") String token, Model model) {
    Alquiler alquiler = alquilerRepository.findByToken(token);

    if (alquiler != null) {
      model.addAttribute("valido", true);
      model.addAttribute("nombre", alquiler.getUsuario().getNombre());
      model.addAttribute("apellido", alquiler.getUsuario().getApellido());
      model.addAttribute("email", alquiler.getUsuario().getEmail());
      model.addAttribute("estado", alquiler.getEstado().toString());
    } else {
      model.addAttribute("valido", false);
      model.addAttribute("mensaje", "Token inválido o alquiler no encontrado.");
    }

    return "estado-pedido";
  }
}