package uitests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;

import java.io.File;


public class UITestListener extends TestListenerAdapter {

    Logger logger = Logger.getLogger(this.getClass());
    WebDriver driver;

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("FAILED");

        driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");

        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        Utils.copyFile(file, new File(buildScreenshotPath(iTestResult)));
    }

    private String buildScreenshotPath(ITestResult iTestResult) {
        String classSubfolder = "/" + iTestResult.getTestClass().getName();
        String testSubFolder = "/" + iTestResult.getMethod().getMethodName();


        return new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append("/screenshots")
                .append(classSubfolder)
                .append(testSubFolder)
                .append("/image.png").toString();
    }
}
