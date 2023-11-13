package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.AbsPageObject;

public class HeaderContainerComponent extends AbsPageObject {

    public HeaderContainerComponent(WebDriver driver) {
        super(driver);
    }

    public void goToEventsCalendar() {
        waiters.waitForHeadersElementsLoaded();

        WebElement headerContainer = $(By.xpath("//div[./nav]"));
        waiters.waitForElementVisible(headerContainer);

        WebElement education = headerContainer.findElement(By.cssSelector("span[title='Обучение']"));
        waiters.waitForElementVisible(education);
        moveToElement(education);

        WebElement eventsCalendar = $(By.xpath("//a[contains(text(),'Календарь мероприятий')]"));
        waiters.waitForElementVisible(eventsCalendar);
        moveAndClick(eventsCalendar);
    }
}