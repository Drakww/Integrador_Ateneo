package avance.integrador.controlador;

import avance.integrador.servicio.PagomatriculaServicio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecPagoAPODERADOControlador {

    @Autowired
    private PagomatriculaServicio pagoMatriculaServicio;

    // Método para cargar la vista del formulario
    @GetMapping("/SecPagoAPODERADO")
    public String apoderadoForm() {
        return "SecPagoAPODERADO"; // Cambia el nombre de la plantilla si es necesario
    }

    @PostMapping("/actualizarVoucher")
    public String actualizarVoucher(@RequestParam("voucher_imagen") MultipartFile file,
                                    @RequestParam("numero_documento") String numeroDocumento,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Llamar al servicio para guardar el archivo y actualizar la ruta en la base de datos
            pagoMatriculaServicio.actualizarVoucher(file, numeroDocumento);
            redirectAttributes.addFlashAttribute("mensajeExitoPago", "Voucher actualizado correctamente.Vuelve a iniciar sesion para ver los cambios");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensajeErrorPago", "Ocurrió un error al actualizar el voucher. Intente de nuevo.");
            return "redirect:/SecPagoAPODERADO"; // Redireccionar a la misma vista en caso de fallo
        }

        return "redirect:/SecPagoAPODERADO"; // Redireccionar a la vista del formulario después de la actualización
    }
}
