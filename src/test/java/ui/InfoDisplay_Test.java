package ui;

import components.HeaderContainerComponent;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.QACoursesPage;

public class InfoDisplay_Test {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(InfoDisplay_Test.class);
    private MainPage mainPage;
    private QACoursesPage qaCoursesPage;
    private HeaderContainerComponent headerContainerComponent;

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().newDriver();
        mainPage = new MainPage(driver);
        qaCoursesPage = new QACoursesPage(driver);
        headerContainerComponent = new HeaderContainerComponent(driver);
    }

//    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void coursesCardsAndEventsTest() {

        log.info("Открытие сайта");
        mainPage.openPage(mainPage.getPagePath());

        log.info("Переход в раздел тестирование");
        mainPage.goToQACourses();

        log.info("Проверка количества карточек курсов");
        qaCoursesPage.checkCountOfQACourses();

        log.info("Проверка данных в карточках курса");
        qaCoursesPage.checkCardsInfo();

//        log.info("Переход в календарь мероприятий");
//        headerContainerComponent.goToEventsCalendar();

    }
}