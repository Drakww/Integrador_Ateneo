/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joshep
 */
@Service
public class AlmacenFiltroMatricula {
     @Autowired
    private HttpSession session;

    public void setFiltro(String numeroDocumento, String nivel, String grado, String estado) {
        session.setAttribute("numeroDocumento", numeroDocumento);
        session.setAttribute("nivel", nivel);
        session.setAttribute("grado", grado);
        session.setAttribute("estado", estado);
    }

    public String getNumeroDocumento() {
        return (String) session.getAttribute("numeroDocumento");
    }

    public String getNivel() {
        return (String) session.getAttribute("nivel");
    }

    public String getGrado() {
        return (String) session.getAttribute("grado");
    }

    public String getEstado() {
        return (String) session.getAttribute("estado");
    }

    public void clear() {
        session.invalidate(); // Borra toda la sesión si es necesario
    }
}
