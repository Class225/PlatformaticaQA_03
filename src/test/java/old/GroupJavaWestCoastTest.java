package old;

import base.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Ignore
public class GroupJavaWestCoastTest extends BaseTest {

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
        getDriver().findElement(LOGINBUTTON).click();
        getDriver().findElement(EMAILFIELD).sendKeys("testing@testing.com");
        getDriver().findElement(PASSWORDFIELD).sendKeys("Test88");
        getDriver().findElement(SIGNINBUTTON).click();
    }

    @Test
    public void testIliaP() {

        getDriver().get("https://www.codewars.com/");

        signInMethodIliaP();

        WebElement scoreButton = getDriver().findElement(SCOREBUTTON);
        String actualValue = "Honor:" + scoreButton.getText();
        scoreButton.click();

        WebElement honor = getDriver().findElement(HONOR);
        String expectedValue = honor.getText();

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIliaPTwo(){

        getDriver().get("https://www.codewars.com/");

        signInMethodIliaP();

        getDriver().findElement(SCOREBUTTON).click();

        WebElement rankField = getDriver().findElement(RANKFIELD);
        String expectedValue = rankField.getText();
        String resultExpected = "";
        for(int i = 0;i<expectedValue.length();i++){
            if (expectedValue.charAt(i)==':'){
                resultExpected += expectedValue.charAt(i+1);
            }
        }
        WebElement overallField = getDriver().findElement(OVERALLFIELD);
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

        getDriver().get("https://www.asos.com/us/");

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testYelenaAnderson1() {

        getDriver().get("https://www.asos.com/us/");

        WebElement searchBar = getDriver().findElement(By.xpath("//input[@type='search']"));

        searchBar.sendKeys("skirt\n");

        WebElement confirmText = getDriver().findElement(By.xpath("//p[contains(text(),'skirt')]"));
        String confirmTextText = confirmText.getText();
        Assert.assertEquals(confirmTextText, "\"Skirt\"");
    }


    @Test
    public void testGlebShkut() throws InterruptedException {
        getDriver().get("https://generator.email/");
        String randomEmail1 = getDriver().findElement(By.xpath("//input[@id='userName']")).getAttribute("value");
        String randomEmail2 = getDriver().findElement(By.xpath("//input[@id='domainName2']")).getAttribute("value");
        getDriver().get("https://www.ck12.org/auth/signup/teacher");
        getDriver().findElement(By.xpath("//a[@class=\"button turquoise large signup-email-button js-signup-email-button\"]")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Dmitry Petrov");
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(randomEmail1 + "@" + randomEmail2);
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("dmitripetrov2312!sas");
        getDriver().findElement(By.xpath("//span[@id=\"password_check\"]")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//input[@id='password']")).getAttribute("value"),"dmitripetrov2312!sas");
        getDriver().findElement(By.xpath("//input[@id='signup_form_submit']")).click();
        getDriver().findElement(By.xpath("//a[@id='continueButton']")).click();
        Thread.sleep(3000);
        getDriver().navigate().refresh();
        getDriver().findElement(By.xpath("//button[contains(text(),\"Let's get started!\")]")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'No, thanks. Just take me to my dashboard.')]")).click();
        Assert.assertEquals(getDriver().getCurrentUrl(),"https://www.ck12.org/my/dashboard-new/content/");
    }

    @Test
    public void testAntonHromcenko() {
        getDriver().get("https://www.webstaurantstore.com");getDriver().findElement(By.xpath("//a[@data-testid='register-nav-link']")).click();
        getDriver().findElement(By.id("email")).sendKeys(EMAILGENERATOR);
        getDriver().findElement(By.id("billname")).sendKeys("test");
        getDriver().findElement(By.id("billaddr")).sendKeys("100 Terminal Dr");
        getDriver().findElement(By.id("billphone")).sendKeys("9549549544");
        getDriver().findElement(By.id("billzip")).sendKeys("33315");
        getDriver().findElement(By.id("password")).sendKeys("Test1!");
        getDriver().findElement(By.id("complete_registration")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(),"https://www.webstaurantstore.com/myaccount.html?goto=register");
    }
  
    @Test
    public void testMarianaLuchynets() {

        getDriver().get("https://www.google.com/maps");

        getDriver().findElement(By.cssSelector("#searchboxinput")).sendKeys("Austin Downtown");
        getDriver().findElement(By.id("searchbox-searchbutton")).click();
        getDriver().findElement(By.xpath("//div[contains(text(),'Directions')]")).click();
        getDriver().findElement(By.cssSelector(".Zvyb8e-T3iPGc-icon.reverse")).click();
        getDriver().findElement(By.cssSelector("input[placeholder='Choose destination, or click on the map...']")).sendKeys("Zilker park Austin");
        getDriver().findElement(By.cssSelector("img[aria-label='Walking']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'Guadalupe St and Ann and Roy Butler Hike and Bike Trail')]")).getText(),
                "Guadalupe St and Ann and Roy Butler Hike and Bike Trail");
    }

    @Test(description = "Is [I want To...] dropdown appears after selecting \"Consumer\" option in [I am a...] dropdown")
    public void testSvetlanaGorbunova() {

        getDriver().get("https://www.dre.ca.gov/");
        WebElement iAmADropdown = getDriver().findElement(By.id("FilterMenu1"));
        WebElement iWantToDropdown = getDriver().findElement(By.id("FilterMenu2"));

        Select s = new Select(iAmADropdown);
        s.selectByVisibleText("Consumer");
        Assert.assertTrue(iWantToDropdown.isDisplayed());
    }

    @Ignore
    @Test
    public void testMaxFindText() throws InterruptedException{
        String baseUrl = "http://the-internet.herokuapp.com/";

        getDriver().get(baseUrl);
        getDriver().findElement(By.xpath("//a[@href='/abtest']")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//*[(text()='A/B Test Control')]")).getText(), "A/B Test Control");
    }

    @Test
    public void testMaxCheckElementIsDisplayed() throws InterruptedException{
        String baseUrl = "http://the-internet.herokuapp.com/";

        getDriver().get(baseUrl);
        getDriver().findElement(By.xpath("//a[@href='/add_remove_elements/']")).click();
        getDriver().findElement(By.xpath("//button[@onclick='addElement()']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[@class='added-manually']")).isDisplayed());
    }


    @Ignore
    @Test
    public void testYelenaBay() throws InterruptedException{
        getDriver().get("https://www.att.com/");

        WebElement search = getDriver().findElement(By.id("z1-searchfield"));
        search.sendKeys("iPhone 13 Pro Max");
        search.sendKeys(Keys.ENTER);

        getDriver().findElement(By.xpath("//button[contains(text(),'Shop now')]")).click();
        String title = getDriver().findElement(By.xpath("//h1[contains(text(),'iPhone 13 Pro Max')]")).getText();
        Assert.assertEquals(title, "iPhone 13 Pro Max");
    }

}



