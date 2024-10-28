package avance.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import avance.integrador.modelo.apoderado;

public interface apoderadoRepositorio extends JpaRepository<apoderado, Integer> {
    // Puedes añadir métodos personalizados si es necesario
}
