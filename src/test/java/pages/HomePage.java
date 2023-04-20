package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public By theLatestNewsSelector = By.xpath("//h2[contains(text(),'The Latest')]");
    public By articlesSelector = By.cssSelector("article");
    public By articlesAuthorSelector = By.cssSelector(".river-byline__authors > span");
    public By articlesImageSelector = By.cssSelector(".post-block__media img");
    public By articlesTitleSelector = By.cssSelector("h2[class='post-block__title'] a");


    public HomePage(WebDriver driver) {
        super(driver);
        this.browserTitle = "TechCrunch | Startup and Technology News";
    }

}
