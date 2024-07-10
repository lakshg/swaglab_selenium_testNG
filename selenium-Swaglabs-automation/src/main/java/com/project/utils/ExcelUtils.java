package com.project.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * ExcelUtils provides utility methods to read data from Excel files.
 */
public class ExcelUtils {

    /**
     * Reads data from the specified Excel sheet and returns it as a list of maps.
     * Each map represents a row with column names as keys and cell values as values.
     *
     * @param filePath the path to the Excel file
     * @param sheetName the name of the sheet to read data from
     * @return a list of maps containing the data from the specified Excel sheet
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        // List to store the test data
        List<Map<String, String>> testDataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        // Iterate through each row in the sheet
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> dataMap = new HashMap<>();
            // Iterate through each cell in the row
            for (int j = 0; j < row.getLastCellNum(); j++) {
                dataMap.put(headerRow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
            }
            testDataList.add(dataMap);
        }
        // Close the workbook and input stream
        workbook.close();
        fis.close();

        return testDataList;
    }
}
