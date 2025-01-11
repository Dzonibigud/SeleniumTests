import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
    @Test
    void firstUITest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://demoqa.com/", url);

        Thread.sleep(2000);
        driver.close();
    }
    @Test
    void secondUITest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();
        WebElement webForm = driver.findElement(By.xpath("//h5[text()='Elements']"));
        webForm.click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://demoqa.com/elements", url);

        Thread.sleep(2000);
        driver.quit();

    }
}
