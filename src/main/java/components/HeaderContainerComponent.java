package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.AbsPageObject;

public class HeaderContainerComponent extends AbsPageObject {

    public HeaderContainerComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[./nav]")
    private WebElement headerContainer;

    public void goToEventsCalendar() {
//        String educationSelector = "span[title='Обучение']";

        WebElement education = headerContainer.findElement(By.cssSelector("span[title='Обучение']"));

//        standardWaiters.waitForElementVisible(headerContainer.findElement(By.cssSelector(educationSelector)));
//        moveToElement(headerContainer.findElement(By.cssSelector(educationSelector)));

        standardWaiters.waitForElementVisible(education);
        moveToElement(education);

        WebElement eventsCalendar = $(By.xpath("//a[contains(text(),'Календарь мероприятий')]"));
        moveAndClick(eventsCalendar);
    }
}