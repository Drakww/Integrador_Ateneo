package avance.integrador.modelo;

public class CredencialDTO {

	private alumno alumno;
	private Usuario usuario;
	
	public CredencialDTO(avance.integrador.modelo.alumno alumno, Usuario usuario) {
		super();
		this.alumno = alumno;
		this.usuario = usuario;
	}
	public alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(alumno alumno) {
		this.alumno = alumno;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
