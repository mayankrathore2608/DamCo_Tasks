package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class TestBase {
    public static WebDriver driver;
    public static Properties properties;
    public TestBase(){
    try{
        properties = new Properties();
        FileInputStream fs = new FileInputStream("C:\\Users\\mayank.rathore\\IdeaProjects\\Damco_Framework\\src\\main\\java\\config\\config.properties");
        properties.load(fs);
    }catch (Exception e ){
        System.out.println(e);
    }
}
public static void setup(String browser) throws Exception{
 if(browser.equalsIgnoreCase("chrome")&&(driver==null)){
     System.setProperty("webdriver.chrome.driver", "C:\\Users\\mayank.rathore\\Desktop\\driver\\chromedriver_104\\chromedriver.exe");
     driver = new ChromeDriver();
  }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
  }
}
