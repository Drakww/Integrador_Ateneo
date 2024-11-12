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
public class SecReporteSOPORTE {
       @GetMapping("/SecReporteSOPORTE")
    public String mostrarInicio() {
        return "SecReporteSOPORTE"; // Retorna la vista desde templates/SecReporteSOPORTE.html
    }
}
