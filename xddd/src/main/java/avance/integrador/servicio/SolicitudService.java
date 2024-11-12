package avance.integrador.servicio;

import avance.integrador.modelo.Solicitud;
import avance.integrador.repositorio.solicitudRepositorio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class SolicitudService {

    @Autowired
    private solicitudRepositorio solicitudRepo;

    // Método para obtener solicitudes pendientes
    public List<Object[]> obtenerSolicitudesPendientes() {
        return solicitudRepo.obtenerSolicitudesPendientes();
    }

    // Método para actualizar el estado de una solicitud
    @Transactional
    public void actualizarEstadoSolicitud(Integer idSolicitud, String estado) {
        solicitudRepo.actualizarEstadoSolicitud(idSolicitud, estado);
    }
    
    public List<Object[]> filtrarSolicitudes(String numeroDocumento, String nivel, String grado, String estado) {
    return solicitudRepo.filtrarSolicitudes(numeroDocumento, nivel, grado, estado);
    }
    
    
    
    
    
    
 // Nuevo método para generar el archivo Excel
  public void generarExcel(HttpServletResponse response, String numeroDocumento, String nivel, String grado, String estado) throws IOException {
    List<Object[]> solicitudes = filtrarSolicitudes(numeroDocumento, nivel, grado, estado);

    // Crear el archivo Excel
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Solicitudes");

    // Crear el estilo para los encabezados
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex()); // Color de fondo
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Relleno sólido
    headerStyle.setFont(createBoldFont(workbook)); // Fuente en negrita
    headerStyle.setAlignment(HorizontalAlignment.CENTER); // Centrar texto
    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Centrar verticalmente
    headerStyle.setBorderBottom(BorderStyle.THIN); // Borde inferior del encabezado
    headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // Color del borde inferior

    // Crear la fila del encabezado
    Row headerRow = sheet.createRow(0);
    String[] columnHeaders = {
        "ID Solicitud", 
        "Apoderado", 
        "Número Documento Apoderado", 
        "Alumno", 
        "Número Documento Alumno", 
        "Nivel", 
        "Grado", 
        "Teléfono Móvil", 
        "Fecha", 
        "Estado"
    };

    // Establecer encabezados con estilo
    for (int i = 0; i < columnHeaders.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(columnHeaders[i]);
        cell.setCellStyle(headerStyle); // Aplicar el estilo a cada celda del encabezado
    }

    // Ajustar el ancho de las columnas automáticamente
    for (int i = 0; i < columnHeaders.length; i++) {
        sheet.autoSizeColumn(i); // Ajustar el ancho de cada columna según el contenido
    }

    // Llenar los datos en el Excel
    int rowIndex = 1;
    for (Object[] solicitud : solicitudes) {
        Row row = sheet.createRow(rowIndex++);
        for (int i = 0; i < solicitud.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(solicitud[i] != null ? solicitud[i].toString() : "");
        }
    }

    // Configurar la respuesta
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=solicitudes.xlsx");
    workbook.write(response.getOutputStream());
    workbook.close();
}

// Método auxiliar para crear una fuente en negrita
private Font createBoldFont(Workbook workbook) {
    Font font = workbook.createFont();
    font.setBold(true); // Establecer texto en negrita
    font.setColor(IndexedColors.WHITE.getIndex()); // Color del texto en blanco
    return font;
}


}





     
    


