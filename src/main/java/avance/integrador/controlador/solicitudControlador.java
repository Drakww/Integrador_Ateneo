/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Joshep
 */
@Controller  // Anotación necesaria para que Spring reconozca este controlador
public class solicitudControlador {
       @GetMapping("/solicitud")
   public String apoderadoForm() {
       return "solicitud";  // Retorna la plantilla Thymeleaf (apoderado.html)
   }
}
