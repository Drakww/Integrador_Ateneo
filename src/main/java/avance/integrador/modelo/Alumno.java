package avance.integrador.modelo;

import java.util.List;

public class Alumno {

	private int id;
	private String tipo_documento;
	private String numero_documento;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String celular;
	private String correo;
	private String edad;
	private String direccion;
	

	private Apoderado apoderado;
	
	private List<Matricula> matriculas;

	public Alumno() {
		// TODO Auto-generated constructor stub
	}
	
	public Alumno(int id, String tipo_documento, String numero_documento, String nombre, String apellidoPaterno,
			String apellidoMaterno, String celular, String correo, String edad, String direccion, Apoderado apoderado) {
		super();
		this.id = id;
		this.tipo_documento = tipo_documento;
		this.numero_documento = numero_documento;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.celular = celular;
		this.correo = correo;
		this.edad = edad;
		this.direccion = direccion;
		this.apoderado = apoderado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}
		
}