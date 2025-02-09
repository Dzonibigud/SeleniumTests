package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WebDriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
//        driver.get(ConfigReader.getProperty("AE.url"));
    }

    @AfterEach
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}
