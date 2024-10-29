package avance.integrador.modelo;

import java.time.Year;

import lombok.Data;

@Data
public class SolicitudMatriculaDTO {

	private apoderado apoderado;
	private alumno alumno;
	private matricula matricula;
	private Solicitud solicitud;

	// Campos de Apoderado
	private String tipo_documento;
	private String numero_documento;
	private String apellido_paterno;
	private String apellido_materno;
	private String nombres;
	private String telefono_movil;
	private String correo;
	private String direccion;

	// Campos de Alumno
	private String tipo_documentos;
	private String numero_documentos;
	private String apellido_paternos;
	private String apellido_maternos;
	private String nombress;
	private String fecha_nacimiento; // Podrías cambiar a Date o LocalDate
	private String sexo;
	private String nacionalidad;

	// Campos de Matricula
	private Year año_matricula;
	private String estado;
	private String sede;
	private String turno;
	private String nivel;
	private String grado;

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

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono_movil() {
		return telefono_movil;
	}

	public void setTelefono_movil(String telefono_movil) {
		this.telefono_movil = telefono_movil;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo_documentos() {
		return tipo_documentos;
	}

	public void setTipo_documentos(String tipo_documentos) {
		this.tipo_documentos = tipo_documentos;
	}

	public String getNumero_documentos() {
		return numero_documentos;
	}

	public void setNumero_documentos(String numero_documentos) {
		this.numero_documentos = numero_documentos;
	}

	public String getApellido_paternos() {
		return apellido_paternos;
	}

	public void setApellido_paternos(String apellido_paternos) {
		this.apellido_paternos = apellido_paternos;
	}

	public String getApellido_maternos() {
		return apellido_maternos;
	}

	public void setApellido_maternos(String apellido_maternos) {
		this.apellido_maternos = apellido_maternos;
	}

	public String getNombress() {
		return nombress;
	}

	public void setNombress(String nombress) {
		this.nombress = nombress;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Year getAño_matricula() {
		return año_matricula;
	}

	public void setAño_matricula(Year año_matricula) {
		this.año_matricula = año_matricula;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

}
