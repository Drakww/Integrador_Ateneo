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
   
}
