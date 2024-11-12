/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.credalumno;
import avance.integrador.servicio.CredalumnoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Joshep
 */
@Controller
public class SecCredencialesSOPORTEControlador {
          @Autowired
    private CredalumnoServicio credalumnoServicio;

    @GetMapping("/SecCredencialesSOPORTE")
    public String mostrarCredenciales(Model model) {
        model.addAttribute("credalumno", new credalumno()); // Agrega un objeto vacio para el formulario
        return "SecCredencialesSOPORTE"; // Retorna la vista desde templates/SecCredencialesSOPORTE.html
    }
    
    @PostMapping("/registrarCredenciales")
    public String registrarCredenciales(@ModelAttribute credalumno credalumno, Model model) {
        credalumnoServicio.guardarCredalumno(credalumno);
        model.addAttribute("mensaje", "Credenciales registradas correctamente"); // Mensaje de éxito
        return "SecCredencialesSOPORTE"; // Retorna la vista después de registrar
    }
    
    
    
        @PostMapping("/filtrarCredenciales")
    public String filtrarCredenciales(@RequestParam("numeroDocumento") String numeroDocumento, Model model) {
        List<Object[]> credenciales = credalumnoServicio.filtrarCredenciales(numeroDocumento);
        model.addAttribute("credenciales", credenciales); // Agrega las credenciales encontradas al modelo
        return "SecCredencialesSOPORTE"; // Retorna la vista
    }
    
    
    
    
     @PostMapping("/actualizarCredencial")
    @ResponseBody // Para devolver datos en formato JSON
    public ResponseEntity<Map<String, Object>> actualizarCredencial(@RequestBody Map<String, String> credencialData) {
        String idCredalumno = credencialData.get("id_credalumno");
        String usuario = credencialData.get("usuario");
        String contrasena = credencialData.get("contrasena");

        // Llama al servicio para actualizar las credenciales
        credalumnoServicio.actualizarCredalumno(idCredalumno, usuario, contrasena);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
}
