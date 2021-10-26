import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Group_eat_and_drink_JavaTest {

    private WebDriver driver;

    private static final String URL = "https://www.godaddy.com/";
    private static final By EXERCISE1 = By.xpath("//*[@id=\"w3-exerciseform\"]/div/div/pre/input[1]");
    private static final By EXERCISE2 = By.name("ex2");
    private static final By EXERCISE3 = By.name("ex3");
    private static final By NEXTEX = By.id("correctnextbtn");
    private static final String URLWEB = "http://www.diamondpeak.com/";

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

    @Test(priority = 1)
    public void testFindCorrectItem() {

        driver.get("https://askent.ru/");
        WebElement bags = driver.findElement(By.xpath("//a[@href=\"https://askent.ru/cat/zhenskoe/filter/kollektsiya-is-9b65b8c3-fe04-11e8-80be-18a905775a6f/apply/\"]"));
        bags.click();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[contains(text(),'Показать ещё')]")).click();
        driver.findElement(By.xpath("//div[@class=\"productItem__link\"]/a[@href=\"/cat/sumki/ryukzak_63/\"]")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://askent.ru/cat/sumki/ryukzak_63/");
    }

    @Test(priority = 2)
    public void testItemToBasket() {

        driver.get("https://askent.ru/cat/sumki/ryukzak_63/");

        driver.findElement(By.xpath("//div[@class='productCols']//div[@class='product__colorBlock']//div[3]//div[1]")).click();
        driver.findElement(By.xpath("//div[@id='fixed_block']//span[contains(text(),'Добавить в корзину')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(text(),'Перейти в корзину')]")).click();
        WebElement addOneItem = driver.findElement(By.xpath("//div[@class='basketItem__add']"));
        addOneItem.click();
        driver.navigate().refresh();
        driver.findElement(By.xpath("//button[contains(text(),'Продолжить оформление заказа')]")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://askent.ru/order/");
    }

    @Test(priority = 3)
    public void testSingIn() {

        driver.get("https://askent.ru/order/");

        WebElement userLogin = driver.findElement(By.xpath("//div[@class='items_block']//input[@name='USER_LOGIN']"));
        userLogin.sendKeys("ziz@gmail.com");
        WebElement userPassword = driver.findElement(By.xpath("//div[@class='items_block']//input[@name='USER_PASSWORD']"));
        userPassword.sendKeys("1111AAAAaaaa");
        WebElement signIn = driver.findElement(By.xpath("//a[@class='control_link'][contains(text(),'Войти')]"));
        signIn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement message = driver.findElement(By.xpath("//*[contains(text(),'Не верный логин или пароль')]"));

        Assert.assertEquals(message.getText(), "Не верный логин или пароль");
    }

    @Test
    public void testOlenaKSearches() {
        driver.get("https://www.kobo.com/");
        String bookName = "Harry Potter";

        WebElement searchField = driver.findElement(By.name("query"));
        searchField.sendKeys(bookName + "\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//h2[@class='title product-field']/child::a"));
        Assert.assertTrue(itemList.size() != 0);
        for (WebElement item : itemList) {
            Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                    contains(bookName.toLowerCase(Locale.ROOT)));
        }
    }

    @Test
    public void testOlenaKFindByIsbn() {
        driver.get("https://www.kobo.com/");
        String numberIsbn = "9781781103326";
        String expectedResult = "Harry Potter en de Steen der Wijzen";

        WebElement searchField = driver.findElement(By.name("query"));
        searchField.sendKeys(numberIsbn + "\n");

        WebElement bookTitle = driver.findElement(By.xpath("(//h2[@class = 'title product-field'])[1]"));
        String actualResult = bookTitle.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRegistrationTatianaT() throws InterruptedException {
        driver.get("https://humans.net/");
        WebElement signUp = driver.findElement
                (By.xpath("//a[text()='Sign up']"));
        signUp.click();
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("humans");

        WebElement joinNow = driver.findElement(By.id("reg-step-1"));
        joinNow.click();

        WebElement codeBox1 = driver.findElement(By.name("digit0"));
        codeBox1.sendKeys("1");

        WebElement codeBox2 = driver.findElement(By.name("digit1"));
        codeBox2.sendKeys("2");

        WebElement codeBox3 = driver.findElement(By.name("digit2"));
        codeBox3.sendKeys("3");

        WebElement codeBox4 = driver.findElement(By.name("digit3"));
        codeBox4.sendKeys("4");

        WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
        continueButton.click();

        WebElement error = driver.findElement(By.xpath("//div[text()='Incorrect verification code']"));

        Assert.assertEquals(error.getText(), "Incorrect verification code");
    }

    @Test
    public void testLogInIncorrectValuesTatianaT() throws InterruptedException {
        driver.get("https://humans.net/registration");
        WebElement logIn = driver.findElement(By.xpath("//span[text()='Log in']"));
        logIn.click();
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("humans");

        WebElement continueButton = driver.findElement(By.xpath("//button[@type='submit']/span"));
        continueButton.click();

        WebElement error = driver.findElement(By.xpath("//span[text()='Incorrect login or password']"));

        Assert.assertEquals(error.getText(), "Incorrect login or password");

    }

    @Test
    void testSergeA_navigateMainPage() {
        driver.get(URL);
        expectedOrActualResult("https://www.godaddy.com/", URL);
    }

    @Test
    void testSergeA_searchDomain() {
        testSergeA_navigateMainPage();
        String name = "google.com";
        WebElement buttonNameForSearch = driver.findElement(By.className("searchText"));
        buttonNameForSearch.click();
        WebElement inputSearchDomain = driver.findElement(By.xpath("//input[@type='text'][1]"));

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
        driver.get(URL);
    }

    public void completeExerciseCorrect() {

        WebElement learnJava = driver.findElement(By.xpath("//*[@id=\"main\"]/div[6]/div/div[3]/div/a"));
        learnJava.click();
        driver.findElement(EXERCISE1).sendKeys("System");
        driver.findElement(EXERCISE2).sendKeys("out");
        driver.findElement(EXERCISE3).sendKeys("println");
    }

    public void completeExerciseIncorrect() {

        WebElement learnJava = driver.findElement(By.xpath("//*[@id=\"main\"]/div[6]/div/div[3]/div/a"));
        learnJava.click();
        driver.findElement(EXERCISE1).sendKeys("print");
        driver.findElement(EXERCISE2).sendKeys("out");

        driver.findElement(EXERCISE3).sendKeys("phrase");
    }

    public void proceedToResultPage() {

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"w3-exerciseform\"]/div/button"));
        submit.click();

        System.out.println("current URL " + driver.getCurrentUrl());
        System.out.println("current page title " + driver.getTitle());

        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());


        driver.switchTo().window(newTb.get(1));

        System.out.println("current URL " + driver.getCurrentUrl());
        System.out.println("current page title " + driver.getTitle());

        WebElement answerButton = driver.findElement(By.xpath("//*[@id=\"answerbutton\"]"));
        answerButton.click();
    }

    @Test
    public void testElenauSIncorrectResultCheck() {
        navigateToPage();
        completeExerciseIncorrect();
        proceedToResultPage();

        WebElement warning = driver.findElement(By.xpath("//*[@id=\"assignmentNotCorrect\"]/h2"));

        Assert.assertEquals(warning.getText(), "Not Correct");
    }

    @Test
    public void testElenauSCorrectResultCheck() {

        navigateToPage();
        completeExerciseCorrect();
        proceedToResultPage();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"assignmentCorrect\"]/h2"));

        Assert.assertEquals(result.getText(), "Correct!");
    }

    @Test
    public void testElenauSAssignmentList() {

        navigateToPage();
        completeExerciseCorrect();
        proceedToResultPage();

        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement l : links) {
            String name = l.getText();
            System.out.println(name);
        }
        driver.findElement(NEXTEX).click();

        WebElement description = driver.findElement(By.xpath("//*[@id=\"assignmenttext\"]/p"));

        Assert.assertEquals(description.getText(), "Comments in Java are written with special characters. Insert the missing parts:");
    }

    @Test
    public void SergeyBrigMenuTest() {

        String expectedResult = "http://automationpractice.com/index.php?id_product=1&controller=product";

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        WebElement shirtLink = driver.findElement(By.xpath("//div[@id = 'block_top_menu']/ul/li[3]/a"));
        shirtLink.click();
        WebElement productLink = driver.findElement(By.xpath("//a[@class = 'product_img_link']"));
        productLink.click();
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void OlenaMSearchTheItemTest() {

        driver.get("https://www.homedepot.com/");
        driver.findElement(By.id("headerSearch")).sendKeys("refrigerator\n");
        driver.findElement(By.id("headerSearchButton")).click();

        driver.findElement(By.linkText("Shop All French Door Refrigerators")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.homedepot.com/b/Appliances-Refrigerators-French-Door-Refrigerators/N-5yc1vZc3oo");
    }

    @Test
    public void OlenaMSignInTest() {

        driver.get("https://www.homedepot.com/");

        driver.findElement(By.id("headerMyAccount")).click();
        driver.findElement(By.id("SPSOSignIn")).click();

        driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("password-input-field")).sendKeys("ZXCasd123");
        driver.findElement(By.xpath("//span[normalize-space()='Sign In']")).click();

        WebElement error = driver.findElement(By.xpath("//span[@class='alert-inline__message']"));
        Assert.assertEquals(error.getText(), "For your protection, we've locked your account for a short period of time. You may try logging in again in 15 minutes. If you've forgotten your password, you may change it using the link below.");

    }

    @Test
    public void SergeyBrigSearchTest() {

        driver.get("https://www.webstaurantstore.com");

        final String searchText = "fork";
        driver.findElement(By.id("searchval")).sendKeys(searchText + "\n");
        List<WebElement> itemList = driver.findElements(By.xpath("//div/a[@data-testid='itemDescription']"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test
    public void SergeyBrigBrandMenuTest() {
        driver.get("https://www.webstaurantstore.com");

        driver.findElement(By.xpath("//a[@title='Amana Commercial Microwaves']")).click();

        List<WebElement> brandList = driver.findElements(By.xpath("//p[@class = 'description category_name']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains("amana"));
        }
    }

    @Test
    public void testSearchFieldFindJobTatianaT() {
        driver.get("https://humans.net/");

        WebElement searchField = driver.findElement(By.xpath("//input[@role='combobox']"));
        searchField.sendKeys("Engineering");
        WebElement fieldLocation = driver.findElement(By.xpath("//button[@type='button']/div"));
        fieldLocation.click();
        WebElement fieldCity = driver.findElement(By.xpath("//input[@placeholder='City']"));
        fieldCity.sendKeys("Seattle");
        WebElement city = driver.findElement(By.xpath("//span[text()='Seattle']"));
        city.click();
        WebElement find = driver.findElement(By.xpath("//button[text()='Find']"));
        find.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://humans.net/findwork/all/any/Seattle%20WA/?q=Engineering");
    }

    @Test
    public void SergeyBrigBrandSearch() {

        driver.get("https://www.webstaurantstore.com");

        driver.findElement(By.xpath("//input[@id = 'searchval']")).sendKeys("cup \n");

        List<WebElement> brandList = driver.findElements(By.xpath("//a[@ data-testid='itemDescription']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains("cup"));
        }
    }

    @Test
    public void SergeyBrigMenu2Test() {
        driver.get("https://www.webstaurantstore.com");

        List<WebElement> menuList = driver.findElements(By.xpath("//div[@class = 'm-0 lt:flex']/a"));
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getText().toLowerCase().contains("furniture")) {
                menuList.get(i).click();
                break;
            }
        }
        List<WebElement> categoryList = driver.findElements(By.xpath("//div/a/h2"));
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getText().contains("Hotel Furniture")) {
                categoryList.get(i).click();
                break;
            }
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.webstaurantstore.com/64111/hotel-furniture.html");
    }

    @Test
    public void testDiamondPage() throws InterruptedException {
        driver.get(URLWEB);
        WebElement title = driver.findElement(By.xpath("//a[@class='logo']"));

        String actualResult = title.getText();
        assertEquals(actualResult, "DIAMOND PEAK");
        List<WebElement> navBar = driver.findElements(By.xpath("//ul[@class ='navbar row']"));
        for (int i = 0; i < navBar.size(); i++) {
            assertEquals(navBar.get(i).getText(), "DIAMOND PEAK\n" +
                    "THE MOUNTAINTICKETS & PASSES\n" +
                    "LESSONS & RENTALSPLAN A VISIT");
        }

    }

    @Test
    public void OlgaNTest() throws InterruptedException {

        driver.get("https://www.vacasa.com/");

        WebElement searchField = driver.findElement(By.xpath("//input[@class = 'form-control hero-search-field col input-with-icon ui-autocomplete-input']"));
        searchField.sendKeys("Charlotte");


        WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-v-primary px-6 search-button hero-search-button']"));

        searchButton.click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.vacasa.com/search?place=/usa/Vermont/Charlotte/");
        driver.quit();
    }
}

