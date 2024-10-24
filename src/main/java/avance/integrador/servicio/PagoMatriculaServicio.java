/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.servicio;

import avance.integrador.modelo.PagoMatricula;
import avance.integrador.repositorio.PagoMatriculaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class PagoMatriculaServicio implements IPagoMatriculaServicio{
    
    @Autowired
    private PagoMatriculaRepositorio pagoMatriculaRepositorio;

    @Override
    public List<PagoMatricula> listarPagosMatricula() {
        List<PagoMatricula> pagosMatricula = pagoMatriculaRepositorio.findAll();
        return pagosMatricula;
    }

    @Override
    public PagoMatricula buscarPagoMatricula(Integer id) {
        PagoMatricula pagoMatricula = pagoMatriculaRepositorio.findById(id).orElse(null);
        return pagoMatricula;
    }

    @Override
    public void guardarPago(PagoMatricula pagoMatricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPago(PagoMatricula pagoMatricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
