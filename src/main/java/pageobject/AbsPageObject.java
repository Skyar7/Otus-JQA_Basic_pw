package pageobject;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiters;

public abstract class AbsPageObject extends ElementActions {
    protected StandardWaiters standardWaiters;
    protected Faker faker;

    public AbsPageObject (WebDriver driver) {
        super(driver);
        this.standardWaiters = new StandardWaiters(driver);
        this.faker = new Faker();

        PageFactory.initElements(driver, this);
    }
}