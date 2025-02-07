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



    public void moveToProduct(int pN){
        scroleToElement(jeans.get(pN));
        moveToElement(jeans.get(pN));
        By productLocator = By.xpath("//div[@data-product-id='" + pN + "']");
        WebElement item = driver.findElement(productLocator);
        WebElement quickShop = item.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        click(quickShop);
    }

}
