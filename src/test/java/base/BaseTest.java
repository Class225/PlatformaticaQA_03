package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, 10);
        }
        return wait;
    }

    protected void stopDriver() {
        driver.quit();
        wait = null;

        BaseUtils.log("Browser closed");
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        BaseUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());
        try {
            BaseUtils.log("Browser open, get web page and login");

            driver = BaseUtils.createDriver();
            BaseUtils.get(driver);
            BaseUtils.login(driver);
            BaseUtils.reset(driver);
        } catch (Exception e) {
            stopDriver();
            throw e;
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        stopDriver();
        BaseUtils.logf("Execution time is %o sec\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }
}
