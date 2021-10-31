import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.List;


public class GroupBugBustersTest extends BaseTest {
    public final String URL = "https://breadtopia.com/";

    public void browse(){
        getDriver().get("https://coderanch.com/");
        WebElement browse = getDriver().findElement(By.id("browse-button"));
        browse.click();
    }

    @Test
    public void testNatalliaMarkhotka_ValidateMainPage() {
        getDriver().get(URL);

        List<WebElement> images = getDriver().findElements(By.xpath("//div[@id='post-53473']/div[1]/ div"));
        int numberOfImages = images.size();
        Assert.assertEquals(numberOfImages, 4);

    }

    public void UtilMethod_NatalliaMarkhotka_Login() {
        getDriver().get(URL);

        WebElement myAccount = getDriver().findElement(By.id("menu-item-261238"));

        myAccount.click();

        WebElement username_F = getDriver().findElement(By.id("username"));
        WebElement password_F = getDriver().findElement(By.id("password"));
        WebElement login_B = getDriver().findElement(By.name("login"));

        username_F.sendKeys("snezhnaja10@gmail.com");
        password_F.sendKeys("QweAsd123!@#");
        login_B.click();
        }

    @Test
    public void testNatalliaMarkhotka_Login() {

        UtilMethod_NatalliaMarkhotka_Login();

        WebElement greetings = getDriver().findElement(By.xpath("//span[@class='nm-username']"));

        Assert.assertEquals(greetings.getText(), "Hello Katerina");

    }

    @Ignore
    @Test
    public void testSearchNelyaLuchynets(){ //Nelya Luchynets
        getDriver().get("https://www.walgreens.com/");

        WebElement input = getDriver().findElement(By.xpath("//input[@id='ntt-placeholder']"));
        input.sendKeys("vitamin\n");

        WebElement result = getDriver().findElement(By.xpath("//h1[@class='h1__page-title']"));
        Assert.assertEquals(result.getText(),"Vitamins and Supplements");
    }

    @Test
    public void testAndreyTeterinAddressCheck() {

        final String ADDRESS = "0x141a281889581C97cd7e6D4EC0A9B064B08bb239";

        getDriver().get("https://ethermine.org/");

        WebElement search = getDriver().findElement(By.xpath("//input[@class='search-input search-header']"));
        search.sendKeys(ADDRESS);
        search.sendKeys(Keys.ENTER);

        WebElement addressHeader = getDriver().findElement(By.className("address"));
        getWait().until(ExpectedConditions.visibilityOf(addressHeader));
        Assert.assertEquals(addressHeader.getText(), ADDRESS);
    }

    @Test
    public void testAndreyTeterinNews() {

        getDriver().get("https://ethermine.org/");

        WebElement twitterButton = getDriver().findElement(By.className("open-twitter"));
        WebElement twitterWidget = getDriver().findElement(By.className("twitter-container"));

        twitterButton.click();

        Assert.assertFalse(twitterWidget.isDisplayed());
    }

    @Test
    public void testAndreyTeterinDarkTheme() {

        getDriver().get("https://ethermine.org/");

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement darkThemeButton = getDriver().findElement(By.className("theme-switch"));
        WebElement html = getDriver().findElement(By.xpath("//html"));

        js.executeScript("arguments[0].click();", darkThemeButton);

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }

    @Test
    public void testMichaelBrowse() {
        browse();
        String actURL = "https://coderanch.com/forums/";
        String expURL = getDriver().getCurrentUrl();
        Assert.assertEquals(expURL, actURL);
    }

    @Ignore
    @Test
    public void testMichael(){

        browse();

        WebElement RegisterLogin = getDriver().findElement(By.cssSelector("#loginLink"));
        RegisterLogin.click();

        WebElement username = getDriver().findElement(By.name("username"));
        WebElement password = getDriver().findElement(By.name("password"));
        WebElement login = getDriver().findElement(By.className("styled-button"));

        username.sendKeys("abc@gmail.com");
        password.sendKeys("your_password");
        login.click();

        WebElement error = getDriver().findElement(By.className("errorMsg"));
        Assert.assertEquals(error.getText(), "Invalid login name / email or password.\n" +
                "\n" +
                "Sorry, there have too many attempts from this IP lately and the cows are tired. Please try again a minute later");
    }

    @Test
    public void testNatalliaMarkhotka_Logout() {
        UtilMethod_NatalliaMarkhotka_Login();

        WebElement logout_F = getDriver().findElement(By.xpath("//li/a[text() ='Logout']"));

        logout_F.click();
        WebElement title = getDriver().findElement(By.cssSelector("#nm-login-wrap h2"));
        Assert.assertEquals(title.getText(), "Log in");

    }

    public void explicitWait(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testNatalliaMarkhotka_AddingToWishList() {
        getDriver().get(URL);

        WebElement shop_L = getDriver().findElement(By.id("menu-item-261990"));

        shop_L.click();

        WebElement bakingSupplies_L = getDriver().findElement(By.xpath("//img[@alt='Baking Tools & Supplies']"));

        bakingSupplies_L.click();

        WebElement proofingBaskets_L = getDriver().findElement(By.xpath("//img[@alt='Proofing Baskets']"));

        getWait().until(ExpectedConditions.elementToBeClickable(proofingBaskets_L));
        explicitWait(1000);

        proofingBaskets_L.click();

        WebElement addToWishListSecondElement = getDriver().findElement(By.id("nm-wishlist-item-96536-button"));
        WebElement wishListCount = getDriver().findElement(By.xpath("//nav//span[@class = 'nm-menu-wishlist-count']"));

        Assert.assertEquals(wishListCount.getText(), "0");

        getWait().until(ExpectedConditions.elementToBeClickable(addToWishListSecondElement));
        explicitWait(1000);

        addToWishListSecondElement.click();

        boolean isPresent = getDriver().findElements(By.xpath("//a[@class ='nm-wishlist-button nm-wishlist-item-96536-button added']")).size() > 0;
        WebElement addedToWithList = getDriver().findElement(By.xpath("//a[@class ='nm-wishlist-button nm-wishlist-item-96536-button added']"));

        Assert.assertTrue(isPresent);
        Assert.assertEquals(wishListCount.getText(), "1");
    }
}
