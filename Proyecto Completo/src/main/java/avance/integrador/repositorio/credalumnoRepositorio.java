/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.repositorio;


import avance.integrador.modelo.credalumno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface credalumnoRepositorio extends JpaRepository<credalumno, Integer> {

    credalumno findByUsuario(String usuario);
    
    

    @Query("SELECT ca.id_credalumno, ca.numero_documento, ca.usuario, ca.contrasena " +
           "FROM credalumno ca " +
           "WHERE ca.numero_documento = :numeroDocumento")  
    List<Object[]> filtrarCredenciales(@Param("numeroDocumento") String numeroDocumento);


}

