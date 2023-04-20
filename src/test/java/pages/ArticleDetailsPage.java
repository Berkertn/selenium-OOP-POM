package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends BasePage {
    public By articleContentsParentSelector = By.cssSelector(".article-content");
    public By articlesContentSelector = By.cssSelector("p");
    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
        this.browserTitle = driver.getTitle();
    }
}
