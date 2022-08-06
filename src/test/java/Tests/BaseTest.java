package Tests;

import Helper.HelperClass;
import Pages.GooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Locale;

public class BaseTest extends HelperClass {

    static WebDriver driver;



    @Parameters({"browserName"})
    @BeforeTest()
    public void setUp(String browserName) {
        browserName = browserName.toLowerCase(Locale.ROOT);

        System.out.println("Browser Name is "+ browserName);

        switch (browserName) {

            case ("chrome"):
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
                driver =  new ChromeDriver();
                break;


            case ("firefox"):
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/resources/geckodriver.exe");
                driver =  new FirefoxDriver();
                break;

            case ("edge"):
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;

        }

        driver.manage().window().maximize();

    }



    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}

