package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	FileInputStream fs;
	XSSFWorkbook wb;
	
	public ExcelReader() {
		try {
			fs = new FileInputStream(new File("./testData/UserDetails.xlsx"));
			wb = new XSSFWorkbook(fs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDataFromExcelSheet(int row,int column) {
		String value = wb.getSheetAt(0).getRow(row).getCell(column).getStringCellValue();
		return value;
	}
	public int totalRow() {
		return wb.getSheetAt(0).getPhysicalNumberOfRows();
	}
	public int totalColumn() {
		int columnSize = 0;
		columnSize = wb.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
		return columnSize;
		}
}
