/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class AdministradorControlador {
	
  
	 // Método para cargar la vista del formulario
	    @GetMapping("/indexSoporte")
	    public String apoderadoForm() {
	
	        return "indexSoporte"; // Cambia el nombre de la plantilla si es necesario
	    }

	   

    
}