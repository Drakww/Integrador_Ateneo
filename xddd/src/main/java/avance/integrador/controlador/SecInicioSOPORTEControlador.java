package avance.integrador.controlador;

import avance.integrador.servicio.matriculaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecInicioSOPORTEControlador {

    @GetMapping("/SecInicioSOPORTE")
    public String mostrarInicio() {
        return "SecInicioSOPORTE"; // Retorna la vista desde templates/SecInicioSOPORTE.html
    }
    
  
}
