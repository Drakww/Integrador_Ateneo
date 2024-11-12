/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance.integrador.servicio;
import avance.integrador.modelo.pagomatricula;
import avance.integrador.repositorio.PagomatriculaRepositorio;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joshep
 */
@Service
public class PagomatriculaServicio {

  @Autowired
    private PagomatriculaRepositorio pagoMatriculaRepositorio;

    public void actualizarVoucher(MultipartFile file, String numeroDocumento) throws IOException {
        String uploadDirectory = new File("src/main/resources/static/uploads/vouchers").getAbsolutePath();
        File directory = new File(uploadDirectory);

        // Crear la carpeta si no existe
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("No se pudo crear el directorio de destino: " + uploadDirectory);
        }

        // Generar un nombre único para el archivo
        String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDirectory + "/" + uniqueFileName;

        // Guarda el archivo en el servidor
        file.transferTo(new File(filePath));

        // Actualiza la ruta del voucher en la base de datos
        pagoMatriculaRepositorio.actualizarVoucherPath(uniqueFileName, numeroDocumento);
    }
    


        // Método para obtener pagos pendientes
      public List<Object[]> obtenerPagosPendientes() {
        return pagoMatriculaRepositorio.obtenerPagosPendientes();
    }

    @Transactional
    public void actualizarPago(Integer idPago, String estado, BigDecimal acuenta, BigDecimal deuda) {
        // Mensajes de depuración para verificar los valores que se están pasando
    
        
        
        pagoMatriculaRepositorio.actualizarPago(idPago, estado, acuenta, deuda);
    }
        
        
        
        
        
        
        
        
      public List<Object[]> filtrarSolicitudes(String numeroDocumento, String nivel, String grado, String estado) {
        return pagoMatriculaRepositorio.filtrarSolicitudes(numeroDocumento, nivel, grado, estado);
    }

  
   
      
      
      
      
      
            // Nuevo método para generar el archivo Excel
    public void generarExcel(HttpServletResponse response, String numeroDocumento, String nivel, String grado, String estado) throws IOException {
        // Obtener las solicitudes filtradas
        List<Object[]> solicitudes = filtrarSolicitudes(numeroDocumento, nivel, grado, estado);

        // Crear el archivo Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pagos");

        // Crear el estilo para los encabezados
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(createBoldFont(workbook));
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // Crear la fila del encabezado
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {
            "ID Pago", 
            "Concepto", 
            "N° Documento", 
            "Monto Final", 
            "Acuenta", 
            "Deuda", 
            "Fecha de Pago", 
            "Voucher", 
            "Estado"
        };

        // Establecer encabezados con estilo
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            cell.setCellStyle(headerStyle);
        }

        // Ajustar el ancho de las columnas automáticamente
        for (int i = 0; i < columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Llenar los datos en el Excel
        int rowIndex = 1; // Comenzar desde la segunda fila
        for (Object[] solicitud : solicitudes) {
            Row row = sheet.createRow(rowIndex++);
            // Asegurarse de que el arreglo de solicitud tenga el mismo tamaño que columnHeaders
            for (int i = 0; i < columnHeaders.length; i++) {
                Cell cell = row.createCell(i);
                // Comprobar si hay datos en la posición correspondiente y convertir a String
                if (i < solicitud.length) {
                    // Manejar tipos específicos si es necesario
                    if (solicitud[i] instanceof BigDecimal) {
                        cell.setCellValue(((BigDecimal) solicitud[i]).doubleValue()); // Convertir BigDecimal a double
                    } else if (solicitud[i] instanceof Date) {
                        // Formatear la fecha adecuadamente
                        cell.setCellValue((Date) solicitud[i]);
                        CellStyle dateStyle = workbook.createCellStyle();
                        CreationHelper createHelper = workbook.getCreationHelper();
                        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
                        cell.setCellStyle(dateStyle);
                    } else {
                        cell.setCellValue(solicitud[i] != null ? solicitud[i].toString() : ""); // Cualquier otro tipo a String
                    }
                } else {
                    cell.setCellValue(""); // Si no hay dato, dejar celda vacía
                }
            }
        }

        // Configurar la respuesta
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Pagos_Matrícula.xlsx");
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
