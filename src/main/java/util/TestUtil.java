package util;

import base.TestBase;
//import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class TestUtil extends TestBase {
public static String FILE_NAME = "C:\\Users\\mayank.rathore\\IdeaProjects\\SirionOne\\src\\main\\java\\testdata\\CDRCreationData.xlsx";
public static WebDriverWait wb;

public static Object[][] getData() throws Exception{

    FileInputStream fs = new FileInputStream(FILE_NAME);
    Workbook book = WorkbookFactory.create(fs);
    Sheet sheet = book.getSheetAt(0);
    int noOfRows = sheet.getLastRowNum();
    int noOfColumns=sheet.getRow(0).getLastCellNum();
    Object[][] data = new Object[noOfRows][noOfColumns];
     for(int i=0;i<noOfRows;i++){
        for(int j=0;j<noOfColumns;j++){
            data[i][j]=sheet.getRow(i+1).getCell(j).toString();
        }
    }
    return data;
  }
public static void waitForElements(WebElement webElement){


//
//    java.time.Duration.ofSeconds(12);
//
//    wb = new WebDriverWait(driver,(java.time.Duration)30);
//


}





}
