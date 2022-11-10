package pages;

import base.TestBase;
//import jdk.nashorn.internal.runtime.ECMAException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestBase {

    @FindBy(name = "username_login")
    WebElement username;
    @FindBy(id="password-field")
    WebElement password;
    @FindBy(id="loginButton")
    WebElement loginButton;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
    public void login(String user,String pass)throws Exception{
//        Thread.sleep(1000);
        username.sendKeys(user);
//        Thread.sleep(1000);
           password.sendKeys(pass);
//        Thread.sleep(1000);
           loginButton.click();
//        Thread.sleep(1000);
    }
}
