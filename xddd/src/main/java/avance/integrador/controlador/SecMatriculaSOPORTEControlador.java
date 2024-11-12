/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.AlmacenFiltroMatricula;
import avance.integrador.servicio.matriculaservice;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Joshep
 */
@Controller
public class SecMatriculaSOPORTEControlador {
        
    
    @Autowired
    private matriculaservice matriculaService;
    
        @Autowired
    private AlmacenFiltroMatricula  almafiltroMatricula; // Inyección del servici
        
    @GetMapping("/SecMatriculaSOPORTE")
    public String mostrarMatricula(  
            @RequestParam(required = false) String numeroDocumento,
            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String grado,
            @RequestParam(required = false) String estado,
            Model model) {
        
        
         // Convertir campos vacíos a null
        numeroDocumento = (numeroDocumento != null && !numeroDocumento.isEmpty()) ? numeroDocumento : null;
        nivel = (nivel != null && !nivel.isEmpty() && !nivel.equals("Seleccione")) ? nivel : null;
        grado = (grado != null && !grado.isEmpty() && !grado.equals("Seleccione")) ? grado : null;
        estado = (estado != null && !estado.isEmpty() && !estado.equals("Seleccione")) ? estado : null;

        // Almacenar filtros en la sesión usando el servicio
        almafiltroMatricula.setFiltro(numeroDocumento, nivel, grado, estado);
        
        // Obtener las solicitudes filtradas
        List<Object[]> matriculasFiltradas = new ArrayList<>();
        int contadorResultados = 0;
        
        // Si alguno de los filtros tiene valor, realiza la búsqueda
        if (numeroDocumento != null || nivel != null || grado != null || estado != null) {
            matriculasFiltradas = matriculaService.filtrarMatriculas(numeroDocumento, nivel, grado, estado);
            contadorResultados = matriculasFiltradas.size();
        }

        model.addAttribute("matriculasPendientes", matriculaService.obtenerMatriculasPendientes());
        model.addAttribute("matriculasFiltradas", matriculasFiltradas);
        model.addAttribute("contadorResultados", contadorResultados);
        return "SecMatriculaSOPORTE"; // Retorna la vista desde templates/SecInicioSOPORTE.html
    }
    
    
    
       // Endpoint para actualizar el estado de la solicitud y recargar la página
    @PostMapping("/actualizarEstadoMatricula")
    public String actualizarEstadoMatricula(@RequestParam Integer idMatricula, @RequestParam String estado) {
        matriculaService.actualizarEstadoMatricula(idMatricula, estado);
        return "redirect:/SecMatriculaSOPORTE"; // Redirige a la misma página para recargarla
    }
    
    
     @GetMapping("/descargarExcelMatricula")
    public void descargarExcel(HttpServletResponse response) throws IOException {
        // Obtener los filtros desde la sesión usando el servicio
        String numeroDocumento = almafiltroMatricula.getNumeroDocumento();
        String nivel = almafiltroMatricula.getNivel();
        String grado = almafiltroMatricula.getGrado();
        String estado = almafiltroMatricula.getEstado();

        // Llamar al método del servicio para generar el Excel
        matriculaService.generarExcel(response, numeroDocumento, nivel, grado, estado);
    }
    
}
