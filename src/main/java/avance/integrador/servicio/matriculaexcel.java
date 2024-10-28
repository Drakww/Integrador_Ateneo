package avance.integrador.servicio;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import avance.integrador.modelo.matricula;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class matriculaexcel{

    public void export(List<matricula> matri, HttpServletResponse response) throws IOException {
        

        // Crear el archivo Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            // Crear encabezado
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Apoderado");
            headerRow.createCell(1).setCellValue("Alumno");
            headerRow.createCell(2).setCellValue("N° Documento");
            headerRow.createCell(3).setCellValue("Nivel");
            headerRow.createCell(4).setCellValue("Grado");
            headerRow.createCell(5).setCellValue("Tipo solicitud");
            headerRow.createCell(6).setCellValue("Estado");
            headerRow.createCell(7).setCellValue("Año");

            // Llenar el archivo con datos de la base de datos
            int rowIdx = 1;
            for (matricula matris : matri) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue( matris.getApoderado().getNombres());
                row.createCell(1).setCellValue(matris.getAlumno().getNombres() );
                row.createCell(2).setCellValue(matris.getAlumno().getNumero_documento());
                row.createCell(3).setCellValue(matris.getNivel() );
                row.createCell(4).setCellValue( matris.getGrado());
                row.createCell(5).setCellValue(matris.getSede() );
                row.createCell(6).setCellValue( matris.getEstado() );
                row.createCell(7).setCellValue( matris.getAño_matricula().toString());
            }

            // Escribir el archivo en el flujo de salida de respuesta HTTP
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo Excel");
        }
    }
}

