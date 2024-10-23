package avance.integrador.controlador;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImagenControlador {

    private final ResourceLoader resourceLoader;

    public ImagenControlador(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/imagenes/{nombreImagen}")
    public Resource cargarImagen(@PathVariable String nombreImagen) {
        return resourceLoader.getResource("file:src/main/resources/static/imagenes/" + nombreImagen);
    }
}
