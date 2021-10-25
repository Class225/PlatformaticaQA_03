import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NoGrValeriyKanTest {
    private static final String URL_NBA_STORE = "https://store.nba.com/";
    private static final By TOP_NAV_MEN = By.id("1");
    private static final By TOP_NAV_WOMEN_XPATH = By.xpath("//a[@aria-label='women']");
    private static final By LIST_ITEM_HEADER_CLASSNAME = By.className("breadcrumb-text");
    private static final By SEARCH_FIELD = By.id("typeahead-input-desktop");
    private static final By SEARCH_RESULT_ITEMS = By.xpath("//a[@data-talos = 'linkSearchResult']");

    private static final String MEN = "MEN";
    private static final String WOMEN = "WOMEN";
    private static final String SEARCH_PRODUCT_NAME = "shorts\n";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void clickTopMenuMenValeriyKan() {
        driver.get(URL_NBA_STORE);
        driver.findElement(TOP_NAV_MEN).sendKeys("\n");

        WebElement itemsTitleMen = new WebDriverWait(driver, Duration.ofSeconds(0).getSeconds()).until(ExpectedConditions.presenceOfElementLocated(LIST_ITEM_HEADER_CLASSNAME));

        Assert.assertEquals(itemsTitleMen.getText(), MEN);
    }

    @Test
    public void clickTopMenuWomenValeriyKan() {
        driver.get(URL_NBA_STORE);
        driver.findElement(TOP_NAV_WOMEN_XPATH).sendKeys("\n");

        WebElement itemsTitleWomen = new WebDriverWait(driver, Duration.ofSeconds(0,10).getNano()).until(ExpectedConditions.presenceOfElementLocated(LIST_ITEM_HEADER_CLASSNAME));

        Assert.assertEquals(itemsTitleWomen.getText(), WOMEN);
    }

    @Test
    public void productSearchValeriyKan() {
        driver.get(URL_NBA_STORE);

        WebElement search = driver.findElement(SEARCH_FIELD);

        search.sendKeys(SEARCH_PRODUCT_NAME);

        List<WebElement> searchResult = driver.findElements(SEARCH_RESULT_ITEMS);

        Assert.assertTrue(searchResult.size() >= 1, "\nSearch result items less than 1! \nNote: if no matches for a product, search results should return other types of products.");
    }
}
