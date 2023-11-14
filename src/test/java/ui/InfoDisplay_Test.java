package ui;

import components.HeaderContainerComponent;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.EventsCalendarPage;
import pages.MainPage;
import pages.QACoursesPage;

public class InfoDisplay_Test {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(InfoDisplay_Test.class);
    private MainPage mainPage;
    private QACoursesPage qaCoursesPage;

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().newDriver();
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void coursesCardsAndEventsTest() {
        log.info("Проверка количества курсов в разделе тестирования.");
        mainPage.openPage(mainPage.getPagePath());
        mainPage.goToQACourses();

        qaCoursesPage = new QACoursesPage(driver);
        qaCoursesPage.checkCountOfQACourses();
    }

    @Test
    public void checkCardsTest() {
        log.info("Просмотр карточек курса и проверка данных.");
        mainPage.openPage(mainPage.getPagePath());
        mainPage.goToQACourses();

        qaCoursesPage = new QACoursesPage(driver);
        qaCoursesPage.checkCountOfQACourses();
        qaCoursesPage.checkCardsInfo();
    }

    @Test
    public void eventsDatesValidationTest() {
        log.info("Валидация дат предстоящих мероприятий.");
        mainPage.openPage(mainPage.getPagePath());

        new HeaderContainerComponent(driver).goToEventsCalendar();
        new EventsCalendarPage(driver).datesChecking();
    }

    @Test
    public void eventsFiltrationTest() {
        log.info("Просмотр мероприятий по типу.");
        mainPage.openPage(mainPage.getPagePath());

        new HeaderContainerComponent(driver).goToEventsCalendar();
        new EventsCalendarPage(driver).checkEventsFilter();
    }
}