/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.matricula;
import avance.integrador.servicio.matriculaexcel;
import avance.integrador.servicio.matriculaservice;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Joshep
 */
@Controller
public class SecSolicitudSOPORTEControlador {
	@Autowired
	private matriculaservice aser;

	// Método para cargar la vista del formulario
	@GetMapping("/SecSolicitudSOPORTE")
	public String apoderadoForm(Model model, @Param("clave") String clave) {
		model.addAttribute("al", aser.obtenermatri(clave));
		return "SecSolicitudSOPORTE"; // Cambia el nombre de la plantilla si es necesario
	}

	@Autowired
	private matriculaexcel excelExportService;

	// Método para descargar el archivo Excel
	@GetMapping("/administrador/export")
	public void exportarExcel(@Param("clave") String clave, HttpServletResponse response) throws IOException {
		List<matricula> users = aser.obtenermatri(clave);
		excelExportService.export(users, response); // Generar el Excel
	}

}
