package testcases;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class FlightTestFromMMT extends TestBase {

private String mmtUrl = "https://www.makemytrip.com/flights/" ;

//
// @BeforeMethod
//    public static void initialize(String browser) throws Exception {
//           setup(browser);
// }
//

    @Test
    public void validateTest() throws InterruptedException, AWTException, MessagingException {

     System.setProperty("webdriver.chrome.driver", "C:\\Users\\mayank.rathore\\Desktop\\driver\\chromedriver_104\\chromedriver.exe");
     driver = new ChromeDriver();

     driver.get(mmtUrl);
     driver.manage().window().maximize();
     Thread.sleep(3000);
     Robot robot = new Robot();

     WebElement fromField = driver.findElement(By.id("fromCity"));
     fromField.sendKeys("Delhi");
     Thread.sleep(5000);
     robot.keyPress(KeyEvent.VK_DOWN);
     robot.keyPress(KeyEvent.VK_ENTER);
     Thread.sleep(2000);

     WebElement toField = driver.findElement(By.id("toCity"));
     toField.sendKeys("Mumbai");
     Thread.sleep(5000);
     robot.keyPress(KeyEvent.VK_DOWN);
     robot.keyPress(KeyEvent.VK_ENTER);
     Thread.sleep(3000);

     WebElement searchBtn = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
     JavascriptExecutor js = (JavascriptExecutor)driver;//create instance of javascript executor to do actions
     js.executeScript("arguments[0].click();", searchBtn);
     Thread.sleep(6000);

     Actions action = new Actions(driver);
     action.moveByOffset(1000, 477).click().build().perform();
     Thread.sleep(2000);


      WebElement sortByDeptartureBtn = driver.findElement(By.xpath("//span[(text()='Departure')]"));
       sortByDeptartureBtn.click();
       Thread.sleep(2000);

   long start = 0 ;
   while(true){
    js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    Thread.sleep(2000);
    long end = (Long)js.executeScript("return document.body.scrollHeight");
    if(start==end){
     break;
    }
    start=end;
   }

  Thread.sleep(2000);
 List<WebElement> list = driver.findElements(By.xpath("//span[text()='View Prices']/parent::button/preceding-sibling::div/p"));
 ArrayList<Integer> priceList = new ArrayList();

 for(WebElement w : list){
   String s = w.getText();
   s=s.replaceAll("[₹ ,]","");
   priceList.add(Integer.parseInt(s));
 }

 Collections.sort(priceList);
     for(Integer a : priceList){
         System.out.println(a);
     }

     String lowestPrice = Integer.toString(priceList.get(1));

     int currencyAmount = priceList.get(1);

//     Converting string price into indian standard
     Locale ind = new Locale("en","IN");
     Currency rupee = Currency.getInstance(ind);
     NumberFormat rupeeFormat = NumberFormat.getCurrencyInstance(ind);
     String s = rupeeFormat.format(currencyAmount);
     String[] ar=s.split("[.]");
     String lowestPriceString = ar[0] ;
     Thread.sleep(5000);

     lowestPrice = lowestPriceString.trim();

String xpath = "//span[@class='appendRight8']/parent::button/preceding-sibling::div/p[text()='"+lowestPriceString+"']/parent::div/parent::div/parent::div/preceding-sibling::div/div/p";
System.out.println(driver.findElement(By.xpath(xpath)).getText());



 }
}
