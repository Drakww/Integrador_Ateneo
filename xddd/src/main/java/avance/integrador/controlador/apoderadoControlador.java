package avance.integrador.controlador;

import avance.integrador.modelo.Solicitud;
import avance.integrador.modelo.alumno;
import avance.integrador.modelo.credalumno;
import avance.integrador.modelo.matricula;
import avance.integrador.modelo.pagomatricula;
import avance.integrador.repositorio.alumnoRepositorio;
import avance.integrador.repositorio.credalumnoRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class apoderadoControlador {
   
    @Autowired
    private credalumnoRepositorio credalumnoRepositorio;


@Autowired
private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/apoderado")
    public String apoderadoForm() {
        return "apoderado";  // Retorna la plantilla Thymeleaf (apoderado.html)
    }

 @PostMapping("/loginApoderado")
public String login(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
    
    credalumno credAlumno = credalumnoRepositorio.findByUsuario(usuario);

    if (credAlumno != null && passwordEncoder.matches(contrasena, credAlumno.getContrasena())) { // Verifica que el alumno no sea null
        // Alumno 
        String tipoDocumentoAlumno=credAlumno.getAlumno().getTipo_documento();
        session.setAttribute("tipoDocumentoAlumno", tipoDocumentoAlumno);
        
        String numeroDocumentoAlumno=credAlumno.getAlumno().getNumero_documento();
        session.setAttribute("numeroDocumentoAlumno", numeroDocumentoAlumno);
        
        String apellidoPaternoAlumno=credAlumno.getAlumno().getApellido_paterno();
        session.setAttribute("apellidoPaternoAlumno", apellidoPaternoAlumno); 
        
        String apellidoMaternoAlumno=credAlumno.getAlumno().getApellido_materno();
        session.setAttribute("apellidoMaternoAlumno", apellidoMaternoAlumno);
        
        String nombresAlumno = credAlumno.getAlumno().getNombres(); // Accede al nombre del alumno
        session.setAttribute("nombresAlumno", nombresAlumno); // Guarda el nombre en la sesión
        
        String fechaNacimiento = credAlumno.getAlumno().getFecha_nacimiento(); // Accede al nombre del alumno
        session.setAttribute("fechaNacimiento", fechaNacimiento); // Guarda el nombre en la sesión

        String sexo = credAlumno.getAlumno().getSexo(); // Accede al nombre del alumno
        session.setAttribute("sexo", sexo); // Guarda el nombre en la sesión
        
        String nacionalidad = credAlumno.getAlumno().getNacionalidad(); // Accede al nombre del alumno
        session.setAttribute("nacionalidad", nacionalidad); // Guarda el nombre en la sesión
         
        // Apoderado 
        String tipoDocumentoApoderado=credAlumno.getAlumno().getApoderado().getTipo_documento();
        session.setAttribute("tipoDocumentoApoderado", tipoDocumentoApoderado);
        
        String numeroDocumentoApoderado=credAlumno.getAlumno().getApoderado().getNumero_documento();
        session.setAttribute("numeroDocumentoApoderado", numeroDocumentoApoderado);
        
        String apellidoPaternoApoderado=credAlumno.getAlumno().getApoderado().getApellido_paterno();
        session.setAttribute("apellidoPaternoApoderado", apellidoPaternoApoderado); 
        
        String apellidoMaternoApoderado=credAlumno.getAlumno().getApoderado().getApellido_materno();
        session.setAttribute("apellidoMaternoApoderado", apellidoMaternoApoderado);
        
        String nombresApoderado = credAlumno.getAlumno().getApoderado().getNombres();// Accede al nombre del alumno
        session.setAttribute("nombresApoderado", nombresApoderado); // Guarda el nombre en la sesión
        
        String telefono = credAlumno.getAlumno().getApoderado().getTelefono_movil();// Accede al nombre del alumno
        session.setAttribute("telefono", telefono); // Guarda el nombre en la sesión
        
        String correo = credAlumno.getAlumno().getApoderado().getCorreo();// Accede al nombre del alumno
        session.setAttribute("correo", correo); // Guarda el nombre en la sesión
        
        String direccion = credAlumno.getAlumno().getApoderado().getDireccion();// Accede al nombre del alumno
        session.setAttribute("direccion", direccion); // Guarda el nombre en la sesión
        
        
        
        // Asegúrate de que el alumno tiene matrículas y accede a la primera
        List<matricula> matriculas = credAlumno.getAlumno().getMatriculas();
        if (!matriculas.isEmpty()) { // Verifica que la lista no esté vacía
            matricula primeraMatricula = matriculas.get(0); // Accede a la primera matrícula

            // Guarda en la sesión la información adicional de la matrícula
            session.setAttribute("estadoMatricula", primeraMatricula.getEstado());
            session.setAttribute("sede", primeraMatricula.getSede());
            session.setAttribute("turno", primeraMatricula.getTurno());
            session.setAttribute("grado", primeraMatricula.getGrado());
            session.setAttribute("nivel", primeraMatricula.getNivel());
            session.setAttribute("añoMatricula", primeraMatricula.getAño_matricula());
        }
        
        
        // Asegúrate de que el alumno tiene solicitudes y accede a la primera
        List<Solicitud> solicitudes = credAlumno.getAlumno().getSolicitudes();
        if (!solicitudes.isEmpty()) { // Verifica que la lista no esté vacía
            Solicitud primeraSolicitud = solicitudes.get(0); // Accede a la primera matrícula

            // Guarda en la sesión la información adicional de la matrícula
            session.setAttribute("estadoSolicitud", primeraSolicitud.getEstado());
        }

        // Asegúrate de que el alumno tiene pagos y accede a la primera
        List<pagomatricula> pagomatriculas = credAlumno.getAlumno().getPagomatriculas();
        if (!pagomatriculas.isEmpty()) { // Verifica que la lista no esté vacía
            pagomatricula primeraPago = pagomatriculas.get(0); // Accede a la primera matrícula

            // Guarda en la sesión la información adicional de la matrícula
            session.setAttribute("conceptoPago", primeraPago.getConcepto());
            session.setAttribute("montoFinal", primeraPago.getMonto_final());
            session.setAttribute("acuenta", primeraPago.getAcuenta());
            session.setAttribute("deuda", primeraPago.getDeuda());
            session.setAttribute("estadoPago", primeraPago.getEstado());
            session.setAttribute("voucherPath", primeraPago.getVoucher_path());
        }
        
        
        return "redirect:/loader?seccion=indexApoderado"; // Redirige si el login es exitoso
    } else {
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "apoderado"; // Vuelve a mostrar la página de login con un mensaje de error
    }
}



    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Invalida la sesión actual
        return "redirect:/loader?seccion=index"; // Redirige a la página de inicio de sesión
    }
}











    