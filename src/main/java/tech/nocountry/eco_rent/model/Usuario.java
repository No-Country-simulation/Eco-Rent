package tech.nocountry.eco_rent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "El nombre no puede estar vacío")
  private String nombre;

  @NotEmpty(message = "El apellido no puede estar vacío")
  private String apellido;

  @NotEmpty(message = "El email no puede estar vacío")
  @Email(message = "El email no es válido")
  @Column(unique = true)
  private String email;

  @NotEmpty(message = "El teléfono no puede estar vacío")
  private String telefono;
}
