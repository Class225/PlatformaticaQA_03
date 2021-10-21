import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Group_Bug_BustersTest {
    public final String URL = "https://breadtopia.com/";

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public void browse(){
        driver.get("https://coderanch.com/");
        WebElement browse = driver.findElement(By.id("browse-button"));
        browse.click();
    }

    @BeforeMethod
    void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    @AfterMethod
    void close() {
        driver.quit();
    }

    @Test
    public void testNatalliaMarkhotka_ValidateMainPage() {
        driver.get(URL);

        List<WebElement> images = driver.findElements(By.xpath("//div[@id='post-53473']/div[1]/ div"));
        int numberOfImages = images.size();
        Assert.assertEquals(numberOfImages, 4);

    }

    @Test
    public void testNatalliaMarkhotka_CreateAccount() {
        driver.get(URL);

        WebElement myAccount = driver.findElement(By.id("menu-item-261238"));

        myAccount.click();
        driver.navigate().to("https://breadtopia.com/my-account/");

        WebElement username_F = driver.findElement(By.id("username"));
        WebElement password_F = driver.findElement(By.id("password"));
        WebElement login_B = driver.findElement(By.name("login"));

        username_F.sendKeys("snezhnaja10@gmail.com");
        password_F.sendKeys("QweAsd123!@#");
        login_B.click();
        WebElement greetings = driver.findElement(By.xpath("//span[@class='nm-username']"));

        Assert.assertEquals(greetings.getText(), "Hello Katerina");

    }

    @Test
    public void testSearchNelyaLuchynets(){ //Nelya Luchynets
        driver.get("https://www.walgreens.com/");

        WebElement input = driver.findElement(By.xpath("//input[@id='ntt-placeholder']"));
        input.sendKeys("vitamin\n");

        WebElement result = driver.findElement(By.xpath("//h1[@class='h1__page-title']"));
        Assert.assertEquals(result.getText(),"Vitamins and Supplements");
    }

    @Test
    public void testAndreyTeterinAddressCheck() {

        final String ADDRESS = "0x141a281889581C97cd7e6D4EC0A9B064B08bb239";

        driver.get("https://ethermine.org/");

        WebElement search = driver.findElement(By.xpath("//input[@class='search-input search-header']"));
        search.sendKeys(ADDRESS);
        search.sendKeys(Keys.ENTER);

        WebElement addressHeader = driver.findElement(By.className("address"));
        wait.until(ExpectedConditions.visibilityOf(addressHeader));
        Assert.assertEquals(addressHeader.getText(), ADDRESS);
    }

    @Test
    public void testAndreyTeterinNews() {

        driver.get("https://ethermine.org/");

        WebElement twitterButton = driver.findElement(By.className("open-twitter"));
        WebElement twitterWidget = driver.findElement(By.className("twitter-container"));

        twitterButton.click();

        Assert.assertFalse(twitterWidget.isDisplayed());
    }

    @Test
    public void testAndreyTeterinDarkTheme() {

        driver.get("https://ethermine.org/");

        WebElement darkThemeButton = driver.findElement(By.className("theme-switch"));
        WebElement html = driver.findElement(By.xpath("//html"));

        js.executeScript("arguments[0].click();", darkThemeButton);

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }

    @Test
    public void testMichaelBrowse() {
        browse();
        String actURL = "https://coderanch.com/forums/";
        String expURL = driver.getCurrentUrl();
        Assert.assertEquals(expURL, actURL);
    }

    @Test
    public void testMichael(){

        browse();

        WebElement RegisterLogin = driver.findElement(By.cssSelector("#loginLink"));
        RegisterLogin.click();

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.className("styled-button"));

        username.sendKeys("abc@gmail.com");
        password.sendKeys("your_password");
        login.click();

        WebElement error = driver.findElement(By.className("errorMsg"));
        Assert.assertEquals(error.getText(), "Invalid login name / email or password.\n" +
                "\n" +
                "Sorry, there have too many attempts from this IP lately and the cows are tired. Please try again a minute later");
    }
}
