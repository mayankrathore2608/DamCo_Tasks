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

//public static Message sendEmail(String fromEmailAddress,
//                                String toEmailAddress)
//        throws MessagingException, IOException {
//        /* Load pre-authorized user credentials from the environment.
//           TODO(developer) - See https://developers.google.com/identity for
//            guides on implementing OAuth2 for your application.*/
//
//    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
//            .createScoped(GmailScopes.GMAIL_SEND);
//    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
//
//    // Create the gmail API client
//    Gmail service = new Gmail.Builder(new NetHttpTransport(),
//            GsonFactory.getDefaultInstance(),
//            requestInitializer)
//            .setApplicationName("Gmail samples")
//            .build();
//
//    // Create the email content
//    String messageSubject = "Test message";
//    String bodyText = "lorem ipsum.";
//
//    // Encode as MIME message
//    Properties props = new Properties();
//    Session session = Session.getDefaultInstance(props, null);
//    MimeMessage email = new MimeMessage(session);
//    email.setFrom(new InternetAddress(fromEmailAddress));
//    email.addRecipient(javax.mail.Message.RecipientType.TO,
//            new InternetAddress(toEmailAddress));
//    email.setSubject(messageSubject);
//    email.setText(bodyText);
//
//    // Encode and wrap the MIME message into a gmail message
//    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//    email.writeTo(buffer);
//    byte[] rawMessageBytes = buffer.toByteArray();
//    String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
//    Message message = new Message();
//    message.setRaw(encodedEmail);
//
//    try {
//        // Create send message
//        message = service.users().messages().send("me", message).execute();
//        System.out.println("Message id: " + message.getId());
//        System.out.println(message.toPrettyString());
//        return message;
//    } catch (GoogleJsonResponseException e) {
//        // TODO(developer) - handle error appropriately
//        GoogleJsonError error = e.getDetails();
//        if (error.getCode() == 403) {
//            System.err.println("Unable to send message: " + e.getDetails());
//        } else {
//            throw e;
//        }
//    }
//    return null;
//}
//}
//
//



    public static MimeMessage createEmail(String toEmailAddress,

                                      String fromEmailAddress,
                                      String subject,
                                      String bodyText)
        throws MessagingException {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    MimeMessage email = new MimeMessage(session);

    email.setFrom(new InternetAddress(fromEmailAddress));
    email.addRecipient(javax.mail.Message.RecipientType.TO,
            new InternetAddress(toEmailAddress));
    email.setSubject(subject);
    email.setText(bodyText);
    return email;
}





    @Test
    public void validateTest() throws InterruptedException, AWTException, MessagingException {


//   MimeMessage m =  createEmail("mayankrathore2608@gmail.com","i.mayankrathore@gmail.com","Testing","Hi Mayank");


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
