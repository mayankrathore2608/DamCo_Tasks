package pages;

import base.TestBase;
//import com.sun.tools.javac.comp.Resolve;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateCDRPage extends TestBase {

    @FindBy(xpath = "//div[@id=\'headerNavbar\']/div[1]/a")
    WebElement dashedIcon;

    @FindBy(xpath = "//div[@id='quick-nav']/div/ul[2]/li[3]/a/span[1]")
    WebElement authoringLink ;

    @FindBy(id = "list_node_279")
    WebElement cdrLink ;

    @FindBy(xpath = "//div[@id=\"jx-create-btn-list\"]/a")
    WebElement createButton ;

    @FindBy(xpath = "//div[@id=\"jx-create-btn-list\"]/ul/li/a")
    WebElement createCDRButton ;

    @FindBy(xpath = "//div[@id='jx-create-btn-list']/ul/li/ul/li[1]/a")
    WebElement singleButton;

    public CreateCDRPage(){
        PageFactory.initElements(driver,this);
    }

    public void openCDRCreatePage(){
        dashedIcon.click();
        authoringLink.click();
        cdrLink.click();
        createButton.click();
        createCDRButton.click();
        singleButton.click();
    }

    public void CDRCreation(){

//        Select select = new Select();




    }




}
