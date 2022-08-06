package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//This Class Help Me to locate the elements and use the main functionality like Wait , sendKey , Clear , get attributes if needed
public class PageBase {
    protected WebDriver driver;

    //Constructor
    public PageBase (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Wait until the element to be visible
    protected void waitForAction(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver , 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Click on element
    protected void click(WebElement element){
        waitForAction(element);
        element.click();
    }

    //Clear the text from field
    protected void clear(WebElement element){
        waitForAction(element);
        element.clear();
    }

    //Add the text inside the Field
    protected void sendKey(WebElement element , String txt){
        clear(element);
        element.sendKeys(txt);
    }

    //get any Attribute from specific Element
    protected String getAttribute(WebElement element , String Attribute){
        waitForAction(element);
        return element.getAttribute(Attribute);
    }


}
