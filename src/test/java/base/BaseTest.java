package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.WebDriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.get(ConfigReader.getProperty("AE.url"));
        //driver.get("https://www.ae.com/us/en");
    }
    @AfterMethod
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }
}
