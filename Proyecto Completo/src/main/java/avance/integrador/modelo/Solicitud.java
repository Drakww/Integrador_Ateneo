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
            
}
