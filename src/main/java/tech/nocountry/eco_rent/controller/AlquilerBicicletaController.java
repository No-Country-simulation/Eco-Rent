package tech.nocountry.eco_rent.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.model.TipoBicicleta;
import org.springframework.ui.Model;
import tech.nocountry.eco_rent.repository.AlquilerRepository;
import tech.nocountry.eco_rent.service.EmailService;
import tech.nocountry.eco_rent.service.GeneradorTokenService;

@Controller
public class AlquilerBicicletaController {

  private final AlquilerRepository alquilerRepository;
  private final GeneradorTokenService tokenService;
  private final EmailService emailService;

  @Autowired
  public AlquilerBicicletaController(
      AlquilerRepository alquilerRepository,
      GeneradorTokenService tokenService,
      EmailService emailService) {
    this.alquilerRepository = alquilerRepository;
    this.tokenService = tokenService;
    this.emailService = emailService;
  }

  @PostMapping("/alquiler-bicicleta")
  public String alquilerBicicletaForm(
      @Valid Alquiler alquiler, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("tiposBicicleta", TipoBicicleta.values());
      model.addAttribute("alquiler", alquiler);
      return "alquiler-bicicleta";
    }

    // Save the rental
    alquilerRepository.save(alquiler);

    // Generate token
    String token = tokenService.generateToken();

    // Send email
    String subject = "Confirmación de Alquiler de Bicicleta";
    String text = "Gracias por alquilar una bicicleta. Su token de confirmación es: " + token;
    emailService.sendEmail(alquiler.getEmail(), subject, text);

    model.addAttribute("token", token);
    return "redirect:/exito";
  }

  @GetMapping("/exito")
  public String exito() {
    return "exito";
  }
}
