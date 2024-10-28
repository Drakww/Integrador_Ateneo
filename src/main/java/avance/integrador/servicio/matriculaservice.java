package avance.integrador.servicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avance.integrador.modelo.matricula;
import avance.integrador.repositorio.matricularepositorio;

@Service
public class matriculaservice {

	@Autowired
	private matricularepositorio repo;

   public List<matricula> obtenermatri(String clave){
	  if(clave!=null) {
	   return repo.findAll(clave);
   }
	  return repo.findAll();
	  }
   
   
}

