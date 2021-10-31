import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

import org.testng.Assert;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class GroupGroupTest extends BaseTest {

    private static final String URL1_GG = "https://www.edx.org/";
    private static final String URL2_GG = "https://www.edx.org/search?q=python&tab=course";
    private static final String URL3_DS = "https://www.saucedemo.com/";

    @Ignore
    @Test
    public void testNarimanMirzakhanov1() {
        getDriver().get("https://www.bestbuy.com/");

        WebElement closeButton = getDriver().findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']"));
        closeButton.click();

        String text = "watch";
        getDriver().findElement(By.id("gh-search-input")).sendKeys(text + "\n");

        List<WebElement> itemList = getDriver().findElements(By.xpath("//h4[@class='sku-header']/a"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains(text));
        }
    }

    @Ignore
    @Test
    public void testNarimanMirzakhanov2() {
        getDriver().get("https://www.bestbuy.com/");
        getDriver().findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']")).click();

        String text = "iphone 13 pro max 512 verizon";
        getDriver().findElement(By.id("gh-search-input")).sendKeys(text);
        getDriver().findElement(By.xpath("//span[@class='header-search-icon']")).click();

        WebElement productName = getDriver().findElement(By.xpath("//h4[@class='sku-header']"));
        Assert.assertEquals(productName.getText(), "Apple - iPhone 13 Pro Max 5G 512GB - Graphite (Verizon)");
    }

    @Ignore
    @Test
    public void testNarimanMirzakhanov3() {
        getDriver().get("https://sidelineswap.com/");
        getDriver().findElement(By.xpath("//input[@name = 'q']")).sendKeys("ball");
        getDriver().findElement(By.xpath("//form/button[@type = 'submit']")).click();
        getDriver().findElement(By.xpath("//a/button/div[contains(text(), 'Soccer')]")).click();
        getDriver().findElement(By.xpath("//div/a[contains(text(), 'Soccer Ball Classico')]")).click();
        getDriver().findElement(By.xpath("//button[@id = 'add-to-cart']")).click();

        WebElement productName = getDriver().findElement(By.xpath("//a[contains(text(), 'Soccer Ball Classico')]"));
        Assert.assertEquals(productName.getText(), "Soccer Ball Classico");
    }

    @Ignore
    @Test
    public void testNarimanMirzakhanov4() {
        getDriver().get("https://sidelineswap.com/");
        getDriver().findElement(By.xpath("//input[@name = 'q']")).sendKeys("ball");
        getDriver().findElement(By.xpath("//form/button[@type = 'submit']")).click();
        getDriver().findElement(By.xpath("//a/button/div[contains(text(), 'Soccer')]")).click();
        getDriver().findElement(By.xpath("//div/a[contains(text(), 'Soccer Ball Classico')]")).click();
        getDriver().findElement(By.xpath("//button[@id = 'add-to-cart']")).click();
        getDriver().findElement(By.xpath("//button[contains(text(), 'Remove')]")).click();
        WebElement resultAfterRemoving = getDriver().findElement(By.xpath("//h1[contains(text(), 'No items in your cart')]"));
        Assert.assertEquals(resultAfterRemoving.getText(), "No items in your cart");
    }

    @Ignore
    @Test
    public void testCreateAccountPageOpenPolinaTceretian1() {
        getDriver().get("https://www.starbucks.com");

        WebElement signIn = getDriver().findElement(By.xpath("//*[contains(text(), 'Join now')]"));
        signIn.click();

        String actualUrl = "https://www.starbucks.com/account/create";
        String expectedUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Ignore
    @Test
    public void testCreateAccountRegistrationPolinaTceretian2() {
        getDriver().get("https://www.starbucks.com/account/create");
        getDriver().findElement(By.id("firstName")).sendKeys("T");
        getDriver().findElement(By.id("lastName")).sendKeys("E");

        WebElement email = getDriver().findElement(By.id("emailAddress"));
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        email.sendKeys("username" + randomInt + "@gmail.com");

        getDriver().findElement(By.id("password")).sendKeys("123456Abc!");
        getDriver().findElement(By.xpath("//input[@id ='termsAndConditions']/..//span[@class='block option__labelMarker']")).click();
        getDriver().findElement(By.xpath("//*[contains(text(), 'Create account')]")).click();

        String actualUrl = "https://app.starbucks.com/";
        String expectedUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Ignore
    @Test
    public void testZinaidaLepesh() {
        getDriver().get("https://oz.by/");

        WebElement search = getDriver().findElement(By.id("top-s"));
        search.sendKeys("вишневский\n");

        List<WebElement> itemlist = getDriver().findElements(By.className("viewer-type-card__li "));
        for (WebElement webElement : itemlist) {
            Assert.assertTrue(webElement.getText().toLowerCase(Locale.ROOT).contains("вишневский"));
        }
    }

    @Ignore
    @Test
    public void testZinaidaLepesh2() {
        getDriver().get("https://oz.by/");

        WebElement login = getDriver().findElement(By.className("top-panel__userbar__auth"));
        login.click();

        WebElement byemail = getDriver().findElement(By.id("loginFormLoginEmailLink"));
        byemail.click();

        WebElement email = getDriver().findElement(By.name("cl_email"));
        email.sendKeys("abc@gmail.com");

        WebElement password = getDriver().findElement(By.name("cl_psw"));
        password.sendKeys("pass");

        WebElement enter = getDriver().findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        enter.click();
    }

    @Test
    public void testGayanaGSearchEdx() {
        getDriver().get(URL1_GG);
        String searchText = "python";
        getDriver().findElement(By.xpath("//input[@id='home-search']")).sendKeys(searchText);
        getDriver().findElement(By.xpath
                ("//button[@class='btn-inverse-brand form-submit edit-submit btn btn-brand']")).click();
        getDriver().findElement(By.xpath
                ("//div[@class='mt-2 mt-md-4 pt-2 container-mw-lg container-fluid']//button[@class='show-all-link btn btn-link muted-link inline-link d-inline-block pl-0 pr-4 px-xl-0']")).click();

        List<WebElement> resultList = getDriver().findElements(By.xpath("//div[@class='d-card-body pl-4 pt-4 mt-2']"));
        for (int i = 0; i < resultList.size(); i++) {
            if (i == 2) {
                continue;
            }
            Assert.assertTrue(resultList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test

    public void testGayanaGSearchEdx2() {
        getDriver().get(URL2_GG);

        getDriver().findElement(By.xpath
                ("//div[@class='d-flex bg-primary-700 pt-2 pt-sm-3 mt-sm-1 mb-3 search-refinements']/div[7]")).click();
        getDriver().findElement(By.xpath("//input[@id='any']")).click();

        Assert.assertEquals(getDriver().findElements(By.xpath
                ("//div[@class='d-card-wrapper bg-primary-500']")).size(), 4);
    }
    @Ignore
    @Test
    public void testDashaSandlerGoToLoginPage() {
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, URL3_DS); //does it the same urls?
    }
    @Ignore
    @Test
    public void testDashaSandlerPreconditionsPlaceholdersNames() {
        String placeholderUsernameTextExpected = "Username";
        String passwordTextExpected = "Password";
        WebElement placeholderUserName = getDriver().findElement(By.xpath("//input[@id='user-name']")); //placeholder for username

        WebElement placeholderPassword = getDriver().findElement(By.id("password")); //placeholder for password

        String placeholderNameUsername = placeholderUserName.getAttribute("placeholder");
        String placeholderPasswordActual = placeholderPassword.getAttribute("placeholder");

        Assert.assertEquals(placeholderNameUsername, placeholderUsernameTextExpected); //does it have correct placeholder name for username?
        Assert.assertEquals(passwordTextExpected, placeholderPasswordActual);  //does it have correct placeholder name for password?
    }
    @Ignore
    @Test
    public void testDashaSandlerLoginCheck() {
        String expectedPageAfterLogin = "https://www.saucedemo.com/inventory.html";
        WebElement loginBtn = getDriver().findElement(By.id("login-button"));
        WebElement placeholderUserName = getDriver().findElement(By.xpath("//input[@id='user-name']")); //placeholder for username
        WebElement placeholderPassword = getDriver().findElement(By.id("password")); //placeholder for password

        placeholderUserName.sendKeys("standard_user");
        placeholderPassword.sendKeys("secret_sauce");
        loginBtn.click();

        String currentUrlAfterLogin = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrlAfterLogin, expectedPageAfterLogin);
    }
    @Ignore
    @Test
    public void testMariaMoroz() {
        String inputValue = "100";
        String expectedValue = "0.1";
        getDriver().get("https://www.unitconverters.net");
        WebElement input = getDriver().findElement(By.name("fromVal"));
        WebElement output = getDriver().findElement(By.name("toVal"));
        input.sendKeys(inputValue);

        Assert.assertEquals(output.getAttribute("value"), expectedValue);
    }
    @Ignore
    @Test
    public void testAnnaZasimova_01() {

        getDriver().get("https://www.brainpop.com/");

        WebElement getStartedButton = getDriver().findElement(By.id("get-started-button"));
        getStartedButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.brainpop.com/get-started/");
    }
    @Ignore
    @Test
    public void testAnnaZasimova_02() {

        getDriver().get("https://www.brainpop.com/get-started/");

        WebElement title = getDriver().findElement(
                By.xpath("//h1[contains(text(),'Discover the power of BrainPOP! Start by selecting')]")
        );

        Assert.assertTrue(title.isDisplayed());
    }
    @Ignore
    @Test
    public void testAnnaZasimova_03() {
        getDriver().get("https://www.brainpop.com/");

        WebElement getStartedButton = getDriver().findElement(By.id("get-started-button"));
        getStartedButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.brainpop.com/get-started/");
        getDriver().navigate().back();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.brainpop.com/");
    }
    @Ignore
    @Test
    public void testSearchField() {
        getDriver().get("https://www.dre.ca.gov/");
        WebElement searchField = getDriver().findElement(By.id("search_local_textfield"));
        WebElement searchBtn = getDriver().findElement(By.name("submit"));

        searchField.sendKeys("townhouse" + "\n");

        WebElement targetPageHeader = getDriver().findElement(By.id("serp_title"));
        WebElement targetPageSearchField = getDriver().findElement(By.id("gsc-i-id1"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.dre.ca.gov/serp.html?q=townhouse&cx=001779225245372747843%3A11sknaw8obu&cof=FORID%3A10&ie=UTF-8&submit.x=0&submit.y=0");
        Assert.assertEquals(targetPageHeader.getText(), "Search Results");
        Assert.assertEquals(targetPageSearchField.getAttribute("value"), "townhouse");
    }
    @Ignore
    @Test
    public void OlenaYermakova() {
        getDriver().get("https://www.webstaurantstore.com/");
        getDriver().findElement(By.cssSelector("#searchval")).sendKeys("oven\n");

        List<WebElement> itemList = getDriver().findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains("oven"));
        }
    }
    @Ignore
    @Test
    public void testOlesyaKolenchenko() {
        getDriver().get("https://www.ivi.ru/");

        WebElement search = getDriver().findElement(By.className("headerTop__headerSearch"));
        search.click();
        String searchPage = getDriver().getCurrentUrl();
        WebElement searchString = getDriver().findElement(By.className("nbl-input__editbox"));
        searchString.sendKeys("платье\n");

        List<WebElement> itemList = getDriver().findElements(By.className("searchBlock__content"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains("платье"));
        }
    }
    @Ignore
    @Test
    public void testTaniaLutsenko() {

        getDriver().get("https://postel-market.com.ua/");

        WebElement search = getDriver().findElement(By.name("query"));
        search.sendKeys("одеяло\n");

        WebElement result = getDriver().findElement(By.className("page-name"));
        Assert.assertEquals(result.getText(), "ОДЕЯЛО");
    }
}



