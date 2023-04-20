package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public abstract class DriverManager {

    public WebDriver driver;
    public static String baseUrl = "https://techcrunch.com/";


    public void setDriver(String testBrowser){
        switch (testBrowser){
            case "chrome":{
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("Tests are running on Chrome");
                break;
            }
            case "firefox":{
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().fullscreen();
                System.out.println("Tests are running on Firefox");
                break;
            }
            default:{
                System.out.println("Error: Unidentified Browser!");
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void addViewedCookiePolicy(){
        driver.manage().addCookie(new Cookie("viewed_cookie_policy","yes"));
    }
}
