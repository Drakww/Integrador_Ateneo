package avance.integrador.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="solicitud")
public class Solicitud {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Integer id_solicitud; // Identificador único, auto-incremental
    private String tipo_solicitud; // Tipo de solicitud ('Matrícula en línea', 'Solicitud de admisión')
    private String estado; // Estado de la solicitud ('Pendiente', 'Aprobada', 'Rechazada')
    private Timestamp fecha_solicitud;
    
    	    // Relación ManyToOne con Alumno
	    @ManyToOne
	    @JoinColumn(name = "id_alumno")
	    private alumno alumno;

	    // Relación ManyToOne con Apoderado
	    @ManyToOne
	    @JoinColumn(name = "id_apoderado")
	    private apoderado apoderado;

		public Integer getId_solicitud() {
			return id_solicitud;
		}

		public void setId_solicitud(Integer id_solicitud) {
			this.id_solicitud = id_solicitud;
		}

		public String getTipo_solicitud() {
			return tipo_solicitud;
		}

		public void setTipo_solicitud(String tipo_solicitud) {
			this.tipo_solicitud = tipo_solicitud;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Timestamp getFecha_solicitud() {
			return fecha_solicitud;
		}

		public void setFecha_solicitud(Timestamp fecha_solicitud) {
			this.fecha_solicitud = fecha_solicitud;
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
