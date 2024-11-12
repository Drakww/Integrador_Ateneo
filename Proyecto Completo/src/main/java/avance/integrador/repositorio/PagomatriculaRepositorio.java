/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.repositorio;

import avance.integrador.modelo.pagomatricula;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Joshep
 */
public interface PagomatriculaRepositorio extends JpaRepository<pagomatricula, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE pagomatricula p SET p.voucher_path = :voucherPath WHERE p.numero_documento = :numeroDocumento")
    void actualizarVoucherPath(@Param("voucherPath") String voucherPath, @Param("numeroDocumento") String numeroDocumento);
    
    
    
    
    @Query(value = "SELECT p.id_pago, p.concepto, p.numero_documento, p.monto_final, " +
                   "p.acuenta, p.deuda, p.fecha_pago, p.voucher_path, p.estado " +
                   "FROM PagoMatricula p " +
                   "WHERE p.estado = 'Pendiente'", nativeQuery = true)
    List<Object[]> obtenerPagosPendientes();



@Modifying
@Transactional
@Query("UPDATE pagomatricula p SET p.estado = :estado, p.acuenta = :acuenta, p.deuda = :deuda WHERE p.id_pago = :idPago")
void actualizarPago(@Param("idPago") Integer idPago, 
                    @Param("estado") String estado, 
                    @Param("acuenta") BigDecimal acuenta, 
                    @Param("deuda") BigDecimal deuda);
    
    
    
    @Query("SELECT " +
    "    p.id_pago, " +
    "    p.concepto, " +
    "    p.numero_documento AS dni, " +
    "    p.monto_final, " +
    "    p.acuenta, " +
    "    p.deuda, " +
    "    p.fecha_pago, " +
    "    p.voucher_path, " +
    "    p.estado AS estado_pago, " +
    "    m.nivel, " +
    "    m.grado " +
    "FROM " +
    "    pagomatricula p " +
    "JOIN " +
    "    p.alumno a " +
    "JOIN " +
    "    a.matriculas m " + // usar a.matriculas si es una lista
    "WHERE " +
    "    (:numeroDocumento IS NULL OR p.numero_documento = :numeroDocumento) " +
    "    AND (:estado IS NULL OR p.estado = :estado) " +
    "    AND (:nivel IS NULL OR m.nivel = :nivel) " +
    "    AND (:grado IS NULL OR m.grado = :grado)")
    List<Object[]> filtrarSolicitudes(
        @Param("numeroDocumento") String numeroDocumento,
        @Param("nivel") String nivel,
        @Param("grado") String grado,
        @Param("estado") String estado
    );

    
    
  


}

