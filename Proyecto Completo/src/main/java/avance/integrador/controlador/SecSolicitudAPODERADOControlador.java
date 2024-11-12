/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.apoderado;
import avance.integrador.repositorio.apoderadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Joshep
 */
@Controller
public class SecSolicitudAPODERADOControlador {
    
    @Autowired
    private apoderadoRepositorio apoderadoRepo;

    @GetMapping("/SecSolicitudAPODERADO")
    public String apoderadoForm() {
        return "SecSolicitudAPODERADO";
    }

    // Método para actualizar el apoderado
    // Método para actualizar el apoderado mediante el numero_documento del alumno
     @PostMapping("/actualizarApoderado")
    public String actualizarApoderado(@RequestParam("telefono_movil") String telefonoMovil,
                                        @RequestParam("correo_electronico") String correo,
                                        @RequestParam("direccion_ap") String direccion,
                                        @RequestParam("numero_documento_alumno") String numeroDocumento,
                                        RedirectAttributes redirectAttributes) {
        
        // Imprimir los datos recibidos
        System.out.println("Datos recibidos:");
        System.out.println("Telefono movil: " + telefonoMovil);
        System.out.println("Correo electronico: " + correo);
        System.out.println("Direccion: " + direccion);
        System.out.println("Numero documento alumno: " + numeroDocumento);
        
        // Buscando el apoderado por el numero_documento del alumno
        apoderado apoderado = apoderadoRepo.findApoderadoByNumeroDocumentoAlumno(numeroDocumento);

        if (apoderado != null) {
            apoderado.setTelefono_movil(telefonoMovil);
            apoderado.setCorreo(correo);
            apoderado.setDireccion(direccion);
            apoderadoRepo.save(apoderado);
            redirectAttributes.addFlashAttribute("mensajeExito", "Datos actualizados correctamente.");
            redirectAttributes.addFlashAttribute("mensajeInfo", "Vuelve a iniciar sesión para ver los cambios.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "No se encontró el apoderado para el documento especificado.");
        }

        return "redirect:/SecSolicitudAPODERADO"; // Redirige a la página después de la actualización
    }
}
