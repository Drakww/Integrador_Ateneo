/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.TestSolicitudServicio;

import avance.integrador.modelo.alumno;
import avance.integrador.modelo.apoderado;
import avance.integrador.modelo.matricula;
import avance.integrador.repositorio.matricularepositorio;
import avance.integrador.servicio.matriculaservice;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author PC
 */
public class TestMatriculaServicio {
    private matricularepositorio repo;
    private matriculaservice matriculaServicio;
    
    @BeforeEach
    public void setUp() {
        
        repo = mock(matricularepositorio.class);
        matriculaServicio = new matriculaservice();
        matriculaServicio.repo= repo;
    }
    
    @Test
    public void testObtenermatriConClave(){
        String clave = "123";

        alumno alumno1 = new alumno();
        alumno alumno2 = new alumno();

        apoderado apoderado1 = new apoderado();
        apoderado apoderado2 = new apoderado();

        matricula matricula1 = new matricula(1, "Sede Huaycan", "Mañana", "Primaria", "2º Grado", Year.of(2024), "Pendiente", alumno1, apoderado1);
        matricula matricula2 = new matricula(2, "Sede Huaycan", "Tarde", "Secundaria", "1º Grado", Year.of(2024), "Activo", alumno2, apoderado2);
        List<matricula> listaMatriculas =  Arrays.asList(matricula1,matricula2);
        
        when(repo.findAll(clave)).thenReturn(listaMatriculas);
        
        List<matricula> matriculas = matriculaServicio.obtenermatri(clave);
        
        assertEquals(2, matriculas.size(), "Deria agregarse 2 matriculas en la lista");
        assertEquals(matricula1,matriculas.get(0), "La primera matricula deberia ser matricula1");
        assertEquals(matricula2,matriculas.get(1), "La segunda matricula deberia ser matricula2");   
    }
    
    @Test
    public void testObtenerMatriSinClave(){
        alumno alumno3 = new alumno();
        apoderado apoderado3 = new apoderado();
        
        matricula matricula3 = new matricula(3, "Sede Huaycan", "Tarde", "Secundaria", "4º Grado", Year.of(2024), "Activo", alumno3, apoderado3);
        List<matricula> listaMatriculasSin = Arrays.asList(matricula3);
        
        when(repo.findAll()).thenReturn(listaMatriculasSin);
        
        List<matricula> matriculas = matriculaServicio.obtenermatri(null);
        
        assertEquals(1,matriculas.size());
        assertEquals(matricula3, matriculas.get(0),"La matricula deberia ser matricula 3");
    }
}
