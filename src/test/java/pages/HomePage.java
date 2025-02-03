package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

import java.time.Duration;
import java.util.List;

public class HomePage extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private List<WebElement> accptButton = driver.findElements(By.id("onetrust-accept-btn-handler"));

    @FindBy(xpath = "//a[@data-text='Men']")
    private WebElement menCategory;

    @FindBy(xpath = "//a[@data-text='Women']")
    private WebElement womenCategory;

    @FindBy(xpath = "//a[@data-text='Jeans']")
    private WebElement jeansCategory;

    @FindBy(xpath = "//a[@data-text='Men']/..//a[@data-item-link and text() = 'Jeans']")
    private WebElement menSubCategoryJeans;


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }
    public void navigatgeToMenCategory(){
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(menCategory));
        action.moveToElement(menCategory).perform();
    }
    public void navigatgeToWomenCategory(){
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(womenCategory));
        action.moveToElement(womenCategory).perform();
    }
    public void navigatgeToJeansCategory(){
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(jeansCategory));
        action.moveToElement(jeansCategory).perform();
    }
    public void navigateToMenSubCategoryJeans(){
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(menSubCategoryJeans));
        action.moveToElement(menSubCategoryJeans).click().perform();
    }
    public void closeCoockies(){
        if(!accptButton.isEmpty()){
            accptButton.get(0).click();
        }
    }
}
