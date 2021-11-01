package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private static final ChromeOptions CHROME_OPTIONS;
    static {
        CHROME_OPTIONS = new ChromeOptions();
        String options = System.getenv("CHROME_OPTIONS");
        if (options != null) {
            for (String argument : options.split(";")) {
                CHROME_OPTIONS.addArguments(argument);
            }
        }
        CHROME_OPTIONS.addArguments("--window-size=1920,1080");

        WebDriverManager.chromedriver().setup();
    }

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

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(CHROME_OPTIONS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }
}
