package tech.nocountry.eco_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nocountry.eco_rent.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByEmail(String email);
}
