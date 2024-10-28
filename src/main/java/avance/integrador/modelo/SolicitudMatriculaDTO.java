package avance.integrador.modelo;

import java.time.Year;

import org.apache.commons.validator.routines.RegexValidator;

import lombok.Data;

@Data
public class SolicitudMatriculaDTO {
	
	 // Campos de Apoderado
	 private String tipo_documento;
	    private String numero_documento;
	    private String apellido_paterno;
	    private String apellido_materno;
	    private String nombres;
	    private String telefono_movil;
	    private String correo;
	    private String direccion;

   // Campos de Alumno
	    private String tipo_documentos;
	    private String numero_documentos;
	    private String apellido_paternos;
	    private String apellido_maternos;
	    private String nombress;
	    private String fecha_nacimiento; // Podrías cambiar a Date o LocalDate
	    private String sexo;
	    private String nacionalidad;


   // Campos de Matricula
   private Year año_matricula;
   private String estado;
   private String sede;
   private String turno;
   private String nivel;
   private String grado;
   
  
}	
	

