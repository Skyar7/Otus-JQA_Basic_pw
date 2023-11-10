package pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {
    private String pagePath = "/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getPagePath() {
        return pagePath;
    }
}