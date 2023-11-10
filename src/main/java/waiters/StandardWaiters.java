package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StandardWaiters {
    private WebDriver driver;
    private int timeoutSec = 5;

    public StandardWaiters(WebDriver driver) {
        this.driver = driver;
    }

    public StandardWaiters(WebDriver driver, int timeoutSec) {
        this.driver = driver;
        this.timeoutSec = timeoutSec;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutSec));

        try {
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitForElementVisible(By by) {
        return waitForCondition(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean waitForElementNotvisible(By by) {
        return waitForCondition(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}