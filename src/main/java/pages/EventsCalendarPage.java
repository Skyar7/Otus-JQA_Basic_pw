package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventsCalendarPage extends AbsBasePage {

    public EventsCalendarPage(WebDriver driver) {
        super(driver);
    }

    public void datesChecking() {
        waiters.waitForElementVisible(By.cssSelector(".dod_new-events__list.js-dod_new_events"));

        // Прокрутка страницы для загрузки всех мероприятий.
        this.scrollPage();

        // Получение списка дат всех мероприятий.
        List<WebElement> datesList;
        datesList = $$(By.xpath("(//span[contains(@class,'dod_new-event__date-text')])[position() mod 2 = 1]"));

        // Получение текущей даты в нужном формате.
        LocalDate currentDate = LocalDate.now();

        // Цикл проверки дат.
        for (WebElement element : datesList) {

            // Т.к. со страницы получаем только день и месяц, а дата не может быть без года, то подставляем текущий год.
            LocalDate actualDate = LocalDate.parse(element.getText() + " " + currentDate.getYear(), DateTimeFormatter.ofPattern("d MMMM yyyy"));

            /*
             * На момент написания теста, мероприятия запланированны примерно на два месяца вперёд.
             * Поэтому если месяц январь или февраль, то вероятней всего год нужен следующий.
             * Едва ли мы в январе увидим событие на январь следующего года.
             */

            if ((actualDate.getMonthValue() == Month.JANUARY.getValue()) || (actualDate.getMonthValue() == Month.FEBRUARY.getValue())) {
                actualDate = actualDate.plusYears(1);
            }

            Assertions.assertTrue(actualDate.isAfter(currentDate) || actualDate.isEqual(currentDate));
        }
    }

    public void checkEventsFilter() {
        $(By.cssSelector(".dod_new-events-dropdown.js-dod_new_events-dropdown")).click();

        String reqCardsTypeString = "Открытый вебинар";
        String reqCardsTypeDropdownLocator = String.format("(//a[contains(text(),'%s')])[1]", reqCardsTypeString);
        waiters.waitForElementVisible(By.xpath(reqCardsTypeDropdownLocator));
        $(By.xpath(reqCardsTypeDropdownLocator)).click();

        waiters.waitForElementVisible(By.cssSelector(".dod_new-events__list.js-dod_new_events"));
        this.scrollPage();

        List<WebElement> cardsTypesList;
        cardsTypesList = $$(By.xpath("(//div[contains(@class,'dod_new-type__text')])"));

        for (WebElement element : cardsTypesList) {
            Assertions.assertEquals(reqCardsTypeString, element.getText());
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
            waiters.waitForElementVisible(By.cssSelector(loaderSelector));
            waiters.waitForElementNotvisible(By.cssSelector(loaderSelector));

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