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
    public String registrarSolicitud(@ModelAttribute SolicitudMatriculaDTO solicitudDTO, Model model) {
        // Validar y crear Apoderado
        apoderado apoderado = new apoderado();
        apoderado.setTipo_documento(solicitudDTO.getTipo_documento());

        validarDocumento(solicitudDTO.getNumero_documento(), "Error: el documento de identidad no es correcto.");
        apoderado.setNumero_documento(solicitudDTO.getNumero_documento());

        apoderado.setApellido_paterno(solicitudDTO.getApellido_paterno());
        apoderado.setApellido_materno(solicitudDTO.getApellido_materno());
        apoderado.setNombres(solicitudDTO.getNombres());

        validarTelefono(solicitudDTO.getTelefono_movil());
        apoderado.setTelefono_movil(solicitudDTO.getTelefono_movil());

        apoderado.setCorreo(solicitudDTO.getCorreo());
        apoderado.setDireccion(solicitudDTO.getDireccion());
        apoderado = apo.guardar(apoderado);

        // Validar y crear Alumno
        alumno alumno = new alumno();
        alumno.setTipo_documento(solicitudDTO.getTipo_documentos());

        validarDocumento(solicitudDTO.getNumero_documentos(), "Error: el documento de identidad del alumno no es correcto.");
        alumno.setNumero_documento(solicitudDTO.getNumero_documentos());

        alumno.setApellido_paterno(solicitudDTO.getApellido_paternos());
        alumno.setApellido_materno(solicitudDTO.getApellido_maternos());
        alumno.setNombres(solicitudDTO.getNombress());
        alumno.setFecha_nacimiento(solicitudDTO.getFecha_nacimiento());
        alumno.setSexo(solicitudDTO.getSexo());
        alumno.setNacionalidad(solicitudDTO.getNacionalidad());
        alumno.setApoderado(apoderado);
        alumno = alumnoRepositori.save(alumno);

        // Crear y guardar Matricula
        matricula matricula = new matricula();
        matricula.setAño_matricula(solicitudDTO.getAño_matricula() != null
                ? solicitudDTO.getAño_matricula() : Year.now());
        matricula.setEstado(solicitudDTO.getEstado() != null ? solicitudDTO.getEstado() : "Pendiente");
        matricula.setSede(solicitudDTO.getSede());
        matricula.setTurno(solicitudDTO.getTurno());
        matricula.setNivel(solicitudDTO.getNivel());
        matricula.setGrado(solicitudDTO.getGrado());
        matricula.setAlumno(alumno);
        matricula.setApoderado(apoderado);

        repo.save(matricula);

        model.addAttribute("message", "Solicitud enviada exitosamente.");
        return "exito";
    }

    private void validarDocumento(String documento, String mensaje) {
        if (!phoneValidator.isValid(documento)) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void validarTelefono(String telefono) {
        if (!phoneValidator.isValid(telefono)) {
            throw new IllegalArgumentException("Error: el teléfono no es correcto.");
        }
    }
}
