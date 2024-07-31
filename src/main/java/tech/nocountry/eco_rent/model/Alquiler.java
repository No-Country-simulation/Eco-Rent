package tech.nocountry.eco_rent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Alquiler {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "La fecha de retiro no puede estar vacía")
  @Column(name = "fecha_retiro")
  private LocalDate fechaRetiro;

  @NotNull(message = "La fecha de entrega no puede estar vacía")
  @Column(name = "fecha_entrega")
  private LocalDate fechaEntrega;

  @NotNull(message = "El tipo de bicicleta no puede estar vacío")
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_bicicleta")
  private TipoBicicleta tipoBicicleta;

  @Column(unique = true)
  private String token;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @Column(name = "esta_activa", columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean estaActiva;
}
