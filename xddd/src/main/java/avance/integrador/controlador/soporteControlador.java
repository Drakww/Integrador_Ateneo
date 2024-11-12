/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import avance.integrador.servicio.alumnoservice;

/**
 *
 * @author Joshep
 */
@Controller  // Anotación necesaria para que Spring reconozca este controlador
public class soporteControlador {

	
	@GetMapping("/soporte")
   public String apoderadoForm() {
       return "soporte";  // Retorna la plantilla Thymeleaf (apoderado.html)
   }
    
}
