package uitests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UITestListener extends TestListenerAdapter {

    private WebDriver driver;

    private final Logger LOGGER = Logger.getLogger(UITestListener.class.getName());

    @Override
    public void onTestFailure(ITestResult tr) {
        LOGGER.log(Level.WARNING,
                "FAILED: " + tr.getStartMillis() + " " + tr.getEndMillis() + " " + tr.getStatus());

        this.driver = (WebDriver) tr.getTestContext().getAttribute("driver");
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = buildFilePath(tr);
        Utils.copyFile(screenshotFile, new File(path));

        LOGGER.log(Level.INFO, "Screenshot been saved to this location: " + path);
    }

    private String buildFilePath(ITestResult testResult) {
        String userDir = System.getProperty("user.dir");
        String folder = "/screenshots";
        String className = "/" + testResult.getTestClass().getName().replace(".", "/");
        String testMethodName = "/" + testResult.getMethod().getMethodName();

        String fileName = "/image" + new Timestamp(System.currentTimeMillis()) + ".png";
        return new StringBuilder()
                .append(userDir)
                .append(folder)
                .append(className)
                .append(testMethodName)
                .append(fileName).toString();
    }
}
