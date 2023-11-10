package ui;

import components.HeaderContainerComponent;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class InfoDisplay_Test {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(InfoDisplay_Test.class);
    private MainPage mainPage;
    private HeaderContainerComponent headerContainerComponent;

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

        log.info("Открытие сайта");
        mainPage.open(mainPage.getPagePath());


    }
}