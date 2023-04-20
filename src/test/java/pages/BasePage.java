package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String pageURL;
    protected String browserTitle;
    public By goParentNodeSelector = By.xpath("..");

    // Constructor
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.pageURL = DriverManager.baseUrl;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    //behaviours
    public String getPageUrl() {
        return this.pageURL;
    }

    public String getBrowserTitle() {
        return this.browserTitle;
    }

    public WebElement getElement(By selector) { // returning element
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }

    public String getElementText(WebElement element, By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return element.findElement(selector).getText();
    }
    public String getElementText(WebElement element) {
        return element.getText();
    }

    public void navigateToHrefValue(By selector) {
        driver.get(driver.findElement(selector).getAttribute("href"));
    }

    public String getSrcValue(WebElement element){
        return element.getAttribute("src");
    }

    public void setBrowserTitle(){
        this.browserTitle = driver.getTitle();
    }

}
