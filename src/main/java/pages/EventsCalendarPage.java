package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventsCalendarPage extends AbsBasePage {

    public EventsCalendarPage(WebDriver driver) {
        super(driver);
    }

    public void datesChecking() {
        standardWaiters.waitForElementVisible(By.cssSelector(".dod_new-events__list.js-dod_new_events"));

        // Получение списка дат всех мероприятий.
        List<WebElement> datesList = new ArrayList<>();
        datesList = $$(By.xpath("(//span[contains(@class,'dod_new-event__date-text')])[position() mod 2 = 1]"));

        // Получение текущей даты в нужном формате.
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));

        // Цикл проверки дат.
        for (WebElement element : datesList) {
            LocalDate actualDate = LocalDate.parse(element.getText(), formatter);
            Assertions.assertTrue(actualDate.isAfter(currentDate) || actualDate.isEqual(currentDate));
        }
    }

    private void scrollPage() {
        String loaderSelector = ".dod_new-loader";

        // Создание экземпляра JavascriptExecutor.
        js = (JavascriptExecutor) driver;

        // Определение высоты прокрутки.
        long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");

        // Прокручивание страницы в цикле.
        while (true) {

            // Выполнение скроллинга.
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Ожидание появления и исчезания лоадера для загрузки контента.
            standardWaiters.waitForElementVisible(By.cssSelector(loaderSelector));
            standardWaiters.waitForElementNotvisible(By.cssSelector(loaderSelector));

            // Проверка, достижения конечной точки прокрутки.
            long newScrollHeight = (long) js.executeScript("return document.body.scrollHeight");

            if (newScrollHeight == scrollHeight) {
                break;
            } else {
                scrollHeight = newScrollHeight;
            }
        }
    }
}
