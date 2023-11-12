package pages;

import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbsBasePage extends AbsPageObject {

    protected final static String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String path) {
        driver.get(BASE_URL + path);
    }
}