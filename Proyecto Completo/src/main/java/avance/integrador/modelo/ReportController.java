/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

/**
 *
 * @author Joshep
 */
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/matriculas-inicial")
    public ResponseEntity<byte[]> generateMatriculaInicialReport() {
        return generateReportResponse(() -> reportService.generateMatriculaInicialReport(), "matriculas_inicial.pdf");
    }

    @GetMapping("/matriculas-primaria")
    public ResponseEntity<byte[]> generateMatriculaPrimariaReport() {
        return generateReportResponse(() -> reportService.generateMatriculaPrimariaReport(), "matriculas_primaria.pdf");
    }
    
     @GetMapping("/matriculas-secundaria")
    public ResponseEntity<byte[]> generateMatriculaSecundariaReport() {
        return generateReportResponse(() -> reportService.generateMatriculaSecundariaReport(), "matriculas_secundaria.pdf");
    }
    
    @GetMapping("/solicitudes-inicial")
    public ResponseEntity<byte[]> generateSolicitudInicialReport() {
        return generateReportResponse(() -> reportService.generateSolicitudInicialReport(), "solicitudes_inicial.pdf");
    }

    @GetMapping("/solicitudes-primaria")
    public ResponseEntity<byte[]> generateSolicitudPrimariaReport() {
        return generateReportResponse(() -> reportService.generateSolicitudPrimariaReport(), "solicitudes_primaria.pdf");
    }
    
     @GetMapping("/solicitudes-secundaria")
    public ResponseEntity<byte[]> generateSolicitudSecundariaReport() {
        return generateReportResponse(() -> reportService.generateSolicitudSecundariaReport(), "solicitudes_secundaria.pdf");
    }
    
    @GetMapping("/pagos-inicial")
    public ResponseEntity<byte[]> generatePagoInicialReport() {
        return generateReportResponse(() -> reportService.generatePagoInicialReport(), "pagos_inicial.pdf");
    }

    @GetMapping("/pagos-primaria")
    public ResponseEntity<byte[]> generatePagoPrimariaReport() {
        return generateReportResponse(() -> reportService.generatePagoPrimariaReport(), "pagos_primaria.pdf");
    }
    
     @GetMapping("/pagos-secundaria")
    public ResponseEntity<byte[]> generatePagoSecundariaReport() {
        return generateReportResponse(() -> reportService.generatePagoSecundariaReport(), "pagos_secundaria.pdf");
    }
    

    private ResponseEntity<byte[]> generateReportResponse(ReportGenerator generator, String fileName) {
        try {
            byte[] report = generator.generate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + fileName);
            return ResponseEntity.ok().headers(headers).contentType(org.springframework.http.MediaType.APPLICATION_PDF).body(report);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @FunctionalInterface
    interface ReportGenerator {
        byte[] generate() throws Exception;
    }
}