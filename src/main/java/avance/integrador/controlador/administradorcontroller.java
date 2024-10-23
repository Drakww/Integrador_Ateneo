package avance.integrador.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class administradorcontroller {

	
	@GetMapping("/admi")
	public String init() {
		return "administrador";
	}
}
