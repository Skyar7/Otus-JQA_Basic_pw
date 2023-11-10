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

    public void useLoginButton () {
        WebElement loginButton = headerContainer.findElement(By.xpath(".//button[contains(text(),'Войти')]"));
        loginButton.click();
    }

    public void goToPersonalPage() {

        String headNameSelector = ".sc-199a3eq-0.fJMWHf";
        standardWaiters.waitForElementVisible(By.cssSelector(headNameSelector));
        WebElement headName = $(By.cssSelector(headNameSelector));
        moveToElement(headName);

        String myProfileLocator = "//a[contains(text(), 'Мой профиль')]";
        standardWaiters.waitForElementVisible(By.xpath(myProfileLocator));
        WebElement dropDownMyProfile = $(By.xpath(myProfileLocator));
        moveAndClick(dropDownMyProfile);
    }
}