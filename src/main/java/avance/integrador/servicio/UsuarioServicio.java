/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.servicio;

import avance.integrador.modelo.Usuario;
import avance.integrador.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }

    @Override
    public boolean validarCredenciales(String codigo, String password) {
        Usuario usuario = usuarioRepositorio.findByCodigoAndPassword(codigo, password);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

}
