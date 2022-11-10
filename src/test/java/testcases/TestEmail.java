package testcases;

import base.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class TestEmail extends TestBase {

private String url = "https://temp-mail.org/en/";

   @Test
    public void validateTest() throws InterruptedException, AWTException {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\mayank.rathore\\Desktop\\driver\\chromedriver_104\\chromedriver.exe");

      ChromeOptions o= new ChromeOptions();
      // add Incognito parameter
      o.addArguments("--incognito");
      // DesiredCapabilities object
      DesiredCapabilities c = new DesiredCapabilities();
      //set capability to browser
      c.setCapability(ChromeOptions.CAPABILITY, o);

       driver = new ChromeDriver(o);
       driver.manage().window().maximize();
       driver.get("https://temp-mail.org/en/");

       Thread.sleep(10000);
       WebElement copyBtn = driver.findElement(By.id("click-to-copy"));
       copyBtn.click();
       Thread.sleep(1000);

       String parentWindow = driver.getWindowHandle();
       driver.switchTo().newWindow(WindowType.WINDOW);
       driver.get("https://login.yahoo.com/");
       Thread.sleep(3000);

       WebElement gmailUsername = driver.findElement(By.id("login-username"));
       gmailUsername.sendKeys("mayankrathore1996@yahoo.com");
       Thread.sleep(2000);
       WebElement nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();
       Thread.sleep(3000);
       WebElement pass = driver.findElement(By.id("login-passwd"));
       pass.sendKeys("javaissecure");
       nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();
       driver.findElement(By.id("ymail")).click();
       Thread.sleep(2000);

       WebElement composeBtn = driver.findElement(By.xpath("//a[@href='/d/compose/']"));
       composeBtn.click();
       Thread.sleep(2000);

       WebElement toField = driver.findElement(By.id("message-to-field"));
       Robot robot = new Robot();
       Actions actions = new Actions(driver);
       actions.keyDown(Keys.CONTROL);
       actions.sendKeys("v");
       actions.keyUp(Keys.CONTROL);
       actions.build().perform();
       Thread.sleep(2000);

       WebElement subjectTxt = driver.findElement(By.xpath("//input[@data-test-id='compose-subject']"));
       subjectTxt.click();
       subjectTxt.sendKeys("Testing");
       Thread.sleep(1000);

       WebElement bodyTxt = driver.findElement(By.xpath("//div[@data-test-id='rte']"));
       bodyTxt.sendKeys("Hi Mayank \n I am writing this to u because i am testing this functionality ");
       Thread.sleep(1000);

       WebElement sendBtn = driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']"));
       sendBtn.click();
       Thread.sleep(3000);
       driver.switchTo().window(parentWindow);
       Thread.sleep(180000);

       WebElement mail = driver.findElement(By.xpath("//span[@title='mayankrathore1996@yahoo.com']"));
       JavascriptExecutor js = (JavascriptExecutor)driver;//create instance of javascript executor to do actions
       js.executeScript("arguments[0].click();", mail);



   }
}