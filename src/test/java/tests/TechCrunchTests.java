package tests;

import org.openqa.selenium.WebElement;
import pages.*;
import org.testng.annotations.*;
import java.util.ArrayList;

public class TechCrunchTests extends BaseTest {

    //Declaring Poms

    HomePage homePage;
    ArticleDetailsPage articleDetailsPage;

    @Test(enabled = true, description = "Sure To Be Navigated To Right Site", priority = 0)
    void checkNavigationToTheRightSite() {
        //POM initialization
        homePage = new HomePage(driver);

        // Assertion For Navigated To The Right Site
        softAssertion.assertEquals(driver.getTitle(), homePage.getBrowserTitle());
        softAssertion.assertEquals(driver.getCurrentUrl(), homePage.getPageUrl());
        softAssertion.assertAll("SoftAssertions performed.");
    }

    @Test(enabled = true, description = "Check Every Article Under The Latest Section Has Authors Name and Image", priority = 0, dependsOnMethods = "checkNavigationToTheRightSite")
    void checkArticlesProperties() {
        //POM initialization

        homePage = new HomePage(driver);

        WebElement theLatestElement = homePage.getElement(homePage.theLatestNewsSelector);
        WebElement parentOfArticleElement = theLatestElement.findElement(homePage.goParentNodeSelector);
        ArrayList<WebElement> articles = (ArrayList<WebElement>) parentOfArticleElement.findElements(homePage.articlesSelector);

        //iteration for every article item and soft assertions
        for (WebElement article : articles) {

            if (article.findElement(homePage.articlesAuthorSelector).isDisplayed()) {

                WebElement articlesAuthor = article.findElement(homePage.articlesAuthorSelector);
                String articlesAuthorsName = homePage.getElementText(articlesAuthor);

                WebElement articlesImage = article.findElement(homePage.articlesImageSelector);

                //softAssertions
                softAssertion.assertTrue(articlesAuthorsName.length() >= 1, "authors name element text content is empty");
                softAssertion.assertTrue(homePage.getSrcValue(articlesImage).startsWith(baseUrl));
                softAssertion.assertTrue(articlesImage.isDisplayed());
                softAssertion.assertTrue(articlesImage.isEnabled());
                softAssertion.assertAll("SoftAssertions performed.");
            }
        }
    }

    @Test(enabled = true, description = "Check One Of The Articles Title And Content", priority = 0, dependsOnMethods = "checkNavigationToTheRightSite")
    void checkArticlesPage() {

        //POM initialization
        homePage = new HomePage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);

        // variables for assertion
        String articlesTitle;

        WebElement theLatestElement = homePage.getElement(homePage.theLatestNewsSelector);
        WebElement parentOfArticleElement = theLatestElement.findElement(homePage.goParentNodeSelector);
        ArrayList<WebElement> articles = (ArrayList<WebElement>) parentOfArticleElement.findElements(homePage.articlesSelector);
        int randomNumber = random.nextInt(articles.size());

        WebElement randomArticle = articles.get(randomNumber);

        scrollToTheElement(randomArticle);
        highlightElement(randomArticle);

        articlesTitle = homePage.getElementText(randomArticle.findElement(homePage.articlesTitleSelector));

        articles.get(randomNumber).click(); // click random article
        articleDetailsPage.setBrowserTitle();//setting title after the navigated new page

        softAssertion.assertTrue((articlesTitle + " | TechCrunch").equals(articleDetailsPage.getBrowserTitle()));
        softAssertion.assertAll("expected: " + (articlesTitle + " | TechCrunch" + "actual:" + articleDetailsPage.getBrowserTitle()));
        softAssertion.assertAll("SoftAssertions performed.");

        WebElement articleContentsParentElement = articleDetailsPage.getElement(articleDetailsPage.articleContentsParentSelector);
        ArrayList<WebElement> articleContents = (ArrayList<WebElement>) articleContentsParentElement.findElements(articleDetailsPage.articlesContentSelector);

        for (WebElement articleContent : articleContents) {
            softAssertion.assertTrue(articleDetailsPage.getElementText(articleContent).length() >= 1, "content node should have values");
        }
        softAssertion.assertAll("SoftAssertions performed.");
    }

}
