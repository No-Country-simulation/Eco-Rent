package tech.nocountry.eco_rent.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.nocountry.eco_rent.model.Alquiler;
import tech.nocountry.eco_rent.model.TipoBicicleta;
import org.springframework.ui.Model;
import tech.nocountry.eco_rent.model.Usuario;
import tech.nocountry.eco_rent.repository.AlquilerRepository;
import tech.nocountry.eco_rent.repository.UsuarioRepository;
import tech.nocountry.eco_rent.service.EmailService;
import tech.nocountry.eco_rent.service.GeneradorTokenService;

@Controller
public class AlquilerBicicletaController {

  private final AlquilerRepository alquilerRepository;
  private final UsuarioRepository usuarioRepository;
  private final GeneradorTokenService tokenService;
  private final EmailService emailService;

  @Autowired
  public AlquilerBicicletaController(
          AlquilerRepository alquilerRepository,
          UsuarioRepository usuarioRepository,
          GeneradorTokenService tokenService,
          EmailService emailService) {
    this.alquilerRepository = alquilerRepository;
    this.usuarioRepository = usuarioRepository;
    this.tokenService = tokenService;
    this.emailService = emailService;
  }

  @GetMapping("/alquiler-bicicleta")
  public String alquilerBicicleta(Model model) {
    model.addAttribute("email", new String());
    return "alquiler-bicicleta";
  }

  @PostMapping("/check-email")
  public String checkEmail(@RequestParam("email") String email, Model model) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null) {
      model.addAttribute("alquiler", new Alquiler());
      model.addAttribute("tiposBicicleta", TipoBicicleta.values());
      model.addAttribute("usuario", usuario);
      return "alquiler-bicicleta-form";
    } else {
      model.addAttribute("usuario", new Usuario());
      return "registro-usuario";
    }
  }

  @PostMapping("/registro-usuario")
  public String registroUsuario(@Valid Usuario usuario, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "registro-usuario";
    }
    usuarioRepository.save(usuario);
    model.addAttribute("alquiler", new Alquiler());
    model.addAttribute("tiposBicicleta", TipoBicicleta.values());
    model.addAttribute("usuario", usuario);
    return "alquiler-bicicleta-form";
  }

  @PostMapping("/alquiler-bicicleta")
  public String alquilerBicicletaForm(
          @Valid Alquiler alquiler, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("tiposBicicleta", TipoBicicleta.values());
      model.addAttribute("alquiler", alquiler);
      return "alquiler-bicicleta-form";
    }

    // Fetch the user from the database
    Usuario usuario = usuarioRepository.findById(alquiler.getUsuario().getId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

    // Set the user in the rental
    alquiler.setUsuario(usuario);

    // Generate token
    String token = tokenService.generateToken();
    alquiler.setToken(token);

    // Save the rental
    alquilerRepository.save(alquiler);

    // Send email
    String subject = "Confirmación de Alquiler de Bicicleta";
    String text = "Gracias por alquilar una bicicleta. Su token de confirmación es: " + token;
    emailService.sendEmail(alquiler.getUsuario().getEmail(), subject, text);

    model.addAttribute("token", token);
    return "redirect:/exito";
  }

  @GetMapping("/exito")
  public String exito() {
    return "exito";
  }
}
