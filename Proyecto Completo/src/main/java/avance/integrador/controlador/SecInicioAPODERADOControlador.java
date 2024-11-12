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
@Controller
public class SecInicioAPODERADOControlador {
       // Método para cargar la vista del formulario
	    @GetMapping("/SecInicioAPODERADO")
	    public String apoderadoForm() {
	
	        return "SecInicioAPODERADO"; // Cambia el nombre de la plantilla si es necesario
	    }
}
