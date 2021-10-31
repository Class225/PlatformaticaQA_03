import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class NoGrValeriyKanTest extends BaseTest {
    private static final String URL_NBA_STORE = "https://store.nba.com/";
    private static final String URL_GITHUB = "https://github.com/SergeiDemyanenko/PlatformaticaQA_03/";
    private static final By TOP_NAV_MEN = By.id("1");
    private static final By TOP_NAV_WOMEN_XPATH = By.xpath("//a[@aria-label='women']");
    private static final By LIST_ITEM_HEADER_CLASSNAME = By.className("breadcrumb-text");
    private static final By SEARCH_FIELD = By.id("typeahead-input-desktop");
    private static final By SEARCH_RESULT_ITEMS = By.xpath("//a[@data-talos = 'linkSearchResult']");

    private static final String MEN = "MEN";
    private static final String WOMEN = "WOMEN";
    private static final String SEARCH_PRODUCT_NAME = "shorts\n";
    @Ignore
    @Test
    public void clickTopMenuMenValeriyKan() {
        getDriver().get(URL_NBA_STORE);
        getDriver().findElement(TOP_NAV_MEN).sendKeys("\n");

        WebElement itemsTitleMen = new WebDriverWait(getDriver(), Duration.ofSeconds(0).getSeconds()).until(ExpectedConditions.presenceOfElementLocated(LIST_ITEM_HEADER_CLASSNAME));

        Assert.assertEquals(itemsTitleMen.getText(), MEN);
    }
    @Ignore
    @Test
    public void clickTopMenuWomenValeriyKan() {
        getDriver().get(URL_NBA_STORE);
        getDriver().findElement(TOP_NAV_WOMEN_XPATH).sendKeys("\n");

        WebElement itemsTitleWomen = new WebDriverWait(getDriver(), Duration.ofSeconds(0,10).getNano()).until(ExpectedConditions.presenceOfElementLocated(LIST_ITEM_HEADER_CLASSNAME));

        Assert.assertEquals(itemsTitleWomen.getText(), WOMEN);
    }
    @Ignore
    @Test
    public void productSearchValeriyKan() {
        getDriver().get(URL_NBA_STORE);

        WebElement search = getDriver().findElement(SEARCH_FIELD);

        search.sendKeys(SEARCH_PRODUCT_NAME);

        List<WebElement> searchResult = getDriver().findElements(SEARCH_RESULT_ITEMS);

        Assert.assertTrue(searchResult.size() >= 1, "\nSearch result items less than 1! \nNote: if no matches for a product, search results should return other types of products.");
    }
    @Test
    public void clickButtonNotification() {
        getDriver().get(URL_GITHUB);

        WebElement buttonNotification = getDriver().findElement(By.cssSelector("ul>li>a[data-hydro-click*='notification']"));

        buttonNotification.click();

        WebElement loginTitle = getDriver().findElement(By.cssSelector("h1"));

        Assert.assertEquals(loginTitle.getText(),"Sign in to GitHub");
    }
    @Test
    public void clickButtonStar() {
        getDriver().get(URL_GITHUB);

        WebElement buttonStar = getDriver().findElement(By.cssSelector("li>div>a[data-hydro-click*='star button']"));
        buttonStar.click();

        WebElement loginTitle = getDriver().findElement(By.cssSelector("h1"));

        Assert.assertEquals(loginTitle.getText(),"Sign in to GitHub");
    }
    @Test
    public void clickButtonFork() {
        getDriver().get(URL_GITHUB);

        WebElement buttonFork = getDriver().findElement(By.cssSelector("a[data-hydro-click*='fork button']"));
        buttonFork.click();

        WebElement loginTitle = getDriver().findElement(By.cssSelector("h1"));

        Assert.assertEquals(loginTitle.getText(),"Sign in to GitHub");
    }

}
