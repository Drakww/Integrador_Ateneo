package avance.integrador.modelo;

import lombok.Data;

@Data
public class SolicitudMatriculaDTO {
    private apoderado apoderado;
    private alumno alumno;
    private matricula matricula;
    private Solicitud solicitud;
    
    
	public apoderado getApoderado() {
		return apoderado;
	}
	public void setApoderado(apoderado apoderado) {
		this.apoderado = apoderado;
	}
	public alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(alumno alumno) {
		this.alumno = alumno;
	}
	public matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(matricula matricula) {
		this.matricula = matricula;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
    
    
}
