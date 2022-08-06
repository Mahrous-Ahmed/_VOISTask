package Tests;

import Pages.GooglePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestScenario extends BaseTest {
    GooglePage googlePage;
    int countPage2;

    @BeforeClass
    public void Initialization() {
        googlePage = new GooglePage(driver);
        countPage2 = 0;
    }

    @Test(priority = 1)
    public void tc_step1() throws IOException {
        //Validate GO the web driver to Google
        driver.navigate().to((String) getDataFromExcelSheet("URL", 0, 0));
        Assert.assertEquals(driver.getCurrentUrl(),
                getDataFromExcelSheet("Data", 0, 0));
    }

    @Test(priority = 2, dependsOnMethods = {"tc_step1"})
    public void tc_step2() throws IOException {
        //Validate GO the web driver to Vodafone
        googlePage.searchBy("Vodafone");
        googlePage.clickOnSearch();
        Assert.assertTrue(driver.getCurrentUrl().
                contains(getDataFromExcelSheet("Data", 1, 0)));
    }

    @Test(priority = 3, dependsOnMethods = {"tc_step2"})
    public void tc_step3() throws IOException, InterruptedException {
        //Validate GO the web driver to Vodafone page number 2
        googlePage.goToNextPage();
    }

    @Test(priority = 4, dependsOnMethods = {"tc_step3"})
    public void tc_step4() throws IOException, InterruptedException {

        Assert.assertTrue(driver.getCurrentUrl().
                contains(getDataFromExcelSheet("Data", 1, 0)));
        Assert.assertTrue(googlePage.getPageNumber().
                contains(getDataFromExcelSheet("Data", 2, 0)));

        //Get Number number of results displayed on the second page
        countPage2 = googlePage.getCountOfSearch();

    }

    @Test(priority = 5, dependsOnMethods = {"tc_step4"})
    public void tc_step5() throws IOException, InterruptedException {
        //Validate we are in the Third page
        googlePage.goToNextPage();
        Assert.assertTrue(driver.getCurrentUrl().
                contains(getDataFromExcelSheet("Data", 1, 0)));
        Assert.assertTrue(googlePage.getPageNumber().
                contains(getDataFromExcelSheet("Data", 3, 0)));
    }

    @Test(priority = 6, dependsOnMethods = {"tc_step5"})
    public void tc_step6() throws IOException, InterruptedException {
        //Validate search results in page 2 and 3 are equal
        int page3 = googlePage.getCountOfSearch();
        Assert.assertEquals(countPage2 ,page3);
    }


    @AfterMethod
    public void TakeScreenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./ScreenShots/" + result.getName() + ".png"));

        }

    }

}
