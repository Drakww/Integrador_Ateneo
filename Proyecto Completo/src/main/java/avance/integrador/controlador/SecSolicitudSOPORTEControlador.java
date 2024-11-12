package avance.integrador.controlador;

import avance.integrador.modelo.AlmacenFiltroSolicitud;
import avance.integrador.modelo.Solicitud;
import avance.integrador.servicio.SolicitudService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class SecSolicitudSOPORTEControlador {

    @Autowired
    private SolicitudService solicitudService;
    
    @Autowired
    private AlmacenFiltroSolicitud  almafiltroSolicitud; // Inyección del servici

  // Cargar la página principal de solicitudes con o sin filtrado
  @GetMapping("/SecSolicitudSOPORTE")
    public String mostrarSecSolicitudSOPORTE(
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
        almafiltroSolicitud.setFiltro(numeroDocumento, nivel, grado, estado);
        
        // Obtener las solicitudes filtradas
        List<Object[]> solicitudesFiltradas = new ArrayList<>();
        int contadorResultados = 0;
        
        // Si alguno de los filtros tiene valor, realiza la búsqueda
        if (numeroDocumento != null || nivel != null || grado != null || estado != null) {
            solicitudesFiltradas = solicitudService.filtrarSolicitudes(numeroDocumento, nivel, grado, estado);
            contadorResultados = solicitudesFiltradas.size();
        }

        
               // También puedes mantener la lista de solicitudes pendientes si es necesario
        model.addAttribute("solicitudesPendientes", solicitudService.obtenerSolicitudesPendientes());
        model.addAttribute("solicitudesFiltradas", solicitudesFiltradas);
        model.addAttribute("contadorResultados", contadorResultados);
        
        return "SecSolicitudSOPORTE";
    }

    
    
    
    
    
    // Endpoint para actualizar el estado de la solicitud y recargar la página
    @PostMapping("/actualizarEstado")
    public String actualizarEstadoSolicitud(@RequestParam Integer idSolicitud, @RequestParam String estado) {
        solicitudService.actualizarEstadoSolicitud(idSolicitud, estado);
        return "redirect:/SecSolicitudSOPORTE"; // Redirige a la misma página para recargarla
    }
    
    
    
    
    
    
  @GetMapping("/descargarExcel")
    public void descargarExcel(HttpServletResponse response) throws IOException {
        // Obtener los filtros desde la sesión usando el servicio
        String numeroDocumento = almafiltroSolicitud.getNumeroDocumento();
        String nivel = almafiltroSolicitud.getNivel();
        String grado = almafiltroSolicitud.getGrado();
        String estado = almafiltroSolicitud.getEstado();

        // Llamar al método del servicio para generar el Excel
        solicitudService.generarExcel(response, numeroDocumento, nivel, grado, estado);
    }



    

}
