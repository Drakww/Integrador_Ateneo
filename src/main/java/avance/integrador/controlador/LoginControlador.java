/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.servicio.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlador {

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String loginForm() {
        return "login";  // El nombre de la plantilla de Thymeleaf (login.html)
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String codigo, 
                              @RequestParam String password, 
                              Model model) {
        boolean autenticacion = usuarioServicio.validarCredenciales(codigo, password);

        if (autenticacion) {
            return "redirect:/dashboard";  // Redirige a la página de inicio si las credenciales son correctas
        } else {
            model.addAttribute("error", "La contraseña o codigo es incorrecto");
            return "login";  // Devuelve a la página de login si falla
        }
    }
}