/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Joshep
 */
@Component
public class JasperReportCompiler {

    @PostConstruct
    public void compileReports() {
        compileReport("reporte_matricula_inicial.jrxml", "reporte_matricula_inicial.jasper");
        compileReport("reporte_matricula_primaria.jrxml", "reporte_matricula_primaria.jasper");
        compileReport("reporte_matricula_secundaria.jrxml", "reporte_matricula_secundaria.jasper");
        compileReport("reporte_solicitud_inicial.jrxml", "reporte_solicitud_inicial.jasper");
        compileReport("reporte_solicitud_primaria.jrxml", "reporte_solicitud_primaria.jasper");
        compileReport("reporte_solicitud_secundaria.jrxml", "reporte_solicitud_secundaria.jasper");
        compileReport("reporte_pago_inicial.jrxml", "reporte_pago_inicial.jasper");
        compileReport("reporte_pago_primaria.jrxml", "reporte_pago_primaria.jasper");
        compileReport("reporte_pago_secundaria.jrxml", "reporte_pago_secundaria.jasper");
    }

    private void compileReport(String jrxmlFile, String jasperFile) {
        Path outputPath = Paths.get("target/classes/" + jasperFile); 
        try (InputStream reportStream = getClass().getResourceAsStream("/" + jrxmlFile);
             OutputStream outputStream = Files.newOutputStream(outputPath)) {
            
            if (reportStream != null) {
                JasperCompileManager.compileReportToStream(reportStream, outputStream);
                System.out.println("Reporte compilado exitosamente en " + outputPath);
            } else {
                System.err.println("No se encontró el archivo " + jrxmlFile);
            }
        } catch (Exception e) {
            System.err.println("Error al compilar el reporte " + jrxmlFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
