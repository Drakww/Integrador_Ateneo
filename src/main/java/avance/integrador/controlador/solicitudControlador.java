	package avance.integrador.controlador;

import avance.integrador.modelo.Solicitud;
import avance.integrador.modelo.SolicitudMatriculaDTO; // Importa la clase DTO
import avance.integrador.modelo.apoderado;
import avance.integrador.modelo.alumno; // Asegúrate de importar alumno
import avance.integrador.modelo.matricula;
import avance.integrador.repositorio.apoderadoRepositorio;
import avance.integrador.repositorio.alumnoRepositorio; // Necesitarás un repositorio para alumno
import avance.integrador.repositorio.matricularepositorio;
import avance.integrador.repositorio.solicitudRepositorio;
import java.sql.Timestamp;
import java.time.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class solicitudControlador {

    @Autowired
    private apoderadoRepositorio apoderadoRepositorio;

    @Autowired
    private alumnoRepositorio alumnoRepositorio; // Inyecta el repositorio de alumno
    
    @Autowired
    private matricularepositorio matricularepositorio; 
    
       @Autowired
    private solicitudRepositorio solicitudRepositorio; // Inyecta el repositorio de solicitud

    // Muestra el formulario para registrar la solicitud
    @GetMapping("/solicitud")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("solicitudMatricula", new SolicitudMatriculaDTO()); // Agrega un objeto DTO vacío
        return "solicitud"; // Nombre de la vista HTML
    }

    // Procesa la solicitud enviada mediante POST
    @PostMapping("/solicitudMatricula")
    public String registrarSolicitud(@ModelAttribute SolicitudMatriculaDTO solicitudMatricula) {
        // Guarda el apoderado
        apoderado apoderado = solicitudMatricula.getApoderado();
        apoderadoRepositorio.save(apoderado);

        // Guarda el alumno con la relación al apoderado
        alumno alumno = solicitudMatricula.getAlumno();
        alumno.setApoderado(apoderado); // Asocia el alumno con el apoderado
        alumnoRepositorio.save(alumno);
        
        // Guarda la matrícula asociando el alumno y el apoderado
        matricula matricula = solicitudMatricula.getMatricula();
        matricula.setAlumno(alumno); // Asocia la matrícula con el alumno
        matricula.setApoderado(apoderado); // Asocia la matrícula con el apoderado
        
        matricula.setAño_matricula(Year.of(2025)); // Asigna el año de matrícula
        matricula.setEstado("Pendiente"); // Asigna el estado predeterminado
        
        matricularepositorio.save(matricula); // Guarda la matrícula
        
        // Crea la solicitud asociada
        Solicitud solicitud = new Solicitud();
        solicitud.setApoderado(apoderado); // Asocia el apoderado
        solicitud.setAlumno(alumno); // Asocia el alumno
        solicitud.setTipo_solicitud("Matrícula en línea"); // Define el tipo de solicitud
        solicitud.setEstado("Pendiente"); // Define el estado de la solicitud
        solicitud.setFecha_solicitud(new Timestamp(System.currentTimeMillis())); // Asigna la fecha actual
        
        solicitudRepositorio.save(solicitud); // Guarda la solicitud 
       

        return "redirect:/exito"; // Redirección a la página de éxito
    }
}
