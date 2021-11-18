import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    WebDriver driver;


    @Test
    public void testSearchAndVerifyResults() {
        String fullXpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input";
        String xPath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input";
        String selector = "body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input";

        driver.get("https://google.com/");
        String nameValue = "q";
        WebElement textInput = driver.findElement(By.name(nameValue));
        textInput.sendKeys("Portnov Computer School");
        textInput.submit();

        WebElement statsElement = driver.findElement(By.id("result-stats"));
        String wholeStatsText = statsElement.getText();
        String[] array = wholeStatsText.split(" ");
        String numberOfResults = array[1];
        String replacedComma = numberOfResults.replace(",", "");
        int parsedInteger = Integer.parseInt(replacedComma);

        Assert.assertTrue(parsedInteger > 1000);
    }

    @Parameters({"browser"})
    @BeforeSuite
    public void suiteSetup(String browser) {
        String os = System.getProperty("os.name");

        if (os.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/macos/m1/chromedriver4638");
            System.setProperty("webdriver.gecko.driver", "src/test/resources/macos/m1/geckodriverAarch64");
        }

        if (os.contains("Windows")) {
            //TODO: set chrome and geckodriver properties for windows
        }

        if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        }

        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        }


    }

    @Test
    public void testOSVersion() {
        String os = System.getProperty("os.name");
        System.out.println(os);
    }

    @Test
    public void testFirefox() {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://google.com/");
    }
}
