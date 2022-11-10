package testcases;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import base.TestBase;
import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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


 @BeforeMethod
    public static void initialize() throws Exception {
           setup();
 }
    @Test
    public void validateTest() throws InterruptedException, AWTException, MessagingException {

//     Open the makemytrip website
     driver.get(TestConfig.MMT_URL);

     Thread.sleep(3000);


     //Fill the source location
     WebElement fromField = driver.findElement(By.id("fromCity"));
     fromField.sendKeys(TestConfig.SOURCE);
     Thread.sleep(5000);
     Robot robot = new Robot();
     robot.keyPress(KeyEvent.VK_DOWN);
     robot.keyPress(KeyEvent.VK_ENTER);
     Thread.sleep(2000);

     //Fill the destination location
     WebElement toField = driver.findElement(By.id("toCity"));
     toField.sendKeys(TestConfig.DESTINATION);
     Thread.sleep(5000);
     robot.keyPress(KeyEvent.VK_DOWN);
     robot.keyPress(KeyEvent.VK_ENTER);
     Thread.sleep(3000);


//     Click Search button
     WebElement searchBtn = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
     JavascriptExecutor js = (JavascriptExecutor)driver;//create instance of javascript executor to do actions
     js.executeScript("arguments[0].click();", searchBtn);
     Thread.sleep(6000);


//     faced a pop up and to close it , click any where on webpage
     Actions action = new Actions(driver);
     action.moveByOffset(1000, 477).click().build().perform();
     Thread.sleep(2000);


//Sort the list of flights by departure
      WebElement sortByDeptartureBtn = driver.findElement(By.xpath("//span[(text()='Departure')]"));
       sortByDeptartureBtn.click();
       Thread.sleep(2000);


//       Scroll Down the webpage to get whole list of flights
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

 Thread.sleep(8000);

//   Getting prices string of all flights in list pricelistString
 List<WebElement> pricelistString = driver.findElements(By.xpath("//span[text()='View Prices']/parent::button/preceding-sibling::div/p"));
 ArrayList<Integer> priceList = new ArrayList();

// Iterate the list and remove ₹ and , so that it can be converted from string into integer and stored in integer arraylist
 for(WebElement w : pricelistString){
   String s = w.getText();
   s=s.replaceAll("[₹ ,]","");
   priceList.add(Integer.parseInt(s));
 }

// Sort the list to get second lowest price
   Collections.sort(priceList);

// Need the price in indian standard format to get the flight name of the second lowest price
     String secondlowestPrice = Integer.toString(priceList.get(1));
     String temp="";
     for(WebElement w : pricelistString){
            temp = w.getText();
            String s=temp.replaceAll("[₹ ,]","");
            if(s.equalsIgnoreCase(secondlowestPrice)){
                break;
            }
        }

//     Xpath of flight name of the second lowest price
        String xpathofAirlineForSpecificPrice = "//span[text()='View Prices']/parent::button/preceding-sibling::div/p[text()='"+temp+"']/parent::div/parent::div/parent::div/preceding-sibling::div/div/p";

       String flightNameForSecondLowestPrice= driver.findElement(By.xpath(xpathofAirlineForSpecificPrice)).getText();

//Printing the flight name of second lowest price
     System.out.println("The price of "+flightNameForSecondLowestPrice+" is "+secondlowestPrice+" which is second lowest price flight");



//For Validations , need to sort the list by price and get the flight name and price of second option

//Sort the list of flights by departure
        WebElement sortByPriceBtn = driver.findElement(By.xpath("//span[(text()='Price')]"));
        sortByPriceBtn.click();
        Thread.sleep(2000);

String actualFlightName = driver.findElement(By.xpath("//div[@id='flight_list_item_RKEY:6fb3f35a-8f6a-432c-be16-c0c05a78a53d:13_0']/div[1]/div[2]/div[1]/div/p[1]")).getText();
Assert.assertEquals(actualFlightName,flightNameForSecondLowestPrice);


String actualSecondLowestPrice = driver.findElement(By.xpath("//p[text()='"+actualFlightName+"']/parent::div/parent::div/following-sibling::div[3]/div/div/p")).getText();
Assert.assertEquals(actualSecondLowestPrice,secondlowestPrice);



 }
}
