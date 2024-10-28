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
    
    // Getters y Setters

    public Integer getId_apoderado() {
        return id_apoderado;
    }

    public void setId_apoderado(Integer id_apoderado) {
        this.id_apoderado = id_apoderado;
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

    public List<alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
}
