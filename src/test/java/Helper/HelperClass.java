package Helper;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelperClass {
    FileInputStream FIS = null;


    private FileInputStream getFileInputStream(String filePath) {

        File sourceFile = new File(filePath);
        try {
            FIS = new FileInputStream(sourceFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error ,occured : " + e.getMessage());
        }

        return FIS;
    }


    public String getDataFromExcelSheet(String fileName, int row, int col) throws IOException {
        String filePath = "";
        if (fileName.equals("URL"))
            filePath = System.getProperty("user.dir") + "/URLs.xlsx";
        else if (fileName.equals("Data"))
            filePath = System.getProperty("user.dir") + "/assertionData.xlsx";

        FIS = getFileInputStream(filePath);

        XSSFWorkbook wb = new XSSFWorkbook(FIS);
        XSSFSheet sheet = wb.getSheetAt(0);

        return sheet.getRow(row).getCell(col).toString();
    }


}
