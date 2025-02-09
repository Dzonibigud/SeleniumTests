package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MenJeansPage extends HomePage{
    protected WebDriver driver;

    public MenJeansPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-product-id]") private List<WebElement> jeans;
    @FindBy(xpath = "//div[@data-product-id='0112_6926_074']") private WebElement productLocator;
    @FindBy(xpath = "//span[@class ='dropdown-text']") private WebElement size;
    @FindBy(xpath = "//li[@data-value]") List<WebElement> sizeOfJeans;
    @FindBy(xpath = "//button[@name = 'add-to-bag']") private WebElement addToBag;
    @FindBy(xpath = "//button[@name='viewBag']")private WebElement viewBag;
    @FindBy(xpath = "//iframe[@title ='PayPal']") private WebElement iFrame;
    @FindBy(xpath = "//div[@role='link']")private WebElement payPal;

    public void moveToProduct(int pN){
        scroleToElement(jeans.get(pN));
        moveToElement(jeans.get(pN));
        WebElement quickShop = productLocator.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        click(quickShop);
    }
    public void selectSizeOfJeans(){
        click(size);
        for (WebElement product : sizeOfJeans) {
            if (!product.getText().contains("Out of Stock Online")) {
                product.click();
                break;
            }
        }
    }
    public void addToBag(){
        click(addToBag);
        click(viewBag);
    }
    public void payPalButton() throws InterruptedException {
        driver.switchTo().frame(iFrame);
        scroleToElement(payPal);
        moveToElement(payPal);
        Thread.sleep(500);
        click(payPal);
    }
}
