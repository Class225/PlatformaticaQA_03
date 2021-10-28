import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Ignore
public class GroupJavaWestCoastTest {

    private WebDriver driver;

    private static final By SCOREBUTTON = By.xpath("//div[@class='ml-10px is-inline']");
    private static final By HONOR = By.xpath("//b[text()='Honor:']/..");
    private static final By RANKFIELD = By.xpath("//b[text()=\"Rank:\"]/..");
    private static final By OVERALLFIELD = By.xpath("//b[text()=\"Overall:\"]/..");
    private static final By LOGINBUTTON = By.xpath("//a[text()='Log In']");
    private static final By EMAILFIELD = By.id("user_email");
    private static final By PASSWORDFIELD = By.id("user_password");
    private static final By SIGNINBUTTON = By.xpath("//button[text()='Sign in']");
    private static final String EMAILGENERATOR = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomAlphanumeric(2) + "!" + "@gmail.com";

    public void signInMethodIliaP(){
        driver.findElement(LOGINBUTTON).click();
        driver.findElement(EMAILFIELD).sendKeys("testing@testing.com");
        driver.findElement(PASSWORDFIELD).sendKeys("Test88");
        driver.findElement(SIGNINBUTTON).click();
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }


    @Test
    public void testIliaP() {

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        WebElement scoreButton = driver.findElement(SCOREBUTTON);
        String actualValue = "Honor:" + scoreButton.getText();
        scoreButton.click();

        WebElement honor = driver.findElement(HONOR);
        String expectedValue = honor.getText();

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIliaPTwo(){

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        driver.findElement(SCOREBUTTON).click();

        WebElement rankField = driver.findElement(RANKFIELD);
        String expectedValue = rankField.getText();
        String resultExpected = "";
        for(int i = 0;i<expectedValue.length();i++){
            if (expectedValue.charAt(i)==':'){
                resultExpected += expectedValue.charAt(i+1);
            }
        }
        WebElement overallField = driver.findElement(OVERALLFIELD);
        String actualValue = overallField.getText();
        String resultActual = "";
        for(int i = 0;i<actualValue.length();i++){
            if (actualValue.charAt(i)==':'){
                resultActual += actualValue.charAt(i+1);
            }
        }
        Assert.assertEquals(resultActual,resultExpected);
    }
    @Test
    public void testYelenaAnderson() throws InterruptedException {

        String expectedResult = "https://www.asos.com/us/";

        driver.get("https://www.asos.com/us/");

        Thread.sleep(3000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testYelenaAnderson1() {

        driver.get("https://www.asos.com/us/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='search']"));

        searchBar.sendKeys("skirt\n");

        WebElement confirmText = driver.findElement(By.xpath("//p[contains(text(),'skirt')]"));
        String confirmTextText = confirmText.getText();
        Assert.assertEquals(confirmTextText, "\"Skirt\"");
    }

    @Test
    public void testGlebShkut() throws InterruptedException {
        driver.get("https://generator.email/");
        String randomEmail1 = driver.findElement(By.xpath("//input[@id='userName']")).getAttribute("value");
        String randomEmail2 = driver.findElement(By.xpath("//input[@id='domainName2']")).getAttribute("value");
        driver.get("https://www.ck12.org/teacher/");
        driver.findElement(By.xpath("//a[@title=\"Create an Account with CK-12\"]")).click();
        driver.findElement(By.xpath("//a[@class=\"button turquoise large signup-email-button js-signup-email-button\"]")).click();
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Dmitry Petrov");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(randomEmail1 + "@" + randomEmail2);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("dmitripetrov2312!sas");
        driver.findElement(By.xpath("//span[@id=\"password_check\"]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='password']")).getAttribute("value"),"dmitripetrov2312!sas");
        driver.findElement(By.xpath("//input[@id='signup_form_submit']")).click();
        driver.findElement(By.xpath("//a[@id='continueButton']")).click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("//button[contains(text(),\"Let's get started!\")]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'No, thanks. Just take me to my dashboard.')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.ck12.org/my/dashboard-new/content/");
    }

    @Test
    public void testAntonHromcenko() {
        driver.get("https://www.webstaurantstore.com");
        driver.findElement(By.xpath("//a[@data-testid='register-nav-link']")).click();
        driver.findElement(By.id("email")).sendKeys(EMAILGENERATOR);
        driver.findElement(By.id("billname")).sendKeys("test");
        driver.findElement(By.id("billaddr")).sendKeys("100 Terminal Dr");
        driver.findElement(By.id("billphone")).sendKeys("9549549544");
        driver.findElement(By.id("billzip")).sendKeys("33315");
        driver.findElement(By.id("password")).sendKeys("Test1!");
        driver.findElement(By.id("complete_registration")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.webstaurantstore.com/myaccount.html?goto=register");
    }
  
    @Test
    public void testMarianaLuchynets() {

        driver.get("https://www.google.com/maps");

        driver.findElement(By.cssSelector("#searchboxinput")).sendKeys("Austin Downtown");
        driver.findElement(By.id("searchbox-searchbutton")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Directions')]")).click();
        driver.findElement(By.cssSelector(".Zvyb8e-T3iPGc-icon.reverse")).click();
        driver.findElement(By.cssSelector("input[placeholder='Choose destination, or click on the map...']")).sendKeys("Zilker park Austin");
        driver.findElement(By.cssSelector("img[aria-label='Walking']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Guadalupe St and Ann and Roy Butler Hike and Bike Trail')]")).getText(),
                "Guadalupe St and Ann and Roy Butler Hike and Bike Trail");
    }

    @Test(description = "Is [I want To...] dropdown appears after selecting \"Consumer\" option in [I am a...] dropdown")
    public void testSvetlanaGorbunova() {

        driver.get("https://www.dre.ca.gov/");
        WebElement iAmADropdown = driver.findElement(By.id("FilterMenu1"));
        WebElement iWantToDropdown = driver.findElement(By.id("FilterMenu2"));

        Select s = new Select(iAmADropdown);
        s.selectByVisibleText("Consumer");
        Assert.assertTrue(iWantToDropdown.isDisplayed());
    }

    @Test
    public void testMaxFindText() throws InterruptedException{
        String baseUrl = "http://the-internet.herokapp.com/";

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.xpath("//a[@href='/abtest']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[(text()='A/B Test Control')]")).getText(), "A/B Test Control");
    }

    @Test
    public void testMaxCheckElementIsDisplayed() throws InterruptedException{
        String baseUrl = "http://the-internet.herokapp.com/";

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.xpath("//a[@href='/add_remove_elements/']")).click();
        driver.findElement(By.xpath("//button[@onclick='addElement()']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='added-manually']")).isDisplayed());
    }

    @Test
    public void testYelenaBay() throws InterruptedException{
        driver.get("https://www.att.com/");

        WebElement search = driver.findElement(By.id("z1-searchfield"));
        search.sendKeys("iPhone 13 Pro Max");
        search.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//button[contains(text(),'Shop now')]")).click();
        String title = driver.findElement(By.xpath("//h1[contains(text(),'iPhone 13 Pro Max')]")).getText();
        Assert.assertEquals(title, "iPhone 13 Pro Max");


    }

}



