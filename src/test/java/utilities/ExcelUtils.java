package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet st;
	public  XSSFRow row ;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcelUtils(String path) {
		this.path = path;
	}
	
	
	public int getRowCount(String xlsheet) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		int rowCount = st.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	public int getCellCount( String xlsheet, int rownum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		row = st.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	public String getCellData(String xlsheet, int rownum, int colnum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		row = st.getRow(rownum);
		cell = row.getCell(colnum);
		String value;
		
		try {
			DataFormatter formatter = new DataFormatter();
			value = formatter.formatCellValue(cell);
			return value;
		}catch(Exception e) {
			value ="";
		}
		
		wb.close();
		fi.close();
		return value;
		
	}
	public void setCellData(String xlsheet, int rownum,int colnum,String value) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		row = st.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(value);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public void fillGreenColor(String xlsheet, int rownum, int colnum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		row = st.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String xlsheet,int rownum,int colnum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlsheet);
		row = st.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
