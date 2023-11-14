package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CourseCardPage extends AbsBasePage {
    private Logger log = LogManager.getLogger(CourseCardPage.class);

    public CourseCardPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoThenQuit() {
        List<String> list = new ArrayList<>();
        String infoStripTemplLocator = "(//p[@class='sc-1og4wiw-0 sc-3cb1l3-0 jQNgtj dgWykw'])[last()-'%s']";

        waiters.waitForElementVisible(By.xpath(String.format(infoStripTemplLocator, "1")));

        String name = "";
        String description = "";
        String duration = "";
        String format = "";

        try {
            name= $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa")).getText();
        } catch (NoSuchElementException e) {
            log.info("Имя карточки курса не найдено!");
        }

        try {
            description = $(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw")).getText();
        } catch (NoSuchElementException e) {
            log.info(String.format("Описание курса для карточки '%s' не найдено!", name));
        }

        try {
            duration = $(By.xpath(String.format(infoStripTemplLocator, "2"))).getText();
        } catch (NoSuchElementException e) {
            log.info(String.format("Длительность курса для карточки '%s' не найдена!", name));
        }

        try {
            format = $(By.xpath(String.format(infoStripTemplLocator, "1"))).getText();
        } catch (NoSuchElementException e) {
            log.info(String.format("Формат курса для карточки '%s' не найден!", name));
        }

        list.add(name);
        list.add(description);
        list.add(duration);
        list.add(format);

        QACoursesPage qaCoursesPage = new QACoursesPage(driver);
        openPage(qaCoursesPage.getPagePath());

        return list;
    }
}