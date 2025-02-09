package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingBagPage extends BasePage {
    @FindBy(xpath = "//iframe[@title = 'PayPal']")
    private WebElement iFrame;
    @FindBy(xpath = "//div[@role='link']")
    private WebElement payPal;

    public ShoppingBagPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open payPal window")
    public void payPalButton() {
        driver.switchTo().frame(iFrame);
        moveToElement(payPal);
        payPal.click();
    }
}
