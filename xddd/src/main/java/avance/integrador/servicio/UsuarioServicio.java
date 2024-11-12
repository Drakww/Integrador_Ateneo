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
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public boolean autenticar(String codigo, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByCodigo(codigo);
        return usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password);
    }

}
