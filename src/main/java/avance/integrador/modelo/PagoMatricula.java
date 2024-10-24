/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author PC
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "pagomatricula") // Especificamos el nombre correcto de la tabla

public class PagoMatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_pago") // Nombre correcto de la columna
    private Integer id;
    
    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @Column(name = "concepto")
    private String concepto;
    
    @Column(name = "voucher_path")
    private String voucher_path;
    
    @Column(name = "estado") 
    private String estado;
    
    @Column(name = "monto_final")
    private double montoFinal;
    
    @Column(name = "acuenta")
    private double acuenta;
    
    @Column(name = "deuda")
    private double deuda;
    
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;
}
