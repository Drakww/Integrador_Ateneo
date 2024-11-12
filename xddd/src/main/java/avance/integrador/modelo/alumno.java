package avance.integrador.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="alumno")
public class alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Integer id_alumno;

    private String tipo_documento;
    private String numero_documento;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String fecha_nacimiento; // Podrías cambiar a Date o LocalDate
    private String sexo;
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_apoderado") 
    private apoderado apoderado;

@OneToMany(mappedBy = "alumno")
private List<matricula> matriculas;

@OneToMany(mappedBy = "alumno")
private List<Solicitud> solicitudes;

@OneToMany(mappedBy = "alumno")
private List<pagomatricula> pagomatriculas;
    
}
