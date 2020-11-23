package com.expedia.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	private static FileInputStream fi;
	private static FileOutputStream fo;
	private static XSSFWorkbook wb;
	private static XSSFSheet ws;
	private static XSSFRow row;
	private static XSSFCell cell;

	public static int getRowCount(String filename, String sheetName) throws IOException {
		fi = new FileInputStream(filename);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount + 1;
	}

	public static int getCellCount(String filename, String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(filename);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;

	}

	public static String getCellValue(String filename, String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(filename);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		String cellData;
		try {
			DataFormatter format = new DataFormatter();// To Modify any other formats
			String data = format.formatCellValue(cell);
			return data;
		} catch (Exception e) {
			cellData = "";// If cell is empty we are avoiding the exception
		}
		wb.close();
		fi.close();
		return cellData;
	}

	public static void setCellValue(String filename, String sheetName, int rownum, int cellnum, String data)
			throws IOException {
		fi = new FileInputStream(filename);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		cell.setCellValue(data);

		fo = new FileOutputStream(filename);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
