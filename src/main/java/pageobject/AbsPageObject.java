package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiters;

public abstract class AbsPageObject extends ElementActions {
    protected StandardWaiters standardWaiters;

    public AbsPageObject (WebDriver driver) {
        super(driver);
        this.standardWaiters = new StandardWaiters(driver);

        PageFactory.initElements(driver, this);
    }
}