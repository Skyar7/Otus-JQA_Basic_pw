package pageobject;

import org.openqa.selenium.WebDriver;
import waiters.Waiters;

public abstract class AbsPageObject extends ElementActions {
    protected Waiters waiters;

    public AbsPageObject (WebDriver driver) {
        super(driver);
        this.waiters = new Waiters(driver);
    }
}