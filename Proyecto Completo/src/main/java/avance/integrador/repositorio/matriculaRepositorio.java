package avance.integrador.repositorio;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import avance.integrador.modelo.matricula;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

public interface matriculaRepositorio extends JpaRepository<matricula, Integer> {

    @Query(value = "SELECT DISTINCT m.id_matricula, a.numero_documento, m.sede, m.turno, m.nivel, " +
               "m.grado, m.año_matricula, m.estado " +
               "FROM matricula m " +
               "JOIN alumno a ON m.id_alumno = a.id_alumno " +
               "WHERE m.estado = 'Pendiente'", nativeQuery = true)
    List<Object[]> obtenerMatriculasPendientes();

    @Modifying
    @Transactional
    @Query("UPDATE matricula s SET s.estado = :estado WHERE s.id_matricula = :id")
    void actualizarEstadoMatricula(@Param("id") Integer id, @Param("estado") String estado);
    
    
    
        @Query("SELECT m.id_matricula, a.numero_documento AS dni, m.sede, m.turno, m.nivel, m.grado, m.año_matricula, m.estado " +
           "FROM matricula m " +
           "JOIN m.alumno a " +
           "WHERE (:numeroDocumento IS NULL OR a.numero_documento = :numeroDocumento) " +
           "AND (:nivel IS NULL OR m.nivel = :nivel) " +
           "AND (:grado IS NULL OR m.grado = :grado) " +
           "AND (:estado IS NULL OR m.estado = :estado)")
    List<Object[]> filtrarMatriculas(
        @Param("numeroDocumento") String numeroDocumento,
        @Param("nivel") String nivel,
        @Param("grado") String grado,
        @Param("estado") String estado
    );


}
