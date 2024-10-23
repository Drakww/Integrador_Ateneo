/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.repositorio;

import avance.integrador.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    Usuario findByCodigoAndPassword(String codigo, String password);
}
