package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private static String path;
    private static Workbook workbook;
    private static Sheet sheet;

    /**
     * This method opens Excel files
     */
    public static void openExcelFile(String fileName, String sheetName) {
        path = System.getProperty("user.dir") + "/src/test/resources/testdata/" + fileName + ".xlsx";
        try {
            FileInputStream file = new FileInputStream(path);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);

        } catch (FileNotFoundException e) {
            System.out.println("Path to excel file is invalid or the file is missing");
        } catch (IOException e) {
            System.out.println("Failed to load or save the excel file");
        }
    }

    /**
     * This method gets cell value using provided row and cell indexes
     *
     * @param row
     * @param cell
     * @return
     */
    public static String getValue(int row, int cell) {
        return sheet.getRow(row).getCell(cell).toString();
    }

    /**
     * This method sets cell value using provided row and cell indexes
     *
     * @param row
     * @param cell
     * @param value
     */
    public static void setValue(int row, int cell, String value) throws IOException {
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        Row row1;
        if (row >= numberOfRows) {
            row1 = sheet.createRow(row);
        } else {
            row1 = sheet.getRow(row);
        }

        int numberOfCells = row1.getPhysicalNumberOfCells();
        Cell cell1;
        if (cell >= numberOfCells) {
            cell1 = row1.createCell(cell);
        } else {
            cell1 = row1.getCell(cell);
        }
        cell1.setCellValue(value);

        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path);
            workbook.write(output);
        } catch (FileNotFoundException e) {
            System.out.println("Path to excel file is invalid or file is missing");
        } catch (IOException e) {
            System.out.println("Failed to save output file");
        } finally {
            assert output != null;
            output.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ExcelUtils.openExcelFile("TestData", "Test Data");
        System.out.println(ExcelUtils.getValue(4, 0));
        System.out.println(ExcelUtils.getValue(4, 1));

        ExcelUtils.setValue(5, 0, "Lauren Brown");

//        String path = System.getProperty("user.dir")+"/src/test/resources/testdata/TestData.xlsx";
//        try {
//            FileInputStream file = new FileInputStream(path);
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet1 = workbook.getSheet("Test Data");
//            System.out.println(sheet1.getRow(1).getCell(0));
//            System.out.println(sheet1.getRow(1).getCell(1));
//            System.out.println(sheet1.getRow(1).getCell(2));
//
//            System.out.println(sheet1.getPhysicalNumberOfRows());
//
//            sheet1.createRow(4).createCell(0).setCellValue("Harsh Patel");
//            sheet1.getRow(4).createCell(1).setCellValue("harsh.patel@gmail.com");
//            sheet1.getRow(4).createCell(2).setCellValue("123 Michigan Ave, Los Angeles, CA");
//
//            FileOutputStream output = new FileOutputStream(path);
//            workbook.write(output);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Path to excel file is invalid or the file is missing");
//        } catch (IOException e) {
//            System.out.println("Failed to load or save the excel file");
//        }
    }
}
