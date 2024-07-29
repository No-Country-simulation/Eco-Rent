package tech.nocountry.eco_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nocountry.eco_rent.model.Alquiler;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {}
