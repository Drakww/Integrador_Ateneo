package avance.integrador.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="matricula")
public class matricula {
	@Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremental
	 private Integer id_matricula; // Identificador único, auto-incremental
	    private String sede; // Sede educativa
	    private String turno; // Turno de matrícula
	    private String nivel; // Nivel educativo
	    private String grado; // Grado
	    private Year año_matricula; // Año de matrícula
	    private String estado; // Estado de la matrí
	    
	    // Relación ManyToOne con Alumno
	    @ManyToOne
	    @JoinColumn(name = "id_alumno")
	    private alumno alumno;

	    // Relación ManyToOne con Apoderado
	    @ManyToOne
	    @JoinColumn(name = "id_apoderado")
	    private apoderado apoderado;

		public Integer getId_matricula() {
			return id_matricula;
		}

		public void setId_matricula(Integer id_matricula) {
			this.id_matricula = id_matricula;
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

		public alumno getAlumno() {
			return alumno;
		}

		public void setAlumno(alumno alumno) {
			this.alumno = alumno;
		}

		public apoderado getApoderado() {
			return apoderado;
		}

		public void setApoderado(apoderado apoderado) {
			this.apoderado = apoderado;
		}
   
}
