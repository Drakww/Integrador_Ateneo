	package avance.integrador.controlador;

import java.time.Year;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import avance.integrador.modelo.SolicitudMatriculaDTO; // Importa la clase DTO
import avance.integrador.modelo.alumno; // Asegúrate de importar alumno
import avance.integrador.modelo.apoderado;
import avance.integrador.modelo.matricula;
import avance.integrador.repositorio.alumnoRepositorio; // Necesitarás un repositorio para alumno
import avance.integrador.repositorio.matricularepositorio;
import avance.integrador.repositorio.solicitudRepositorio;
import avance.integrador.servicio.apoderadoservice;

@Controller
public class solicitudControlador {

    @Autowired
    private apoderadoservice apo;

    @Autowired
    private alumnoRepositorio alumnoRepositori; // Inyecta el repositorio de alumno
    
    @Autowired
    private matricularepositorio repo; 
    
       @Autowired
    private solicitudRepositorio solicitudRepositorio; // Inyecta el repositorio de solicitud

       RegexValidator phoneValidator = new RegexValidator("^\\d{8,9}$");
       
    // Muestra el formulario para registrar la solicitud
    @GetMapping("/solicitud")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("solicitudMatricula", new SolicitudMatriculaDTO()); // Agrega un objeto DTO vacío
        return "solicitud"; // Nombre de la vista HTML
    }

    // Procesa la solicitud enviada mediante POST
    @PostMapping("/solicitudMatricula")
    public String registrarSolicitud(@ModelAttribute SolicitudMatriculaDTO solicitudDTO,Model model) {
        // Crear y guardar Apoderado
        apoderado apoderado = new apoderado();
        apoderado.setTipo_documento(solicitudDTO.getTipo_documento());
        if ( !phoneValidator.isValid(solicitudDTO.getNumero_documento())) {
            throw new IllegalArgumentException("Error el documento de identidad o el telefono no son correctos ");
        }else {
        	apoderado.setNumero_documento(solicitudDTO.getNumero_documento());
    }
        apoderado.setApellido_paterno(solicitudDTO.getApellido_paterno());
        apoderado.setApellido_materno(solicitudDTO.getApellido_materno());
        apoderado.setNombres(solicitudDTO.getNombres());
        if ( !phoneValidator.isValid(solicitudDTO.getTelefono_movil())) {
            throw new IllegalArgumentException("Error el documento de identidad o el telefono no son correctos");
        }else {
        apoderado.setTelefono_movil(solicitudDTO.getTelefono_movil());
    }
        apoderado.setCorreo(solicitudDTO.getCorreo());
        apoderado.setDireccion(solicitudDTO.getDireccion());
        apoderado = apo.guardar(apoderado);

        // Crear y guardar Alumno
        alumno alumno = new alumno();
        alumno.setTipo_documento(solicitudDTO.getTipo_documentos());
        apoderado.setTipo_documento(solicitudDTO.getTipo_documento());
        if ( !phoneValidator.isValid(solicitudDTO.getNumero_documentos())) {
            throw new IllegalArgumentException("Error el documento de identidad o el telefono no son correctos");
        }else {
        	apoderado.setNumero_documento(solicitudDTO.getNumero_documentos());
    }
        alumno.setApellido_paterno(solicitudDTO.getApellido_paternos());
        alumno.setApellido_materno(solicitudDTO.getApellido_maternos());
        alumno.setNombres(solicitudDTO.getNombress());
        alumno.setFecha_nacimiento(solicitudDTO.getFecha_nacimiento());
        alumno.setSexo(solicitudDTO.getSexo());
        alumno.setNacionalidad(solicitudDTO.getNacionalidad());
        alumno.setApoderado(apoderado);
        alumno = alumnoRepositori.save(alumno);

        // Crear y guardar Matricula con relación al alumno y apoderado
        matricula matri = new matricula();
       matri.setAño_matricula(solicitudDTO.getAño_matricula() != null ? 
    		    solicitudDTO.getAño_matricula() : 
    		        Year.now());
       matri.setEstado(solicitudDTO.getEstado() != null ? solicitudDTO.getEstado() : "Pendiente"); // Default value example

        matri.setSede(solicitudDTO.getSede());
        matri.setTurno(solicitudDTO.getTurno());
        matri.setNivel(solicitudDTO.getNivel());
        matri.setGrado(solicitudDTO.getGrado());
        matri.setAlumno(alumno);
        matri.setApoderado(apoderado);
        repo.save(matri);

        model.addAttribute("message", "Solicitud enviada exitosamente.");
        return "exito";
    }
}
