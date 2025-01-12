import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sql.rowset.WebRowSet;
import java.time.Duration;
import java.util.List;

public class AddItemsTest {
    WebDriver driver;

    private static final String BASE_URL = "https://www.ae.com/us/en";

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() throws InterruptedException{
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void openHomePageTest(){
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        String curentUrl = driver.getCurrentUrl();

        Assertions.assertEquals(BASE_URL, curentUrl);
    }
    @Test
    void addWomenShoesItemTest() throws InterruptedException {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Actions action = new Actions(driver);
        Thread.sleep(5000);
        List<WebElement> popUpClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if(popUpClose.size() > 0){
            popUpClose.get(0).click();
        }
        WebElement womenCategory = driver.findElement(By.cssSelector("a[data-text = 'Women']"));
        action.moveToElement(womenCategory).perform();
        Thread.sleep(2000);

        WebElement womenShoesSubCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']"));
        ////a[@data-text = "Women"]/..//a[@data-item-link and text() = "Shoes"]
        ////a[@data-product-id = "0411_6604_309"]
        womenShoesSubCategory.click();
        Thread.sleep(5000);
        WebElement shoe = driver.findElement(By.cssSelector("div[data-product-id='0411_6604_309']"));
        action.scrollToElement(shoe).perform();
        Thread.sleep(3000);
        shoe.click();
        Thread.sleep(2000);
        WebElement color = driver.findElement(By.cssSelector("img[title = 'Medium Brown']"));
        color.click();
        Thread.sleep(2000);
        WebElement dropDown = driver.findElement(By.xpath("//span[@class ='dropdown-text']"));
        dropDown.click();
        Thread.sleep(1000);
        WebElement size = driver.findElement(By.xpath("//li[@data-value='0041765520']"));
        size.click();
        Thread.sleep(1000);
        List<WebElement> bag = driver.findElements(By.xpath("//button[@name='addToBag']"));
        bag.get(0).click();
        Thread.sleep(3000);
    }
    @Test
    void addMenJeansItemTest() throws InterruptedException {
        int productNumber = 28;
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Actions action = new Actions(driver);
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Thread.sleep(5000);
        List<WebElement> popUpClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (popUpClose.size() > 0) {
            popUpClose.get(0).click();
        }
        WebElement menCategory = driver.findElement(By.xpath("//a[@data-text='Men']"));
        action.moveToElement(menCategory).perform();
        WebElement jeans = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']")));
        jeans.click();
        //Thread.sleep(2000);
        longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Jeans')]")));
        List<WebElement> scrollToJeans = driver.findElements(By.xpath("//div[@data-product-id]"));
        action.scrollToElement(scrollToJeans.get(productNumber)).perform();
        longWait.until(ExpectedConditions.visibilityOf(scrollToJeans.get(productNumber)));
        action.moveToElement(scrollToJeans.get(productNumber)).perform();
        String id = scrollToJeans.get(productNumber).getAttribute("data-product-id");
        System.out.println(id);
        //Thread.sleep(2000);
        WebElement item = driver.findElement(By.xpath("//div[@data-product-id='" + id + "']"));
        String productName = scrollToJeans.get(productNumber).findElement(By.xpath(".//h3[@data-product-name]")).getText();
        WebElement quickShop = item.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        longWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@data-product-id='" + id + "']"), "Quick Shop"));
        quickShop.click();
        //Thread.sleep(2000);
        longWait.until(ExpectedConditions.textToBe(By.xpath("//h1[@data-test-product-name]"), productName));
        WebElement size = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class ='dropdown-text']")));
        size.click();
        //Thread.sleep(1000);
        List<WebElement> sizeOfJeans = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement product : sizeOfJeans) {
            if (!product.getText().contains("Out of Stock Online")) {
                product.click();
                break;
            }
        }
        Thread.sleep(2000);
        WebElement addToBag = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'add-to-bag']")));
        action.moveToElement(addToBag);
        Thread.sleep(1000);
        addToBag.click();
        Thread.sleep(2000);
        WebElement inTheBag = driver.findElement(By.xpath("//h2[text() = 'Added to bag!']"));
        String result = inTheBag.getText();
        Assertions.assertEquals("Added to bag!", result);
    }
}
