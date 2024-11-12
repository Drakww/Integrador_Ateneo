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
public class VoucherControlador {

    private final ResourceLoader resourceLoader;

    public VoucherControlador(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/vouchers/{nombreVoucher}")
    public ResponseEntity<Resource> cargarVoucher(@PathVariable String nombreVoucher) {
        Resource recurso = resourceLoader.getResource("file:src/main/resources/static/uploads/vouchers/" + nombreVoucher);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/png"); // Cambia esto según el tipo de archivo

        return new ResponseEntity<>(recurso, headers, HttpStatus.OK);
    }
}
