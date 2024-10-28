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
public class SecMatriculaSOPORTEControlador {
        @GetMapping("/SecMatriculaSOPORTE")
    public String mostrarMatricula() {
        return "SecMatriculaSOPORTE"; // Retorna la vista desde templates/SecInicioSOPORTE.html
    }
}
