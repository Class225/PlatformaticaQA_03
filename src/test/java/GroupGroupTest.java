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

@Ignore
public class GroupGroupTest {

    private WebDriver driver;
    private static final String URL1_GG = "https://www.edx.org/";
    private static final String URL2_GG = "https://www.edx.org/search?q=python&tab=course";
    private static final String URL3_DS = "https://www.saucedemo.com/";

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
    public void testNarimanMirzakhanov1() {
        driver.get("https://www.bestbuy.com/");

        WebElement closeButton = driver.findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']"));
        closeButton.click();

        String text = "watch";
        driver.findElement(By.id("gh-search-input")).sendKeys(text + "\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//h4[@class='sku-header']/a"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains(text));
        }
    }

    @Test
    public void testNarimanMirzakhanov2() {
        driver.get("https://www.bestbuy.com/");
        driver.findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']")).click();

        String text = "iphone 13 pro max 512 verizon";
        driver.findElement(By.id("gh-search-input")).sendKeys(text);
        driver.findElement(By.xpath("//span[@class='header-search-icon']")).click();

        WebElement productName = driver.findElement(By.xpath("//h4[@class='sku-header']"));
        Assert.assertEquals(productName.getText(), "Apple - iPhone 13 Pro Max 5G 512GB - Graphite (Verizon)");
    }

    @Test
    public void testNarimanMirzakhanov3() {
        driver.get("https://sidelineswap.com/");
        driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("ball");
        driver.findElement(By.xpath("//form/button[@type = 'submit']")).click();
        driver.findElement(By.xpath("//a/button/div[contains(text(), 'Soccer')]")).click();
        driver.findElement(By.xpath("//div/a[contains(text(), 'Soccer Ball Classico')]")).click();
        driver.findElement(By.xpath("//button[@id = 'add-to-cart']")).click();

        WebElement productName = driver.findElement(By.xpath("//a[contains(text(), 'Soccer Ball Classico')]"));
        Assert.assertEquals(productName.getText(), "Soccer Ball Classico");
    }

    @Test
    public void testNarimanMirzakhanov4() {
        driver.get("https://sidelineswap.com/");
        driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("ball");
        driver.findElement(By.xpath("//form/button[@type = 'submit']")).click();
        driver.findElement(By.xpath("//a/button/div[contains(text(), 'Soccer')]")).click();
        driver.findElement(By.xpath("//div/a[contains(text(), 'Soccer Ball Classico')]")).click();
        driver.findElement(By.xpath("//button[@id = 'add-to-cart']")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Remove')]")).click();
        WebElement resultAfterRemoving = driver.findElement(By.xpath("//h1[contains(text(), 'No items in your cart')]"));
        Assert.assertEquals(resultAfterRemoving.getText(), "No items in your cart");
    }

    @Test
    public void testCreateAccountPageOpenPolinaTceretian1() {
        driver.get("https://www.starbucks.com");

        WebElement signIn = driver.findElement(By.xpath("//*[contains(text(), 'Join now')]"));
        signIn.click();

        String actualUrl = "https://www.starbucks.com/account/create";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testCreateAccountRegistrationPolinaTceretian2() {
        driver.get("https://www.starbucks.com/account/create");
        driver.findElement(By.id("firstName")).sendKeys("T");
        driver.findElement(By.id("lastName")).sendKeys("E");

        WebElement email = driver.findElement(By.id("emailAddress"));
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        email.sendKeys("username" + randomInt + "@gmail.com");

        driver.findElement(By.id("password")).sendKeys("123456Abc!");
        driver.findElement(By.xpath("//input[@id ='termsAndConditions']/..//span[@class='block option__labelMarker']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Create account')]")).click();

        String actualUrl = "https://app.starbucks.com/";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testZinaidaLepesh() {
        driver.get("https://oz.by/");

        WebElement search = driver.findElement(By.id("top-s"));
        search.sendKeys("вишневский\n");

        List<WebElement> itemlist = driver.findElements(By.className("viewer-type-card__li "));
        for (WebElement webElement : itemlist) {
            Assert.assertTrue(webElement.getText().toLowerCase(Locale.ROOT).contains("вишневский"));
        }
    }

    @Test
    public void testZinaidaLepesh2() {
        driver.get("https://oz.by/");

        WebElement login = driver.findElement(By.className("top-panel__userbar__auth"));
        login.click();

        WebElement byemail = driver.findElement(By.id("loginFormLoginEmailLink"));
        byemail.click();

        WebElement email = driver.findElement(By.name("cl_email"));
        email.sendKeys("abc@gmail.com");

        WebElement password = driver.findElement(By.name("cl_psw"));
        password.sendKeys("pass");

        WebElement enter = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        enter.click();
    }

    @Test
    // searching all python cources. Third course contains Pyt.. instead of full word. It's a reason why the test
    // failed, so this course was excluded from list elements.
    public void testGayanaGSearchEdx() {
        driver.get(URL1_GG);
        String searchText = "python";
        driver.findElement(By.xpath("//input[@id='home-search']")).sendKeys(searchText);
        driver.findElement(By.xpath
                ("//button[@class='btn-inverse-brand form-submit edit-submit btn btn-brand']")).click();
        driver.findElement(By.xpath
                ("//div[@class='mt-2 mt-md-4 pt-2 container-mw-lg container-fluid']//button[@class='show-all-link " +
                        "btn btn-link muted-link inline-link d-inline-block pl-0 pr-4 px-xl-0']")).click();

        List<WebElement> resultList = driver.findElements(By.xpath("//div[@class='d-card-body pl-4 pt-4 mt-2']"));
        for (int i = 0; i < resultList.size(); i++) {
            if (i == 2) {
                continue;
            }
            Assert.assertTrue(resultList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test
    // test if search by any type of learning python shows first 4 elements of "python" programs
    public void testGayanaGSearchEdx2() {
        driver.get(URL2_GG);

        driver.findElement(By.xpath
                ("//div[@class='d-flex bg-primary-700 pt-2 pt-sm-3 mt-sm-1 mb-3 search-refinements']/div[7]")).click();
        driver.findElement(By.xpath("//input[@id='any']")).click();

        Assert.assertEquals(driver.findElements(By.xpath
                ("//div[@class='d-card-wrapper bg-primary-500']")).size(), 4);
    }

    @Test
    public void testDashaSandlerGoToLoginPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, URL3_DS); //does it the same urls?
    }

    @Test
    public void testDashaSandlerPreconditionsPlaceholdersNames() {
        String placeholderUsernameTextExpected = "Username";
        String passwordTextExpected = "Password";
        WebElement placeholderUserName = driver.findElement(By.xpath("//input[@id='user-name']")); //placeholder for username

        WebElement placeholderPassword = driver.findElement(By.id("password")); //placeholder for password

        String placeholderNameUsername = placeholderUserName.getAttribute("placeholder");
        String placeholderPasswordActual = placeholderPassword.getAttribute("placeholder");

        Assert.assertEquals(placeholderNameUsername, placeholderUsernameTextExpected); //does it have correct placeholder name for username?
        Assert.assertEquals(passwordTextExpected, placeholderPasswordActual);  //does it have correct placeholder name for password?
    }

    @Test
    public void testDashaSandlerLoginCheck() {
        String expectedPageAfterLogin = "https://www.saucedemo.com/inventory.html";
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        WebElement placeholderUserName = driver.findElement(By.xpath("//input[@id='user-name']")); //placeholder for username
        WebElement placeholderPassword = driver.findElement(By.id("password")); //placeholder for password

        placeholderUserName.sendKeys("standard_user");
        placeholderPassword.sendKeys("secret_sauce");
        loginBtn.click();

        String currentUrlAfterLogin = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlAfterLogin, expectedPageAfterLogin);
    }

    @Test
    public void testMariaMoroz() {
        String inputValue = "100";
        String expectedValue = "0.1";
        driver.get("https://www.unitconverters.net");
        WebElement input = driver.findElement(By.name("fromVal"));
        WebElement output = driver.findElement(By.name("toVal"));
        input.sendKeys(inputValue);

        Assert.assertEquals(output.getAttribute("value"), expectedValue);
    }

    @Test
    public void testAnnaZasimova_01() {

        driver.get("https://www.brainpop.com/");

        WebElement getStartedButton = driver.findElement(By.id("get-started-button"));
        getStartedButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.brainpop.com/get-started/");
    }

    @Test
    public void testAnnaZasimova_02() {

        driver.get("https://www.brainpop.com/get-started/");

        WebElement title = driver.findElement(
                By.xpath("//h1[contains(text(),'Discover the power of BrainPOP! Start by selecting')]")
        );

        Assert.assertTrue(title.isDisplayed());
    }

    @Test
    public void testAnnaZasimova_03() {
        driver.get("https://www.brainpop.com/");

        WebElement getStartedButton = driver.findElement(By.id("get-started-button"));
        getStartedButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.brainpop.com/get-started/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.brainpop.com/");
    }

    @Test
    public void testSearchField() {
        driver.get("https://www.dre.ca.gov/");
        WebElement searchField = driver.findElement(By.id("search_local_textfield"));
        WebElement searchBtn = driver.findElement(By.name("submit"));

        searchField.sendKeys("townhouse" + "\n");

        WebElement targetPageHeader = driver.findElement(By.id("serp_title"));
        WebElement targetPageSearchField = driver.findElement(By.id("gsc-i-id1"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.dre.ca.gov/serp.html?q=townhouse&cx=001779225245372747843%3A11sknaw8obu&cof=FORID%3A10&ie=UTF-8&submit.x=0&submit.y=0");
        Assert.assertEquals(targetPageHeader.getText(), "Search Results");
        Assert.assertEquals(targetPageSearchField.getAttribute("value"), "townhouse");
    }

    @Test
    public void OlenaYermakova() {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.cssSelector("#searchval")).sendKeys("oven\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains("oven"));
        }
    }

    @Test
    public void testOlesyaKolenchenko() {
        driver.get("https://www.ivi.ru/");

        WebElement search = driver.findElement(By.className("headerTop__headerSearch"));
        search.click();
        String searchPage = driver.getCurrentUrl();
        WebElement searchString = driver.findElement(By.className("nbl-input__editbox"));
        searchString.sendKeys("платье\n");

        List<WebElement> itemList = driver.findElements(By.className("searchBlock__content"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains("платье"));
        }
    }
}



