package avance.integrador.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Anotación necesaria para que Spring reconozca este controlador
public class apoderadoControlador {
   
   @GetMapping("/apoderado")
   public String apoderadoForm() {
       return "apoderado";  // Retorna la plantilla Thymeleaf (apoderado.html)
   }
}