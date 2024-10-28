/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.PagoMatricula;
import avance.integrador.modelo.matricula;
import avance.integrador.servicio.IPagoMatriculaServicio;
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
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class AdministradorControlador {
	

	 // Método para cargar la vista del formulario
	    @GetMapping("/indexSoporte")
	    public String apoderadoForm() {	
	        return "indexSoporte"; // Cambia el nombre de la plantilla si es necesario
	    }

	   

}