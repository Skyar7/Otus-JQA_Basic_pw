package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class QACoursesPage extends AbsBasePage {
    private String pagePath = "/catalog/courses?categories=testing";

    public String getPagePath() {
        return this.pagePath;
    }

    public QACoursesPage(WebDriver driver) {
        super(driver);
    }

    public void checkCountOfQACourses() {
        Assertions.assertEquals(10, courseCounting().size());
    }

        public void checkCardsInfo() {
        AnyQACardPage anyQACardPage = new AnyQACardPage(driver);

        for (int i = 0; i < courseCounting().size() - 1; i++) {
            List<WebElement> cards = courseCounting();
            cards.get(i).click();

            List<String> cardInfo = anyQACardPage.getInfoThenQuit();
            boolean allNonEmpty = cardInfo.stream().allMatch(s -> !s.isEmpty());
            Assertions.assertTrue(allNonEmpty);
        }
    }

    private List <WebElement> courseCounting() {
        standardWaiters.waitForElementVisible(By.cssSelector(".sc-18q05a6-1"));
        return $$(By.cssSelector(".sc-18q05a6-1 .sc-zzdkm7-0"));
    }
}
