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

public class HomePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    protected void moveToElement(WebElement element){
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
    }
    protected void scroleToElement(WebElement element){
        actions.scrollToElement(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
    }
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}
