package avance.integrador.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avance.integrador.modelo.alumno;
import avance.integrador.repositorio.alumnoRepositorio;

@Service
public class alumnoservice {
	@Autowired
	private alumnoRepositorio alrepo;

	public List<alumno> obteneralumnoapo(){
		return alrepo.findAll();
	}
}
