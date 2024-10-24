/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.controlador;

import avance.integrador.modelo.PagoMatricula;
import avance.integrador.servicio.IPagoMatriculaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdministradorControlador {

    @Autowired
    private IPagoMatriculaServicio pagoServicio;
    
    @GetMapping("/administrador")
    public String Pagos(Model model) {
        List<PagoMatricula> pagos = pagoServicio.listarPagosMatricula();
        model.addAttribute("pagos",pagos);
        
        return "administrador"; // Nombre de la plantilla HTML para el admistrador
    }
}