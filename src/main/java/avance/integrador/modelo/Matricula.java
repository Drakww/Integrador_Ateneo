package avance.integrador.modelo;

import java.util.Date;

public class Matricula {

	private int id;
	private String sede;
	private String turno;
	private String nivel;
	private String grado;
	private Date fecha_matricula;
	
	private Alumno alumno;
	
	public Matricula() {
		// TODO Auto-generated constructor stub
	}

	public Matricula(int id, String sede, String turno, String nivel, String grado, Date fecha_matricula,
			Alumno alumno) {
		super();
		this.id = id;
		this.sede = sede;
		this.turno = turno;
		this.nivel = nivel;
		this.grado = grado;
		this.fecha_matricula = fecha_matricula;
		this.alumno = alumno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFecha_matricula() {
		return fecha_matricula;
	}

	public void setFecha_matricula(Date fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
}
