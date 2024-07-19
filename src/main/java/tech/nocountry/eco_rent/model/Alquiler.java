package tech.nocountry.eco_rent.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Alquiler {
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
    private Date fechaRetiro;
    
    @NotNull(message = "La fecha de entrega no puede estar vacía")
    private Date fechaEntrega;
    
    @NotNull(message = "El tipo de bicicleta no puede estar vacío")
    private TipoBicicleta tipoBicicleta;
}
