package avance.integrador.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Anotación necesaria para que Spring reconozca este controlador
public class LoaderControlador {
   
   @GetMapping("/loader")
   public String apoderadoForm() {
       return "loader";  // Retorna la plantilla Thymeleaf (apoderado.html)
   }
}