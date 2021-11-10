package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class BaseUtils {

    private static final String LOGIN_PROP = "default.username";
    private static final String PAS_PROP = "default.password";

    private static Properties properties;

    private static final ChromeOptions chromeOptions;
    private static final boolean isServerRun;
    static {
        chromeOptions = new ChromeOptions();
        String options = System.getenv("CHROME_OPTIONS");
        if (options != null) {
            for (String argument : options.split(";")) {
                chromeOptions.addArguments(argument);
            }
        }
        chromeOptions.addArguments("--window-size=1920,1080");

        isServerRun = System.getenv("CI_RUN") != null;
        WebDriverManager.chromedriver().setup();
    }

    static void login(WebDriver driver) {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun) {
                try {
                    HttpURLConnection con = (HttpURLConnection) new URL("https://ref2.ahome.work/next_tester.php?group=group2").openConnection();
                    try {
                        con.setRequestMethod("GET");
                        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String response = in.readLine();
                            String[] responseArray = response.split(";");

                            properties.setProperty(LOGIN_PROP, responseArray[0]);
                            properties.setProperty(PAS_PROP, responseArray[1]);
                        }
                    } finally {
                        con.disconnect();
                    }
                } catch (IOException ignore) {}
            } else {
                try {
                    InputStream inputStream = BaseUtils.class.getClassLoader().getResourceAsStream("local.properties");
                    if (inputStream == null) {
                        log("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                        log("You need to create it from local.properties.TEMPLATE file.");
                        log("Please see https://youtu.be/gsicxtw-x34?t=1866 for instructions.");
                        System.exit(1);
                    }
                    properties.load(inputStream);
                } catch (IOException ignore) {}
            }
        }

        login(driver, getUserName(), getUserPassword());
    }

    static void login(WebDriver driver, String login, String pas) {
        driver.findElement(By.name("login_name")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(pas);
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    static void reset(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.id("navbarDropdownProfile")).click();
        TestUtils.jsClick(driver,
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(),'!!! Reset all for my user !!!')]"))));
    }

    static void get(WebDriver driver) {
        driver.get("https://ref2.ahome.work/");
    }

    static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    private static String getUserPassword() {
        return properties.getProperty(PAS_PROP);
    }

    public static String getUserName() {
        return properties.getProperty(LOGIN_PROP);
    }

    public static void log(String str) {
        System.out.println(str);
    }

    public static void logf(String str, Object... arr) {
        System.out.printf(str, arr);
        System.out.println();
    }
}
