package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MenPage extends HomePage {
    protected WebDriver driver;

    public MenPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']") private WebElement accptButton;
    @FindBy(xpath = "//a[@data-text='Men']") private WebElement menCategory;
    @FindBy(xpath = "//a[@data-text='Women']") private WebElement womenCategory;
    @FindBy(xpath = "//a[@data-text='Jeans']") private WebElement jeansCategory;
    @FindBy(xpath = "//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']") private WebElement menSubCategoryJeans;

    public void closeCookies(){
            click(accptButton);
    }
    public void navigateToMenCategory(){
        moveToElement(menCategory);
    }
    public void navigateToMenJeans(){
        moveToElement(menSubCategoryJeans);
        click(menSubCategoryJeans);
    }
}
