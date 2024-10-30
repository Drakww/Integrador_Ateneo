package avance.integrador.modelo;

import lombok.Data;

@Data
public class CredencialDTO {

	private alumno alumno;
	private Usuario usuario;
	
	public CredencialDTO(avance.integrador.modelo.alumno alumno, Usuario usuario) {
		super();
		this.alumno = alumno;
		this.usuario = usuario;
	}
	
	
	
}
