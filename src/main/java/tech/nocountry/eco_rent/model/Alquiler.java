package tech.nocountry.eco_rent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotEmpty(message = "El Apellido no puede estar vacío")
    private String apellido;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El email no es válido")
    private String email;
    
    @NotEmpty(message = "El teléfono no puede estar vacío")
    private String telefono;
    
    @NotNull(message = "La fecha de retiro no puede estar vacía")
    private LocalDate fechaRetiro;
    
    @NotNull(message = "La fecha de entrega no puede estar vacía")
    private LocalDate fechaEntrega;

    @NotNull(message = "El tipo de bicicleta no puede estar vacío")
    @Enumerated(EnumType.STRING)
    private TipoBicicleta tipoBicicleta;
}
