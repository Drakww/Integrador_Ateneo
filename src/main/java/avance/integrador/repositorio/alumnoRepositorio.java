package avance.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import avance.integrador.modelo.alumno;

public interface alumnoRepositorio extends JpaRepository<alumno,Integer> {

	@Query("SELECT a FROM alumno a WHERE a.numero_documento = :numeroDocumento")
	public alumno findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);
	
}
