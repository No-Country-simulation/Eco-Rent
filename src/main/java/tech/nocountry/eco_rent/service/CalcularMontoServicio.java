package tech.nocountry.eco_rent.service;

import org.springframework.stereotype.Service;
import tech.nocountry.eco_rent.model.Alquiler;

@Service
public class CalcularMontoServicio {
    public double calcularMonto(Alquiler alquiler) {
        
        return 100.0; // Ejemplo de monto fijo
    }
}