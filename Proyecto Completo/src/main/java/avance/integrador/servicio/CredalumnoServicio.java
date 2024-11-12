package avance.integrador.servicio;

import avance.integrador.modelo.credalumno;
import avance.integrador.repositorio.credalumnoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredalumnoServicio {

    @Autowired
    private credalumnoRepositorio credalumnoRepositorio;

    // Agrega BCryptPasswordEncoder como un bean para cifrar la contraseña
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public credalumno guardarCredalumno(credalumno credalumno) {
        // Cifra la contraseña antes de guardar
        String hashedPassword = passwordEncoder.encode(credalumno.getContrasena());
        credalumno.setContrasena(hashedPassword);
        return credalumnoRepositorio.save(credalumno);
    }

    public List<Object[]> filtrarCredenciales(String numeroDocumento) {
        return credalumnoRepositorio.filtrarCredenciales(numeroDocumento);
    }

    @Transactional
    public void actualizarCredalumno(String idCredalumno, String usuario, String contrasena) {
        Integer id = Integer.parseInt(idCredalumno);
        credalumno cred = credalumnoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Credencial no encontrada"));

        // Cifra la contraseña antes de actualizar
        String hashedPassword = passwordEncoder.encode(contrasena);
        
        cred.setUsuario(usuario);
        cred.setContrasena(hashedPassword);
        credalumnoRepositorio.save(cred);
    }
}
