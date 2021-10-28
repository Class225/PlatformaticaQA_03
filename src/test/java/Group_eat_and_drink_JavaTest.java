import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class Group_eat_and_drink_JavaTest extends BaseTest {

    private static final String URL = "https://www.godaddy.com/";
    private static final By EXERCISE1 = By.xpath("//*[@id=\"w3-exerciseform\"]/div/div/pre/input[1]");
    private static final By EXERCISE2 = By.name("ex2");
    private static final By EXERCISE3 = By.name("ex3");
    private static final By NEXTEX = By.id("correctnextbtn");
    private static final String URLWEB = "http://www.diamondpeak.com/";

    @Test(priority = 1)
    public void testFindCorrectItem() {

        getDriver().get("https://askent.ru/");
        WebElement bags = getDriver().findElement(By.xpath("//a[@href=\"https://askent.ru/cat/zhenskoe/filter/kollektsiya-is-9b65b8c3-fe04-11e8-80be-18a905775a6f/apply/\"]"));
        bags.click();

        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getDriver().findElement(By.xpath("//a[contains(text(),'Показать ещё')]")).click();
        getDriver().findElement(By.xpath("//div[@class=\"productItem__link\"]/a[@href=\"/cat/sumki/ryukzak_63/\"]")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askent.ru/cat/sumki/ryukzak_63/");
    }

    @Test(priority = 2)
    public void testItemToBasket() {

        getDriver().get("https://askent.ru/cat/sumki/ryukzak_63/");

        getDriver().findElement(By.xpath("//div[@class='productCols']//div[@class='product__colorBlock']//div[3]//div[1]")).click();
        getDriver().findElement(By.xpath("//div[@id='fixed_block']//span[contains(text(),'Добавить в корзину')]")).click();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath("//div[contains(text(),'Перейти в корзину')]")).click();
        WebElement addOneItem = getDriver().findElement(By.xpath("//div[@class='basketItem__add']"));
        addOneItem.click();
        getDriver().navigate().refresh();
        getDriver().findElement(By.xpath("//button[contains(text(),'Продолжить оформление заказа')]")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askent.ru/order/");
    }

    @Test(priority = 3)
    public void testSingIn() {

        getDriver().get("https://askent.ru/order/");

        WebElement userLogin = getDriver().findElement(By.xpath("//div[@class='items_block']//input[@name='USER_LOGIN']"));
        userLogin.sendKeys("ziz@gmail.com");
        WebElement userPassword = getDriver().findElement(By.xpath("//div[@class='items_block']//input[@name='USER_PASSWORD']"));
        userPassword.sendKeys("1111AAAAaaaa");
        WebElement signIn = getDriver().findElement(By.xpath("//a[@class='control_link'][contains(text(),'Войти')]"));
        signIn.click();

        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement message = getDriver().findElement(By.xpath("//*[contains(text(),'Не верный логин или пароль')]"));

        Assert.assertEquals(message.getText(), "Не верный логин или пароль");
    }

    @Test
    public void testOlenaKSearches() {
        getDriver().get("https://www.kobo.com/");
        String bookName = "Harry Potter";

        WebElement searchField = getDriver().findElement(By.name("query"));
        searchField.sendKeys(bookName + "\n");

        List<WebElement> itemList = getDriver().findElements(By.xpath("//h2[@class='title product-field']/child::a"));
        Assert.assertTrue(itemList.size() != 0);
        for (WebElement item : itemList) {
            Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                    contains(bookName.toLowerCase(Locale.ROOT)));
        }
    }

    @Test
    public void testOlenaKFindByIsbn() {
        getDriver().get("https://www.kobo.com/");
        String numberIsbn = "9781781103326";
        String expectedResult = "Harry Potter en de Steen der Wijzen";

        WebElement searchField = getDriver().findElement(By.name("query"));
        searchField.sendKeys(numberIsbn + "\n");

        WebElement bookTitle = getDriver().findElement(By.xpath("(//h2[@class = 'title product-field'])[1]"));
        String actualResult = bookTitle.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRegistrationTatianaT() throws InterruptedException {
        getDriver().get("https://humans.net/");
        WebElement signUp = getDriver().findElement
                (By.xpath("//a[text()='Sign up']"));
        signUp.click();
        Thread.sleep(3000);

        WebElement userName = getDriver().findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");
        WebElement password = getDriver().findElement(By.name("password"));
        password.sendKeys("humans");

        WebElement joinNow = getDriver().findElement(By.id("reg-step-1"));
        joinNow.click();

        WebElement codeBox1 = getDriver().findElement(By.name("digit0"));
        codeBox1.sendKeys("1");

        WebElement codeBox2 = getDriver().findElement(By.name("digit1"));
        codeBox2.sendKeys("2");

        WebElement codeBox3 = getDriver().findElement(By.name("digit2"));
        codeBox3.sendKeys("3");

        WebElement codeBox4 = getDriver().findElement(By.name("digit3"));
        codeBox4.sendKeys("4");

        WebElement continueButton = getDriver().findElement(By.xpath("//button[text()='Continue']"));
        continueButton.click();

        WebElement error = getDriver().findElement(By.xpath("//div[text()='Incorrect verification code']"));

        Assert.assertEquals(error.getText(), "Incorrect verification code");
    }

    @Test
    public void testLogInIncorrectValuesTatianaT() throws InterruptedException {
        getDriver().get("https://humans.net/registration");
        WebElement logIn = getDriver().findElement(By.xpath("//span[text()='Log in']"));
        logIn.click();
        Thread.sleep(3000);

        WebElement userName = getDriver().findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");

        WebElement password = getDriver().findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("humans");

        WebElement continueButton = getDriver().findElement(By.xpath("//button[@type='submit']/span"));
        continueButton.click();

        WebElement error = getDriver().findElement(By.xpath("//span[text()='Incorrect login or password']"));

        Assert.assertEquals(error.getText(), "Incorrect login or password");

    }

    @Test
    void testSergeA_navigateMainPage() {
        getDriver().get(URL);
        expectedOrActualResult("https://www.godaddy.com/", URL);
    }

    @Test
    void testSergeA_searchDomain() {
        testSergeA_navigateMainPage();
        String name = "google.com";
        WebElement buttonNameForSearch = getDriver().findElement(By.className("searchText"));
        buttonNameForSearch.click();
        WebElement inputSearchDomain = getDriver().findElement(By.xpath("//input[@type='text'][1]"));

        inputSearchDomain.sendKeys(name);
        buttonNameForSearch.click();

        String expectedResult = "https://www.godaddy.com/domainsearch/find?checkAvail=1&domainToCheck=" + name;
        String actualResult = "https://www.godaddy.com/domainsearch/find?checkAvail=1&domainToCheck=" + name;
        expectedOrActualResult(expectedResult, actualResult);
    }

    public void expectedOrActualResult(String expectedResult, String actualResult) {
        Assert.assertEquals(expectedResult, actualResult);
    }

    public void navigateToPage() {

        String URL = "https://www.w3schools.com/";
        getDriver().get(URL);
    }

    public void completeExerciseCorrect() {

        WebElement learnJava = getDriver().findElement(By.xpath("//*[@id=\"main\"]/div[6]/div/div[3]/div/a"));
        learnJava.click();
        getDriver().findElement(EXERCISE1).sendKeys("System");
        getDriver().findElement(EXERCISE2).sendKeys("out");
        getDriver().findElement(EXERCISE3).sendKeys("println");
    }

    public void completeExerciseIncorrect() {

        WebElement learnJava = getDriver().findElement(By.xpath("//*[@id=\"main\"]/div[6]/div/div[3]/div/a"));
        learnJava.click();
        getDriver().findElement(EXERCISE1).sendKeys("print");
        getDriver().findElement(EXERCISE2).sendKeys("out");

        getDriver().findElement(EXERCISE3).sendKeys("phrase");
    }

    public void proceedToResultPage() {

        WebElement submit = getDriver().findElement(By.xpath("//*[@id=\"w3-exerciseform\"]/div/button"));
        submit.click();

        System.out.println("current URL " + getDriver().getCurrentUrl());
        System.out.println("current page title " + getDriver().getTitle());

        ArrayList<String> newTb = new ArrayList<String>(getDriver().getWindowHandles());


        getDriver().switchTo().window(newTb.get(1));

        System.out.println("current URL " + getDriver().getCurrentUrl());
        System.out.println("current page title " + getDriver().getTitle());

        WebElement answerButton = getDriver().findElement(By.xpath("//*[@id=\"answerbutton\"]"));
        answerButton.click();
    }

    @Test
    public void testElenauSIncorrectResultCheck() {
        navigateToPage();
        completeExerciseIncorrect();
        proceedToResultPage();

        WebElement warning = getDriver().findElement(By.xpath("//*[@id=\"assignmentNotCorrect\"]/h2"));

        Assert.assertEquals(warning.getText(), "Not Correct");
    }

    @Test
    public void testElenauSCorrectResultCheck() {

        navigateToPage();
        completeExerciseCorrect();
        proceedToResultPage();

        WebElement result = getDriver().findElement(By.xpath("//*[@id=\"assignmentCorrect\"]/h2"));

        Assert.assertEquals(result.getText(), "Correct!");
    }

    @Test
    public void testElenauSAssignmentList() {

        navigateToPage();
        completeExerciseCorrect();
        proceedToResultPage();

        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        for (WebElement l : links) {
            String name = l.getText();
            System.out.println(name);
        }
        getDriver().findElement(NEXTEX).click();

        WebElement description = getDriver().findElement(By.xpath("//*[@id=\"assignmenttext\"]/p"));

        Assert.assertEquals(description.getText(), "Comments in Java are written with special characters. Insert the missing parts:");
    }

    @Test
    public void SergeyBrigMenuTest() {

        String expectedResult = "http://automationpractice.com/index.php?id_product=1&controller=product";

        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        WebElement shirtLink = getDriver().findElement(By.xpath("//div[@id = 'block_top_menu']/ul/li[3]/a"));
        shirtLink.click();
        WebElement productLink = getDriver().findElement(By.xpath("//a[@class = 'product_img_link']"));
        productLink.click();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void OlenaMSearchTheItemTest() {

        getDriver().get("https://www.homedepot.com/");
        getDriver().findElement(By.id("headerSearch")).sendKeys("refrigerator\n");
        getDriver().findElement(By.id("headerSearchButton")).click();

        getDriver().findElement(By.linkText("Shop All French Door Refrigerators")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.homedepot.com/b/Appliances-Refrigerators-French-Door-Refrigerators/N-5yc1vZc3oo");
    }

    @Test
    public void OlenaMSignInTest() {

        getDriver().get("https://www.homedepot.com/");

        getDriver().findElement(By.id("headerMyAccount")).click();
        getDriver().findElement(By.id("SPSOSignIn")).click();

        getDriver().findElement(By.id("email")).sendKeys("abc@gmail.com");
        getDriver().findElement(By.id("password-input-field")).sendKeys("ZXCasd123");
        getDriver().findElement(By.xpath("//span[normalize-space()='Sign In']")).click();

        WebElement error = getDriver().findElement(By.xpath("//span[@class='alert-inline__message']"));
        Assert.assertEquals(error.getText(), "For your protection, we've locked your account for a short period of time. You may try logging in again in 15 minutes. If you've forgotten your password, you may change it using the link below.");

    }

    @Test
    public void SergeyBrigSearchTest() {

        getDriver().get("https://www.webstaurantstore.com");

        final String searchText = "fork";
        getDriver().findElement(By.id("searchval")).sendKeys(searchText + "\n");
        List<WebElement> itemList = getDriver().findElements(By.xpath("//div/a[@data-testid='itemDescription']"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test
    public void SergeyBrigBrandMenuTest() {
        getDriver().get("https://www.webstaurantstore.com");

        getDriver().findElement(By.xpath("//a[@title='Amana Commercial Microwaves']")).click();

        List<WebElement> brandList = getDriver().findElements(By.xpath("//p[@class = 'description category_name']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains("amana"));
        }
    }

    @Test
    public void testSearchFieldFindJobTatianaT() {
        getDriver().get("https://humans.net/");

        WebElement searchField = getDriver().findElement(By.xpath("//input[@role='combobox']"));
        searchField.sendKeys("Engineering");
        WebElement fieldLocation = getDriver().findElement(By.xpath("//button[@type='button']/div"));
        fieldLocation.click();
        WebElement fieldCity = getDriver().findElement(By.xpath("//input[@placeholder='City']"));
        fieldCity.sendKeys("Seattle");
        WebElement city = getDriver().findElement(By.xpath("//span[text()='Seattle']"));
        city.click();
        WebElement find = getDriver().findElement(By.xpath("//button[text()='Find']"));
        find.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://humans.net/findwork/all/any/Seattle%20WA/?q=Engineering");
    }
    @Test
    public void SergeyBrigBrandSearch() {

        getDriver().get("https://www.webstaurantstore.com");

        getDriver().findElement(By.xpath("//input[@id = 'searchval']")).sendKeys("cup \n");

        List<WebElement> brandList = getDriver().findElements(By.xpath("//a[@ data-testid='itemDescription']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains("cup"));
        }
    }
    @Test
    public void SergeyBrigMenu2Test() {
        getDriver().get("https://www.webstaurantstore.com");

        List<WebElement> menuList = getDriver().findElements(By.xpath("//div[@class = 'm-0 lt:flex']/a"));
        for (int i = 0; i < menuList.size(); i++) {
            if(menuList.get(i).getText().toLowerCase().contains("furniture")) {
                menuList.get(i).click();
                break;
            }
        }
        List<WebElement> categoryList = getDriver().findElements(By.xpath("//div/a/h2"));
        for(int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getText().contains("Hotel Furniture")) {
                categoryList.get(i).click();
                break;
            }
        }
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.webstaurantstore.com/64111/hotel-furniture.html");
    }

    @Test
    public void testDiamondPage() {
        getDriver().get(URLWEB);
        WebElement title = getDriver().findElement(By.xpath("//a[@class='logo']"));

        String actualResult = title.getText();
        assertEquals(actualResult, "DIAMOND PEAK");
        List<WebElement> navBar = getDriver().findElements(By.xpath("//ul[@class ='navbar row']"));
        for (int i = 0; i < navBar.size(); i++) {
            assertEquals(navBar.get(i).getText(), "DIAMOND PEAK\n" +
                    "THE MOUNTAINTICKETS & PASSES\n" +
                    "LESSONS & RENTALSPLAN A VISIT");
        }

    }

    @Test
    public void testElena_uSRegistrationPage() {

        getDriver().get("https://www.homedepot.com");

        WebElement myAccount = getDriver().findElement(By.id("headerMyAccount"));
        myAccount.click();

        WebElement register = getDriver().findElement(By.xpath("//*[@id='SPSORegister']/a"));
        register.click();

        WebElement personalAccount =getDriver().findElement(By.xpath("//*[@id='single-signin__body']/div/div[2]/div/div[2]/div[1]/div"));
        personalAccount.click();

        Assert.assertEquals(getDriver().getTitle(),"The Home Depot: sign in, create or secure your account");

    }



    @Test
    public void testElena_uSChewyCartButton() {

        String URL = "https://www.chewy.com/";

        getDriver().get(URL);

        WebElement halloweenShop = getDriver().findElement(By.xpath("//*[@id='sfw-header__main']/header/div[2]/div/ul/li[7]/a"));
        halloweenShop.click();

        WebElement shopFunny = getDriver().findElement(By.xpath("//*[@id='tns3-item3']/a"));
        shopFunny.click();

        WebElement forDog = getDriver().findElement(By.xpath("//*[@id='tns1-item0']"));
        forDog.click();

        WebElement pigCostume = getDriver().findElement(By.xpath("//*[@id='page-content']/div[2]/div[2]/div[2]/div[1]/div/section[2]/article[2]/a"));
        pigCostume.click();

        WebElement smallSize = getDriver().findElement(By.xpath("//*[@id='variation-Size']/div[2]/div[2]/div/label"));
        smallSize.click();

        WebElement addToCartButton = getDriver().findElement(By.xpath("//*[@id='buybox']/div[1]/div/form/div[2]/div[1]/a"));
        addToCartButton.click();


        WebElement checkCart = getDriver().findElement(By.xpath("//*[@id='smartshelf-minicart']/div[5]/div[1]/a"));
        Assert.assertTrue(checkCart.isDisplayed());


    }

    @Test
    private void SergeyBrigEddToCartTest() {
        getDriver().get("https://www.webstaurantstore.com");

        getDriver().findElement(By.xpath("//input[@id = 'searchval']")).sendKeys("220SPRDWNEN3"+ "\n");

        List<WebElement> sizeVariations = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (int i = 0; i < sizeVariations.size(); i++) {
            if(sizeVariations.get(i).getText().contains("5 1/2\"")) {
                sizeVariations.get(i).click();
                break;
            }
        }
        getDriver().findElement(By.xpath("//input[@id ='qty']")).sendKeys("2");
        getDriver().findElement(By.xpath("//input[@id ='buyButton']")).click();
        getDriver().findElement(By.xpath("//a[@data-testid='cart-nav-link']")).click();
        WebElement orderItem = getDriver().findElement(By.xpath("//span[@id= 'cartItemCountSpan']"));

        Assert.assertEquals(orderItem.getText(), "21");

    }
}

