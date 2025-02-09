package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static constants.CommonConstants.OOS;

public class MenJeansPage extends BasePage {
    @FindBy(xpath = "//li[@data-value]")
    List<WebElement> sizes;
    @FindBy(xpath = "//div[@data-product-id]")
    private List<WebElement> jeans;
    @FindBy(xpath = "//div[@data-product-id='0112_6926_074']")
    private WebElement productLocator;
    @FindBy(xpath = "//span[@class ='dropdown-text']")
    private WebElement size;
    @FindBy(xpath = "//button[@name = 'add-to-bag']")
    private WebElement addToBag;
    @FindBy(xpath = "//button[@name='viewBag']")
    private WebElement viewBag;

    public MenJeansPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Add to bag first product")
    public ShoppingBagPage addToBagFirstProduct() throws InterruptedException {
        moveToProduct(0);
        selectFirstAvailableSizeOfProduct();
        return addToBag();
    }

    @Step("Open quick shop for a product")
    private void moveToProduct(int position) {
        moveToElement(jeans.get(position));
        WebElement nthProduct = jeans.get(position);
        WebElement quickShop = nthProduct.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        quickShop.click();
    }


    @Step("Select first available size of product")
    private void selectFirstAvailableSizeOfProduct() throws InterruptedException {
        Thread.sleep(1000);
        size.click();
        for (WebElement product : sizes) {
            if (!product.getText().contains(OOS)) {
                product.click();
                break;
            }
        }
    }

    @Step("Add to bag and open shopping bag page")
    private ShoppingBagPage addToBag() {
        clickWithWait(addToBag);
        clickWithWait(viewBag);
        wait.until(ExpectedConditions.textToBe(By.xpath("//h1"), "Shopping Bag"));
        return new ShoppingBagPage(driver);
    }
}
