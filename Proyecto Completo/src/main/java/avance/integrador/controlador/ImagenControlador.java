package avance.integrador.controlador;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Joshep
 */
@RestController
public class ImagenControlador {

    private final ResourceLoader resourceLoader;

    public ImagenControlador(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/imagenes/{nombreImagen}")
    public ResponseEntity<Resource> cargarImagen(@PathVariable String nombreImagen) {
        Resource recurso = resourceLoader.getResource("file:src/main/resources/static/imagenes/" + nombreImagen);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/png"); // Asegúrate de ajustar el tipo según sea necesario

        return new ResponseEntity<>(recurso, headers, HttpStatus.OK);
    }
}
