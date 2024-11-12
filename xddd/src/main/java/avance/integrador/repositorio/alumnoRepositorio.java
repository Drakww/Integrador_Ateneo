package avance.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import avance.integrador.modelo.alumno;

public interface alumnoRepositorio extends JpaRepository<alumno,Integer> {

}
