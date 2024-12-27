package Utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    static XSSFWorkbook workbook;

    public static String[] GetURLUsernameAndPasswordFromExcel() {
        try {
            // Get the project's root directory
            String projectPath = System.getProperty("user.dir");

            // Construct the path to the Excel file within the project directory
          //  private static String path = System.getProperty("user.dir") + "/src/Main/java/Excel/Op_Employee.xlsx";

            String path = System.getProperty("user.dir") +  "\\src\\main\\resources\\data.XLSX.xlsx";  
            // Load the workbook
            workbook = new XSSFWorkbook(path);
            XSSFSheet sheet = workbook.getSheetAt(0); // Assuming the data is on the first sheet

            // Fetch URL, username, and password from the Excel sheet
            String url = sheet.getRow(1).getCell(0).getStringCellValue();
            String userName = sheet.getRow(1).getCell(1).getStringCellValue();
            String password = sheet.getRow(1).getCell(2).getStringCellValue();
            String attributes = sheet.getRow(1).getCell(3).getStringCellValue();

            workbook.close();  // Close the workbook to avoid memory leaks

            // Return the values as an array
            return new String[]{url, userName, password, attributes};

        } catch (Exception exp) {
            // Handle exceptions
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            return null;
        }
    }
}


