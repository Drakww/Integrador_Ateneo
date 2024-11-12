/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Joshep
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credalumno") // Nombre de la tabla en la base de datos
public class credalumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_credalumno; // Identificador único, auto-incremental
    
    private String numero_documento; // Número de documento alumno, clave foránea
    
    private String usuario; // Concepto del pago
    private String contrasena; // Ruta del voucher

    @ManyToOne // Asumiendo que un alumno puede tener muchas credenciales
    @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", insertable = false, updatable = false)
    private alumno alumno; // Relación con el modelo alumno
    

}
