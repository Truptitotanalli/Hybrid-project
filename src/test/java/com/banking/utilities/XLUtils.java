package com.banking.utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XLUtils {

    // Method to determine if a file is .xls or .xlsx and create a Workbook instance
    private static Workbook getWorkbook(String xlFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(xlFilePath)) {
            Workbook workbook = null;
            // 2 types of excel .xls and .xlsx 
            if (xlFilePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis); // For .xlsx files
            } else if (xlFilePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis); // For .xls files
            }
            return workbook;
        }
    }

    // Method to get the row count of an Excel sheet
    public static int getRowCount(String xlFilePath, String sheetName) throws IOException {
        try (Workbook workbook = getWorkbook(xlFilePath)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet.getPhysicalNumberOfRows();
        }
    }

    // Method to get the column count of a sheet in Excel based on a specific row
    public static int getColumnCount(String xlFilePath, String sheetName, int rowNum) throws IOException {
        try (Workbook workbook = getWorkbook(xlFilePath)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            
            if (row == null) {
                return 0; // Return 0 if the row is empty or doesn't exist
            }

            return row.getPhysicalNumberOfCells();
        }
    }

    // Method to get the data from a cell in Excel
    public static String getCellData(String xlFilePath, String sheetName, int rowNum, int colNum) throws IOException {
        try (Workbook workbook = getWorkbook(xlFilePath)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return "";  // Return empty string if the row is missing
            }
            Cell cell = row.getCell(colNum);

            if (cell == null) {
                return "";  // Return empty string if the cell is empty
            }

            // Ensure cell contains string data; if not, handle other types (numeric, etc.)
            String cellData = "";
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();   // Get string cell value
                    break;
                case NUMERIC:
                    cellData = String.valueOf(cell.getNumericCellValue());   // Handle numeric data as a string
                    break;
                default:
                    break;  // Handle other cell types (like boolean or formula) if needed
            }
            return cellData;
        }
    }
}


  /*  // Method to set data in an Excel cell
    public static void setCellData(String xlFilePath, String sheetName, int rowNum, int colNum, String data) throws IOException {
        Workbook workbook = getWorkbook(xlFilePath);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);

        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }


    // Method to add a new row to an Excel sheet
    public static void addRow(String xlFilePath, String sheetName, String[] rowData) throws IOException {
        Workbook workbook = getWorkbook(xlFilePath);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row newRow = sheet.createRow(rowCount);

        for (int i = 0; i < rowData.length; i++) {
            newRow.createCell(i).setCellValue(rowData[i]);
        }

        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    // Method to delete a row from an Excel sheet
    public static void deleteRow(String xlFilePath, String sheetName, int rowNum) throws IOException {
        Workbook workbook = getWorkbook(xlFilePath);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row != null) {
            sheet.removeRow(row);
        }

        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    // Method to create a new Excel file
    public static void createNewExcelFile(String xlFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook(); // Using HSSFWorkbook for .xls files
        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }*/

