package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {
    private String pagePath = "/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getPagePath() {
        return pagePath;
    }

    public void goToQACourses() {
        $(By.xpath("(//div[@class='sc-yfk21i-0 jeFZmS'][contains(text(),'Тестирование')])[1]")).click();
    }
}