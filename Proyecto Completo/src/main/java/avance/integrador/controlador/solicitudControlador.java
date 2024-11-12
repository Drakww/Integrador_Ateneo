package avance.integrador.controlador;

import avance.integrador.modelo.*;
import avance.integrador.repositorio.*;
import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Year;


@Controller
public class solicitudControlador {

    @Autowired
    private apoderadoRepositorio apoderadoRepositorio;

    @Autowired
    private alumnoRepositorio alumnoRepositorio;

    @Autowired
    private matriculaRepositorio matricularepositorio;

    @Autowired
    private solicitudRepositorio solicitudRepositorio;

    @Autowired
    private PagomatriculaRepositorio pagomatriculaRepositorio;

    RegexValidator documentoValidator = new RegexValidator("^\\d{8}$");
    RegexValidator telefonoValidator = new RegexValidator("^9\\d{8}$");
    RegexValidator textoValidator = new RegexValidator("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");

    @GetMapping("/solicitud")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("solicitudMatricula", new SolicitudMatriculaDTO());

        return "solicitud";
    }

    @PostMapping("/solicitudMatricula")
    public String registrarSolicitud(@ModelAttribute SolicitudMatriculaDTO solicitudMatricula, @RequestParam(value = "nivel", required = false) String nivel, Model model) {
        apoderado apoderado = solicitudMatricula.getApoderado();
        alumno alumno = solicitudMatricula.getAlumno();
        matricula matricula = solicitudMatricula.getMatricula();

        // Validaciones
        if (!documentoValidator.isValid(alumno.getNumero_documento())) {
            model.addAttribute("errorMessage", "Número de documento del alumno inválido. Debe ser de 8 dígitos.");
            return "solicitud";
        }
        if (!documentoValidator.isValid(apoderado.getNumero_documento())) {
            model.addAttribute("errorMessage", "Número de documento del apoderado inválido. Debe ser de 8 dígitos.");
            return "solicitud";
        }
        if (!telefonoValidator.isValid(apoderado.getTelefono_movil())) {
            model.addAttribute("errorMessage", "Número de teléfono inválido. Debe ser de 9 dígitos y comenzar con 9.");
            return "solicitud";
        }
        if (!textoValidator.isValid(alumno.getNombres()) || !textoValidator.isValid(alumno.getApellido_paterno()) || !textoValidator.isValid(alumno.getApellido_materno())) {
            model.addAttribute("errorMessage", "El nombre y apellidos del alumno solo pueden contener letras y espacios.");
            return "solicitud";
        }
        if (!textoValidator.isValid(apoderado.getNombres()) || !textoValidator.isValid(apoderado.getApellido_paterno()) || !textoValidator.isValid(apoderado.getApellido_materno())) {
            model.addAttribute("errorMessage", "El nombre y apellidos del apoderado solo pueden contener letras y espacios.");
            return "solicitud";
        }

        // Convertir nombres y apellidos a mayúsculas
        alumno.setNombres(alumno.getNombres().toUpperCase());
        alumno.setApellido_paterno(alumno.getApellido_paterno().toUpperCase());
        alumno.setApellido_materno(alumno.getApellido_materno().toUpperCase());
        apoderado.setNombres(apoderado.getNombres().toUpperCase());
        apoderado.setApellido_paterno(apoderado.getApellido_paterno().toUpperCase());
        apoderado.setApellido_materno(apoderado.getApellido_materno().toUpperCase());

        // Guardar entidades
        apoderadoRepositorio.save(apoderado);
        alumno.setApoderado(apoderado);
        alumnoRepositorio.save(alumno);

        matricula.setAlumno(alumno);
        matricula.setApoderado(apoderado);
        matricula.setAño_matricula(Year.of(2025));
        matricula.setEstado("Pendiente");
        matricularepositorio.save(matricula);

        Solicitud solicitud = new Solicitud();
        solicitud.setApoderado(apoderado);
        solicitud.setAlumno(alumno);
        solicitud.setTipo_solicitud("Matrícula en línea");
        solicitud.setEstado("Pendiente");
        solicitud.setFecha_solicitud(new Timestamp(System.currentTimeMillis()));
        solicitudRepositorio.save(solicitud);

        pagomatricula pago = new pagomatricula();
        String nivelMatricula = matricula.getNivel();
        BigDecimal monto_matricula = switch (nivelMatricula) {
            case "Inicial" -> BigDecimal.valueOf(100.00);
            case "Primaria" -> BigDecimal.valueOf(150.00);
            case "Secundaria" -> BigDecimal.valueOf(200.00);
            default -> BigDecimal.ZERO;
        };

        
        
        pago.setNumero_documento(alumno.getNumero_documento());
        pago.setConcepto("Matrícula_2025");
        pago.setVoucher_path("");
        pago.setEstado("Pendiente");
        pago.setFecha_pago(new Timestamp(System.currentTimeMillis()));
        pago.setMonto_final(monto_matricula);
        pago.setAcuenta(BigDecimal.ZERO);
        pago.setDeuda(monto_matricula);

        pagomatriculaRepositorio.save(pago);

        return "redirect:/loader?seccion=exito";
    }
}
