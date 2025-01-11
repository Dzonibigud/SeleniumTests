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
        int productNumber = 0;
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Actions action = new Actions(driver);
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner-id")));
        Thread.sleep(5000);
        List<WebElement> popUpClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (!popUpClose.isEmpty()) {
            popUpClose.get(0).click();
        }
        WebElement menCategory = driver.findElement(By.xpath("//a[@data-text='Men']"));
        action.moveToElement(menCategory).perform();
        WebElement jeans = driver.findElement(By.xpath("//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']"));
        jeans.click();
        longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Jeans')]")));
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-product-id]"));
        action.moveToElement(products.get(productNumber)).perform();
        String id = products.get(productNumber).getAttribute("data-product-id");
        String productName = products.get(productNumber).findElement(By.xpath(".//h3[@data-product-name]")).getText();
        System.out.println("ProductId = " + id);
        System.out.println("ProductName = " + productName);
        WebElement firstProduct = products.get(0);
        WebElement quickShop = firstProduct.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        quickShop.click();
        //it's necessary to add this step to wait until the QuickShop menu fully loaded before click at size
        longWait.until(ExpectedConditions.textToBe(By.xpath("//h1[@data-test-product-name]"), productName));
        WebElement sizeButton = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-test-dropdown-toggle]")));
        sizeButton.click();
        List<WebElement> sizeOfJeans = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement size : sizeOfJeans) {
            if (!size.getText().contains("Out of Stock Online")) {
                size.click();
                break;
            }
        }
        Thread.sleep(2000);
        WebElement addToBag = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'add-to-bag']")));
        action.moveToElement(addToBag);
        addToBag.click();
        WebElement inTheBag = driver.findElement(By.xpath("//h2[text() = 'Added to bag!']"));
        String result = inTheBag.getText();
        Assertions.assertEquals("Added to bag!", result);
    }
}
