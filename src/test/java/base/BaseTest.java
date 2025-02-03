package base;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WebDriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.get("https://www.ae.com/us/en");
    }
    @After
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }
}
