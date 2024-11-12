package avance.integrador.repositorio;

import avance.integrador.modelo.Solicitud;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface solicitudRepositorio extends JpaRepository<Solicitud, Integer> {

    @Query(value = "SELECT s.id_solicitud, " +
                   "CONCAT(a.nombres, '<br>', a.apellido_paterno, ' ', a.apellido_materno) AS apoderado, " +
                   "a.numero_documento AS numero_documento_apoderado, " +
                   "CONCAT(al.nombres, '<br>', al.apellido_paterno, ' ', al.apellido_materno) AS alumno, " +
                   "al.numero_documento AS numero_documento_alumno, " +
                   "m.nivel AS nivel, " +
                   "m.grado AS grado, " +
                   "a.telefono_movil AS telefono_movil, " +
                   "s.fecha_solicitud, " +
                   "s.estado " +
                   "FROM solicitud s " +
                   "JOIN apoderado a ON s.id_apoderado = a.id_apoderado " +
                   "JOIN alumno al ON s.id_alumno = al.id_alumno " +
                   "LEFT JOIN matricula m ON al.id_alumno = m.id_alumno " +
                   "WHERE s.estado = 'Pendiente'", nativeQuery = true)
    List<Object[]> obtenerSolicitudesPendientes();

    @Modifying
    @Transactional
    @Query("UPDATE Solicitud s SET s.estado = :estado WHERE s.id_solicitud = :id")
    void actualizarEstadoSolicitud(@Param("id") Integer id, @Param("estado") String estado);



@Query("SELECT s.id_solicitud, " +
       "CONCAT(ap.nombres, '<br>', ap.apellido_paterno, ' ', ap.apellido_materno) AS apoderado, " +
       "ap.numero_documento AS numero_documento_apoderado, " +
       "CONCAT(al.nombres, '<br>', al.apellido_paterno, ' ', al.apellido_materno) AS alumno, " +
       "al.numero_documento AS numero_documento_alumno, " +
       "m.nivel AS nivel, " +
       "m.grado AS grado, " +
       "ap.telefono_movil AS telefono_movil, " +
       "s.fecha_solicitud, " +
       "s.estado " +
       "FROM Solicitud s " +
       "JOIN s.alumno al " +
       "JOIN al.apoderado ap " +
       "LEFT JOIN al.matriculas m " +
       "WHERE (:numeroDocumento IS NULL OR al.numero_documento = :numeroDocumento) " +
       "AND (:nivel IS NULL OR m.nivel = :nivel) " +
       "AND (:grado IS NULL OR m.grado = :grado) " +
       "AND (:estado IS NULL OR s.estado = :estado)")
List<Object[]> filtrarSolicitudes(
    @Param("numeroDocumento") String numeroDocumento,
    @Param("nivel") String nivel,
    @Param("grado") String grado,
    @Param("estado") String estado
);




}
