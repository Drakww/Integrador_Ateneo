/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package avance.integrador.servicio;

import avance.integrador.modelo.PagoMatricula;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IPagoMatriculaServicio {
    public List<PagoMatricula> listarPagosMatricula();
    
    public PagoMatricula buscarPagoMatricula(Integer id);
    
    public void guardarPago(PagoMatricula pagoMatricula);
    
    public void eliminarPago(PagoMatricula pagoMatricula);
}
