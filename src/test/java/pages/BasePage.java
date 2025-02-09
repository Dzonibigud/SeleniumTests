package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /*
    Click and wait for 5 seconds to element to be clickable
     */
    protected void clickWithWait(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}
