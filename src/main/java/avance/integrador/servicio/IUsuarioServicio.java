/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.servicio;

import avance.integrador.modelo.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PC
 */

public interface IUsuarioServicio {
    public List<Usuario> listarUsuario();
    
    public Optional<Usuario> buscarUsuario(String codigo, String password);
    
    public void guardarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Usuario usuario);
}
