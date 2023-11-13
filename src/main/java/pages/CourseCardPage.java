package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CourseCardPage extends AbsBasePage {

    public CourseCardPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoThenQuit() {
        List<String> list = new ArrayList<>();
        String infoStripTemplLocator = "(//p[@class='sc-1og4wiw-0 sc-3cb1l3-0 jQNgtj dgWykw'])[last()-'%s']";

        waiters.waitForElementVisible(By.xpath(String.format(infoStripTemplLocator, "1")));
        String name = $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa")).getText();
        String description = $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw")).getText();
        String duration = $(By.xpath(String.format(infoStripTemplLocator, "2"))).getText();
        String format = $(By.xpath(String.format(infoStripTemplLocator, "1"))).getText();
        list.add(name);
        list.add(description);
        list.add(duration);
        list.add(format);

        QACoursesPage qaCoursesPage = new QACoursesPage(driver);
        openPage(qaCoursesPage.getPagePath());

        return list;
    }
}