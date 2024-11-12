package avance.integrador.controlador;

import avance.integrador.modelo.AlmacenFiltroPago;

import avance.integrador.modelo.pagomatricula;
import avance.integrador.repositorio.PagomatriculaRepositorio;
import avance.integrador.servicio.PagomatriculaServicio;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importa Model para agregar atributos
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Joshep
 */
@Controller
public class SecPagoSOPORTEControlador {
    
    @Autowired
    private PagomatriculaServicio pagomatriculaServicio;
    
    
    @Autowired
    private AlmacenFiltroPago  almafiltroPago; // Inyección del servici
          
    
 
     
     
      @Autowired
    private PagomatriculaRepositorio pagomatriculaRepo;
    
    @GetMapping("/SecPagoSOPORTE")
    public String mostrarPagoSOPORTE(
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
        almafiltroPago.setFiltro(numeroDocumento, nivel, grado, estado);

        // Obtener las solicitudes filtradas
        List<Object[]> solicitudesFiltradas = new ArrayList<>();
        int contadorResultados = 0;

        // Si alguno de los filtros tiene valor, realiza la búsqueda
        if (numeroDocumento != null || nivel != null || grado != null || estado != null) {
            solicitudesFiltradas = pagomatriculaServicio.filtrarSolicitudes(numeroDocumento, nivel, grado, estado);
            contadorResultados = solicitudesFiltradas.size();
        }

        model.addAttribute("pagosPendientes", pagomatriculaServicio.obtenerPagosPendientes());
        model.addAttribute("pagosFiltradas", solicitudesFiltradas);
        model.addAttribute("contadorResultados", contadorResultados);

        return "SecPagoSOPORTE";
    }

    
    
    
    
    
    
@PostMapping("/actualizarEstadoPago")
public String actualizarEstadoPago(@RequestParam Integer idPago, 
                                   @RequestParam String estado, 
                                   @RequestParam(required = false) BigDecimal acuenta, 
                                   @RequestParam(required = false) BigDecimal deuda) {
    
    // Imprimir los valores de los parámetros recibidos
    System.out.println("ID Pago: " + idPago);
    System.out.println("Estado: " + estado);
    System.out.println("Acuenta: " + acuenta);
    System.out.println("Deuda: " + deuda);
    
    // Llamar al servicio para actualizar el estado, acuenta y deuda
    pagomatriculaServicio.actualizarPago(idPago, estado, acuenta, deuda);

    return "redirect:/SecPagoSOPORTE"; 
}

   
      @GetMapping("/descargarExcelPago")
    public void descargarExcel(HttpServletResponse response) throws IOException {
        // Obtener los filtros desde la sesión usando el servicio
        String numeroDocumento = almafiltroPago.getNumeroDocumento();
        String nivel = almafiltroPago.getNivel();
        String grado = almafiltroPago.getGrado();
        String estado = almafiltroPago.getEstado();

        // Llamar al método del servicio para generar el Excel
        pagomatriculaServicio.generarExcel(response, numeroDocumento, nivel, grado, estado);
    }
  

}
