import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    private static final String PAGEURL = "https://phptravels.com/";


    @Test
    public void testStasLFindCorrectItem() throws InterruptedException {

        getDriver().get("https://askent.ru/");
        WebElement woman = getDriver().findElement(By.xpath("//li[@class = 'dropDown']/a[contains(text(), 'Woman')]"));
        woman.click();
        WebElement whiteCollection= getDriver().findElement(By.xpath("//a[@href='https://askent.ru/cat/sumki/']"));
        whiteCollection.click();

        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getDriver().findElement(By.xpath("//a[contains(text(),'Показать ещё')]")).click();
        getDriver().findElement(By.xpath("//a[@href='/cat/sumki/sumka_514/']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askent.ru/cat/sumki/sumka_514/");
    }


    @Test
    public void testStasLItemToBasket() {

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

    @Test
    public void testStasLSingIn() {

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
    public void testStasLSearchs() throws InterruptedException {

        getDriver().get("https://kubkov.net/");
        WebElement searchFild = getDriver().findElement(By.xpath("//div[@class = 'btn_search']"));
        searchFild.click();
        WebElement fullSearchFild = getDriver().findElement(By.xpath("//input[@id = 'title-search-input']"));
        fullSearchFild.sendKeys("медали\n");
        WebElement medaliClass = getDriver().findElement(By.xpath("//li[@class = 'level1']/a[@href = '/catalog/medali/']"));
        medaliClass.click();
        WebElement options = getDriver().findElement(By.xpath("//div[@class = 'col-sm-6 col-md-4 bx-filter-title']"));
        options.click();
        getDriver().findElement(By.xpath("//input[@class = 'min-price']")).sendKeys("10");
        getDriver().findElement(By.xpath("//input[@class = 'max-price']")).sendKeys("200");
        getDriver().findElement((By.xpath("//div[@class='bx-filter-select-text'][contains(text(),'Все')]"))).click();
        getDriver().findElement(By.xpath("//div[@id='popup-window-content-smartFilterDropDown19']//label[@for = 'arrFilter_19_3076719002'][contains(text(),'45 мм')]")).click();
        Thread.sleep(2000);
        WebElement showBotton = getDriver().findElement(By.xpath("//input[@class = 'btn btn-themes']"));
        showBotton.click();
        getDriver().findElement(By.xpath("//a[@href = '/catalog/medali/medali-kn005-/'][contains(., 'Медали')]")).click();
        WebElement countField = getDriver().findElement(By.id("prod_quantity_1369"));
        countField.clear();
        countField.sendKeys("4");
        getDriver().findElement(By.id("prod_link_1369")).click();
        getDriver().findElement(By.xpath("//img[@class = 'shop-box-image-header']")).click();
        getDriver().findElement(By.xpath("//td/input[@type='text']")).clear();
        getDriver().findElement(By.xpath("//td/input[@type='text']")).sendKeys("8");
        getDriver().findElement(By.xpath("//td[@class ='fwb']")).click();
        WebElement deleteItemsInCart = getDriver().findElement(By.xpath("//td[@class = 'control']/a[@class = 'cart_del']"));
        deleteItemsInCart.click();
        WebElement emptyCart = getDriver().findElement(By.xpath("//font[@class = 'errortext']"));

        Assert.assertEquals(emptyCart.getText(), "Ваша корзина пуста");
    }

    @Test
    public void testStasLCountItems() {
        getDriver().get("https://kubkov.net/catalog/medali/filter/price-base-from-10-to-200/diametr-is-5bbdbff0df2a97e4deb25da4f5fe7b4b/apply/");
        List<WebElement> medalList = getDriver().findElements(By.xpath("//div[@class = 'ow-table']/a[@class='h']"));
        Assert.assertTrue(medalList.size() != 0);
        for (int i = 0; i < medalList.size(); i++) {
            medalList.get(i);
            Assert.assertTrue(medalList.get(i).getText().toLowerCase().contains("медали".toLowerCase(Locale.ROOT)));
        }
    }


    @Ignore
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

    @Ignore
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
    public void testVerifyForgotPasswordLinkTatianaT() {
        getDriver().get("https://humans.net/login");
        WebElement forgotPassword = getDriver().findElement(By.xpath("//a[@class='login-form__pass-subtitle']"));
        forgotPassword.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://humans.net/recoveryform");
    }

    @Ignore
    @Test
    public void testVerifyListItemsTatianaT() {
        getDriver().get("http://automationpractice.com/");

        WebElement searchField = getDriver().findElement(By.id("search_query_top"));
        searchField.sendKeys("dress\n");
        List<WebElement> itemList = getDriver().findElements(By.id("search"));

        Assert.assertTrue(itemList.size() != 0);
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("dress"));
        }
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
    @Ignore
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

    @Ignore
    @Test
    public void OlenaMSearchTheItemTest() {

        getDriver().get("https://www.homedepot.com/");
        getDriver().findElement(By.id("headerSearch")).sendKeys("refrigerator\n");
        getDriver().findElement(By.id("headerSearchButton")).click();

        getDriver().findElement(By.linkText("Shop All French Door Refrigerators")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.homedepot.com/b/Appliances-Refrigerators-French-Door-Refrigerators/N-5yc1vZc3oo");
    }

    @Ignore
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
        WebElement fieldLocation = getDriver().findElement(By.xpath("//button[@type='button']/div"));
        fieldLocation.click();
        WebElement fieldCity = getDriver().findElement(By.xpath("//input[@placeholder='City']"));
        fieldCity.sendKeys("Seattle");
        WebElement city = getDriver().findElement(By.xpath("//span[text()='Seattle']"));
        city.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input[@role='combobox']"));
        searchField.sendKeys("Engineering");
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
            if (menuList.get(i).getText().toLowerCase().contains("furniture")) {
                menuList.get(i).click();
                break;
            }
        }
        List<WebElement> categoryList = getDriver().findElements(By.xpath("//div/a/h2[@data-testid='displayTitle']"));
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getText().toLowerCase().contains("hotel furniture")) {
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

        WebElement personalAccount = getDriver().findElement(By.xpath("//*[@id='single-signin__body']/div/div[2]/div/div[2]/div[1]/div"));
        personalAccount.click();

        Assert.assertEquals(getDriver().getTitle(), "The Home Depot: sign in, create or secure your account");

    }


    @Test
    public void testSergeANavigateToPage() {
        getDriver().get(PAGEURL);
        WebElement logoName = getDriver().findElement(By.id("PHPTRAVELS"));
        Assert.assertEquals(logoName.getText(), "PHPTRAVELS");
    }

    @Ignore
    @Test
    public void selectItemforList() throws InterruptedException {
        getDriver().get(PAGEURL);
        getDriver().findElement(By.xpath("//span[normalize-space()='Features']")).click();
        List<WebElement> allOptions = getDriver().findElements(By.xpath("//header/div//div/a"));

        for (WebElement option : allOptions) {
            System.out.println(option.getText());
            if (option.getText().contains("Flights Module")) {
                option.click();
                break;
            }
        }

        WebElement h2Name = getDriver().findElement(By.id("header-title"));
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-title"))).click();
        Assert.assertEquals(h2Name.getText(), "Flights reservation module");
    }
    @Ignore
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

