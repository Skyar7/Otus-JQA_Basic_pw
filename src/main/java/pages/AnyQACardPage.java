package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AnyQACardPage extends AbsBasePage {

    public AnyQACardPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoThenQuit() {
        List<String> list = new ArrayList<>();
        String name = $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa")).getText();
        String description = $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw")).getText();
        String duration = $(By.xpath("(//p[@class='sc-1og4wiw-0 sc-3cb1l3-0 jQNgtj dgWykw'])[last()-2]")).getText();
        list.add(name);
        list.add(description);
        list.add(duration);
        QACoursesPage qaCoursesPage = new QACoursesPage(driver);
        openPage(qaCoursesPage.getPagePath());
        return list;
    }
}
