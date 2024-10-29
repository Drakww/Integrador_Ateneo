/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import avance.integrador.modelo.CredencialDTO;
import avance.integrador.modelo.Usuario;
import avance.integrador.modelo.alumno;
import avance.integrador.servicio.UsuarioServicio;
import avance.integrador.servicio.alumnoservice;

/**
 *
 * @author Joshep
 */
@Controller
@RequestMapping("/")
public class SecCredencialesSOPORTEControlador {

	@Autowired
	private alumnoservice alumnoService;

	@Autowired
	private UsuarioServicio usuarioService;

	@GetMapping("/SecCredencialesSOPORTE")
	public String mostrarCredenciales(Model model) {
		List<alumno> alumnos = alumnoService.obteneralumnoapo();
		List<Usuario> usuarios = usuarioService.listarUsuario();
		
		List<CredencialDTO> credenciales = new ArrayList<CredencialDTO>();
		int maxSize = Math.min(alumnos.size(), usuarios.size());
		
		for (int i = 0; i < maxSize; i++) {
			credenciales.add(new CredencialDTO(alumnos.get(i), usuarios.get(i)));
		}
		
		model.addAttribute("credenciales", credenciales);
		return "SecCredencialesSOPORTE"; // Retorna la vista desde templates/SecInicioSOPORTE.html
	}

	@PostMapping("/crear")
	public String crearUsuario(@RequestParam String numero_documento, @RequestParam String codigo,
			@RequestParam String password) {
		// Buscar el alumno por el número de documento
		alumno alumno = alumnoService.findByNumeroDocumento(numero_documento);
		if (alumno == null) {
			return "redirect:/SecCredencialesSOPORTE";
		}

		// Crear el Usuario asociado
		Usuario usuario = new Usuario();
		usuario.setCodigo(codigo);
		usuario.setPassword(password);
		usuario.setAlumno(alumno); // Asociar el usuario al alumno

		// Guardar el Usuario
		usuarioService.guardarUsuario(usuario);

		return "redirect:/SecCredencialesSOPORTE";
	}

}
