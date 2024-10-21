/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.Usuario;
import avance.integrador.servicio.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */

@Controller
public class LoginControlador {
    
    @Autowired
    IUsuarioServicio usuarioServicio;
    
    @PostMapping("/login")
    public String login(@RequestParam String codigo, @RequestParam String password, Model model){
        Optional<Usuario> usuarioOpt = usuarioServicio.buscarUsuario(codigo, password);
        
        if (usuarioOpt.isPresent()) {
            return "/";
        }else{
            model.addAttribute("error", "Código o contraseña incorrecta");
            return "login";
        }
        
    }
    
}
