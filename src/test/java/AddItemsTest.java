import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AddItemsTest {
    WebDriver driver;

    private static final String BASE_URL = "https://www.ae.com/us/en";

    private void closeWeekendPopup(){
        try {
            WebElement shadowDiv = driver.findElement(By.xpath("//div"));
            SearchContext shadowRoot = shadowDiv.getShadowRoot();
            shadowRoot.findElement(By.cssSelector("button.close")).click();
            } catch (Exception ignore){}
    }
    private void closeCoockies(){
        List<WebElement> popUpClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if(!popUpClose.isEmpty()){
            popUpClose.get(0).click();
        }
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
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
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        closeCoockies();
        WebElement womenCategory = driver.findElement(By.cssSelector("a[data-text = 'Women']"));
        action.moveToElement(womenCategory).perform();
        WebElement womenShoesSubCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']"));
        womenShoesSubCategory.click();
        List<WebElement> shoes = driver.findElements(By.xpath("//div[@data-product-id]"));
        action.moveToElement(shoes.get(22)).perform();
        WebElement quickShop = shoes.get(22).findElement(By.xpath(".//a[text()='Quick Shop']"));
        action.moveToElement(quickShop).click().perform();
        Thread.sleep(3000);
        closeWeekendPopup();
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.textToBe(By.xpath("//h2[text()='Quick Shop']"), "Quick Shop"));
        WebElement size = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class ='dropdown-text']")));
        //size.click();
        action.moveToElement(size).click().perform();
        List<WebElement> sizeOfShoes = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement shoe : sizeOfShoes) {
            if (!shoe.getText().contains("Out of Stock Online")) {
                shoe.click();
                break;
            }
        }
        WebElement addToBag = driver.findElement(By.xpath("//button[@name='add-to-bag']"));
        action.moveToElement(addToBag).click().perform();
    }
    @Test
    void addMenJeansItemTest() throws InterruptedException {
        int productNumber = 15;
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Actions action = new Actions(driver);
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        closeCoockies();
        WebElement menCategory = driver.findElement(By.xpath("//a[@data-text='Men']"));
        action.moveToElement(menCategory).perform();
        WebElement jeans = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']")));
        jeans.click();
        longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Jeans')]")));
        List<WebElement> scrollToJeans = driver.findElements(By.xpath("//div[@data-product-id]"));
        action.scrollToElement(scrollToJeans.get(productNumber)).perform();
        longWait.until(ExpectedConditions.visibilityOf(scrollToJeans.get(productNumber)));
        action.moveToElement(scrollToJeans.get(productNumber)).perform();
        String id = scrollToJeans.get(productNumber).getAttribute("data-product-id");
        System.out.println(id);
        WebElement item = driver.findElement(By.xpath("//div[@data-product-id='" + id + "']"));
        String productName = scrollToJeans.get(productNumber).findElement(By.xpath(".//h3[@data-product-name]")).getText();
        WebElement quickShop = item.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        longWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@data-product-id='" + id + "']"), "Quick Shop"));
        quickShop.click();
        longWait.until(ExpectedConditions.textToBe(By.xpath("//h1[@data-test-product-name]"), productName));
        Thread.sleep(3000);
        closeWeekendPopup();
        Thread.sleep(3000);
        WebElement size = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class ='dropdown-text']")));
        size.click();
        List<WebElement> sizeOfJeans = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement product : sizeOfJeans) {
            if (!product.getText().contains("Out of Stock Online")) {
                product.click();
                break;
            }
        }
        WebElement addToBag = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'add-to-bag']")));
        action.moveToElement(addToBag);
        addToBag.click();
        WebElement viewBag = driver.findElement(By.xpath("//button[@name='viewBag']"));
        viewBag.click();



        longWait.until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Shopping Bag']"),"Shopping Bag"));
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title ='PayPal']"));
        driver.switchTo().frame(iframe);
        WebElement payPal = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='link']")));
        new Actions(driver).moveToElement(payPal).perform();
        Thread.sleep(500);
        payPal.click();
    }
}
