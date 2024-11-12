package avance.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import avance.integrador.modelo.apoderado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Joshep
 */

public interface apoderadoRepositorio extends JpaRepository<apoderado, Integer> {
    
    @Query("SELECT a FROM apoderado a WHERE a.id_apoderado = (SELECT al.apoderado.id_apoderado FROM alumno al WHERE al.numero_documento = :numeroDocumento)")
    apoderado findApoderadoByNumeroDocumentoAlumno(@Param("numeroDocumento") String numeroDocumento);
}
