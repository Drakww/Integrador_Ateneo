/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.repositorio;

import avance.integrador.modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
   Optional<Usuario> findByCodigo(String codigo);
}
