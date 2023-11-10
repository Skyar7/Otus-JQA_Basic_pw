package driver;

import data.BrowsersData;
import driver.impl.ChromeWebDriver;
import driver.impl.IWebDriver;
import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class WebDriverFactory {
    private final String BROWSER_NAME = System.getProperty("browser.name", "chrome");

    public WebDriver newDriver() {
        
        BrowsersData userBrowser = null;

        for(BrowsersData browser: BrowsersData.values()) {
            if(browser.getName().equals(BROWSER_NAME.trim().toLowerCase(Locale.ROOT))) {
                userBrowser = BrowsersData.valueOf(BROWSER_NAME.toUpperCase());
                break;
            }
        }

        if(userBrowser == null) {
            throw new BrowserNotSupportedException(BROWSER_NAME);
        }

        switch(userBrowser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                IWebDriver options = new ChromeWebDriver();
                return new ChromeDriver((ChromeOptions) options.getOptions());
            }
            default: {
                return null;
            }
        }
    }
}