package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GooglePage extends PageBase {

    //This is all data member ->  locate the members
    private WebDriver driver;

    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(xpath = "(//input[@name = 'btnK'])[2]")
    WebElement searchBtn;

    @FindBy(xpath = "((//ul[@jsname= 'bw4e9b'])[1]/li)[1]")
    WebElement firstElementInSearchList;

    @FindBy(xpath = "//span[text() = 'Next']")
    WebElement nextPage;

    @FindBy( xpath = "//div[@id='rso']//h3")
    List<WebElement> searchList;

    @FindBy(id = "result-stats")
    WebElement pageNumber;


    //Constructor
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    //Enter the Keyword inside the Google Search field
    public void searchBy(String text){
        sendKey(searchField , text);
    }

    // Select first element from the list
    public void clickOnSearch(){
       click(firstElementInSearchList);
    }

    //Navigate to next page
    public void goToNextPage() throws InterruptedException {

        click(nextPage);
    }

    //Sum links which displayed
    public int getCountOfSearch(){
        return searchList.size();
    }

    //To see which page the user is currently using
    public String getPageNumber(){
        return pageNumber.getText();
    }

    //Use it to scroll
    private void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,6000)");

    }

}
