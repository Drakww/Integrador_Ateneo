package avance.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import avance.integrador.modelo.matricula;

public interface matricularepositorio extends JpaRepository<matricula, Integer> {
	@Query("SELECT p FROM matricula p WHERE p.alumno.nombres LIKE %:clave%")
	public List<matricula> findAll(@Param("clave") String clave);



}
