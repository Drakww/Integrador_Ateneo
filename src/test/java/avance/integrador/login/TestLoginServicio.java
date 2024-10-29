/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.login;

import avance.integrador.servicio.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


/**
 *
 * @author PC
 */
public class TestLoginServicio {
    
    private UsuarioServicio usuarioServicio;
    private LoginServicio loginServicio;
    
    @BeforeEach
    public void setUp(){
        usuarioServicio = mock(UsuarioServicio.class);
        loginServicio = new LoginServicio(usuarioServicio);
    }
    
    @Test
    public void testValidarCredencialesCorrecto(){
        String codigo = "U123";
        String password = "123";
        
        when(usuarioServicio.validarCredenciales(codigo, password)).thenReturn(true);
        
        Assertions.assertTrue(loginServicio.validarCredenciales(codigo, password),"Correcto");
    }
    
    @Test
    public void testValidarCredencialesIncorrectas(){
        String codigo = "123";
        String password = "000";
        
        when(usuarioServicio.validarCredenciales(codigo, password)).thenReturn(false);
        
        Assertions.assertFalse(loginServicio.validarCredenciales(codigo, password),"Incorrecto");
    }
    
   
    
}
