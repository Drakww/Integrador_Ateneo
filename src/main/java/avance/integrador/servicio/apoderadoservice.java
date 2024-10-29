package avance.integrador.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avance.integrador.modelo.apoderado;
import avance.integrador.repositorio.apoderadoRepositorio;
@Service
public class apoderadoservice {
@Autowired
apoderadoRepositorio repo ;


public List<apoderado> obteneralumnoapo(){
	return repo.findAll();
}

public apoderado guardar(apoderado apo) {
	return repo.save(apo);
}

}
