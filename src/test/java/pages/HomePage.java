package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;

public class HomePage extends BasePage {
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement oneTrustCookieCloseButton;
    @FindBy(xpath = "//a[@data-text='Men']")
    private WebElement menCategory;
    @FindBy(xpath = "//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']")
    private WebElement menSubCategoryJeans;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open homepage")
    public void open() {
        driver.get(ConfigReader.getProperty("AE.url"));
    }

    @Step("Close cookies oneTrust popup")
    public void closeCookies() {
        clickWithWait(oneTrustCookieCloseButton);
    }

    @Step("Open men's jeans subcategory")
    public MenJeansPage navigateToMenJeans() {
        moveToElement(menCategory);
        clickWithWait(menSubCategoryJeans);
        return new MenJeansPage(driver);
    }
}
