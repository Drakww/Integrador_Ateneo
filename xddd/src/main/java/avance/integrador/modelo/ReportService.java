/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.modelo;

/**
 *
 * @author Joshep
 */
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] generateMatriculaInicialReport() throws Exception {
        return generateReport("/reporte_matricula_inicial.jasper");
    }

    public byte[] generateMatriculaPrimariaReport() throws Exception {
        return generateReport("/reporte_matricula_primaria.jasper");
    }
    
    public byte[] generateMatriculaSecundariaReport() throws Exception {
        return generateReport("/reporte_matricula_secundaria.jasper");
    }
    
        public byte[] generateSolicitudInicialReport() throws Exception {
        return generateReport("/reporte_solicitud_inicial.jasper");
    }

    public byte[] generateSolicitudPrimariaReport() throws Exception {
        return generateReport("/reporte_solicitud_primaria.jasper");
    }
    
    public byte[] generateSolicitudSecundariaReport() throws Exception {
        return generateReport("/reporte_solicitud_secundaria.jasper");
    }
    
    public byte[] generatePagoInicialReport() throws Exception {
        return generateReport("/reporte_pago_inicial.jasper");
    }

    public byte[] generatePagoPrimariaReport() throws Exception {
        return generateReport("/reporte_pago_primaria.jasper");
    }
    
    public byte[] generatePagoSecundariaReport() throws Exception {
        return generateReport("/reporte_pago_secundaria.jasper");
    }
    

    private byte[] generateReport(String jasperFile) throws Exception {
        InputStream reportStream = getClass().getResourceAsStream(jasperFile);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, dataSource.getConnection());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
