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
    private HeaderContainerComponent headerContainerComponent;
    private EventsCalendarPage eventsCalendarPage;

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().newDriver();
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

        log.info("1. Проверка количества курсов в разделе тестирования.");
        mainPage = new MainPage(driver);
        mainPage.openPage(mainPage.getPagePath());
        mainPage.goToQACourses();
        qaCoursesPage = new QACoursesPage(driver);
        qaCoursesPage.checkCountOfQACourses();

        log.info("2. Просмотр карточек курса и проверка данных.");
        qaCoursesPage.checkCardsInfo();

        log.info("3. Валидация дат предстоящих мероприятий.");
        headerContainerComponent = new HeaderContainerComponent(driver);
        headerContainerComponent.goToEventsCalendar();
        eventsCalendarPage = new EventsCalendarPage(driver);
        eventsCalendarPage.datesChecking();

        log.info("4. Просмотр мероприятий по типу.");
        eventsCalendarPage.checkEventsFilter();
    }
}