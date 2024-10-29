/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.login;

import avance.integrador.servicio.UsuarioServicio;

/**
 *
 * @author PC
 */
public class LoginServicio{
    private final UsuarioServicio usuarioServicio;

    public LoginServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    public boolean validarCredenciales(String codigo, String password) {
        return usuarioServicio.validarCredenciales(codigo, password);
    }
}
