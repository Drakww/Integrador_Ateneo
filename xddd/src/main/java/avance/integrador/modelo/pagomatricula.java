package avance.integrador.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagomatricula") // Nombre de la tabla en la base de datos
public class pagomatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago; // Identificador único, auto-incremental
    private String numero_documento; // Número de documento, clave foránea
    private String concepto; // Concepto del pago
    private String voucher_path; // Ruta del voucher
    private String estado; // Estado del pago
    private Timestamp fecha_pago; // Fecha del pago
    private BigDecimal monto_final; // Monto final del pago
    private BigDecimal acuenta; // Acuenta
    private BigDecimal deuda; 
    
    @ManyToOne // Asumiendo que un alumno puede tener muchas credenciales
    @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", insertable = false, updatable = false)
    private alumno alumno; // Relación con el modelo alumno
    

}
