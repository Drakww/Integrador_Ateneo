package avance.integrador.modelo;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apoderado")  // Mapeo a la tabla 'apoderado' en la base de datos
public class apoderado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremental
    private Integer id_apoderado;
    
    private String tipo_documento;
    private String numero_documento;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String telefono_movil;
    private String correo;
    private String direccion;

    @OneToMany(mappedBy = "apoderado", cascade = CascadeType.ALL)
    private List<alumno> alumnos;

    @OneToMany(mappedBy = "apoderado", cascade = CascadeType.ALL)
    private List<matricula> matriculas;


    
}
