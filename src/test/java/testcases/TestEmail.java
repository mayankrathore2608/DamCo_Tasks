package testcases;

import base.TestBase;
import config.TestConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestEmail extends TestBase {


@BeforeMethod
public void initialize() {
try{
 setup();
}
catch (Exception e ){
 e.printStackTrace();
}
}

   @Test
    public void validateTest() throws InterruptedException, AWTException, IOException {



 //      System.setProperty("webdriver.chrome.driver", "C:\\Users\\mayank.rathore\\Desktop\\driver\\chromedriver_104\\chromedriver.exe");
//  Opening the window in incognito mode
//      ChromeOptions o= new ChromeOptions();
//      o.addArguments("--incognito");
//      DesiredCapabilities c = new DesiredCapabilities();
//      c.setCapability(ChromeOptions.CAPABILITY, o);
//       driver = new ChromeDriver(o);
//       driver.manage().window().maximize();

//       Open the temp mail website
       driver.get(TestConfig.TEMP_MAIL_URL);

       Thread.sleep(10000);
//       Copy button to copy the mail id
       WebElement copyBtn = driver.findElement(By.id("click-to-copy"));
       copyBtn.click();
       Thread.sleep(1000);

//     Getting the window handle so that we can redirect back and move to yahoo account
       String parentWindow = driver.getWindowHandle();

//       Switch to new window and open yahoo mail
       driver.switchTo().newWindow(WindowType.WINDOW);
       driver.get(TestConfig.YAHOO_ACCOUNT_URL);
       Thread.sleep(3000);

//       Fill the credentials(username and pass ) and login into yahoo account
       WebElement gmailUsername = driver.findElement(By.id("login-username"));
       gmailUsername.sendKeys(TestConfig.YAHOO_EMAIL_USERNAME);
       Thread.sleep(2000);
       WebElement nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();
       Thread.sleep(3000);
       WebElement pass = driver.findElement(By.id("login-passwd"));
       pass.sendKeys(TestConfig.YAHOO_PASSWORD);
       nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();


//       Open yahoo mail
       driver.findElement(By.id("ymail")).click();
       Thread.sleep(2000);

//       Click Compose button to create mail
       WebElement composeBtn = driver.findElement(By.xpath("//a[@href='/d/compose/']"));
       composeBtn.click();
       Thread.sleep(2000);

//       Paste the mail in "To" field which is previously copied from temp mail
       WebElement toField = driver.findElement(By.id("message-to-field"));
       Robot robot = new Robot();
       Actions actions = new Actions(driver);
       actions.keyDown(Keys.CONTROL);
       actions.sendKeys("v");
       actions.keyUp(Keys.CONTROL);
       actions.build().perform();
       Thread.sleep(2000);

//       Fill the Subject of the mail
       WebElement subjectTxt = driver.findElement(By.xpath("//input[@data-test-id='compose-subject']"));
       subjectTxt.click();
       subjectTxt.sendKeys(TestConfig.SUBJECT);
       Thread.sleep(1000);

//       Fill the body of the mail
       WebElement bodyTxt = driver.findElement(By.xpath("//div[@data-test-id='rte']"));
       bodyTxt.sendKeys(TestConfig.BODY_TEXT);
       Thread.sleep(1000);

//       Click Send button to send the mail
       WebElement sendBtn = driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']"));
       sendBtn.click();
       Thread.sleep(3000);

//       Switch to Temp mail website
       driver.switchTo().window(parentWindow);
       Thread.sleep(180000);

//      Click and open the mail which is sent from yahoo account
       WebElement mail = driver.findElement(By.xpath("//span[@title='mayankrathore1996@yahoo.com']"));
       JavascriptExecutor js = (JavascriptExecutor)driver;
       js.executeScript("arguments[0].click();", mail);

//As Temp mail blocked me from accessing the mail so I am assuming , it has not blocked so now its time to validate
// the email id , subject and body of the mail
    String subjectText = driver.findElement(By.xpath("Xpath of subject")).getText();
    String bodyText = driver.findElement(By.xpath("Xpath of mail body")).getText();

//    Validating the subject and body
    Assert.assertEquals(subjectText,TestConfig.SUBJECT);
    Assert.assertEquals(bodyText,TestConfig.BODY_TEXT);

// Taking Screenshot of the mail
    TakesScreenshot scrShot =((TakesScreenshot)driver);
    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

//    Screenshot is stored in src/main/resources folder
    File DestFile=new File(TestConfig.SCREENSHOT_FILE_PATH);
    FileUtils.copyFile(SrcFile, DestFile);
   }

   @AfterMethod
 public void closeBrowser(){
    driver.quit();
   }



}