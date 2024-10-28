package avance.integrador.repositorio;

import avance.integrador.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;


public interface solicitudRepositorio extends JpaRepository<Solicitud, Integer> {
    // Puedes añadir métodos personalizados si es necesario
}
