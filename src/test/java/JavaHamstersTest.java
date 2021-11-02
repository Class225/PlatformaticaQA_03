import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class JavaHamstersTest extends BaseTest {

    private final String URL_IK = "https://www.vprok.ru/";
    private static final String MAIN_PAGE_URL = "http://automationpractice.com/index.php";
    private static final String SAUSEDEMO_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void maximizeScreen() {
        getDriver().manage().window().maximize();
    }

    @Test
    public void testPavelSipatySearchResult() {

        getDriver().get("https://www.webstaurantstore.com/");

        WebElement search = getDriver().findElement(By.id("searchval"));
        search.sendKeys("stainless work table\n");

        List<WebElement> listOfTableDescriptions = getDriver().findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        if (!listOfTableDescriptions.isEmpty()) {
            WebElement element = listOfTableDescriptions.get(listOfTableDescriptions.size() - 1);
        }

        for (int i = 0; i < listOfTableDescriptions.size(); i++) {
            Assert.assertTrue(listOfTableDescriptions.get(i).getText().contains("Table"), "All found goods have word \"Table\" in description");
        }

    }

    @Test
    public void testPavelSipatyLogInSuccess() {

        String expectedUrl = "https://www.saucedemo.com/inventory.html";

        getDriver().get(SAUSEDEMO_URL);
        WebElement username = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        getDriver().findElement(By.xpath("//input[@id='login-button']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl);
    }

    @Test
    public void testAddToCartAllElementsMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        List<WebElement> addToCart = getDriver().findElements(By.xpath("//button[text()='Add to cart']"));
        for (int i = 0; i<addToCart.size(); i++) {
            addToCart.get(i).click();
        }
        WebElement cartCount = getDriver().findElement(By.xpath("//span[text()='6']"));
        Assert.assertTrue(cartCount.getText().contains("6"));
    }

    @Test
    public void testUserFlowMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement addToCart = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement cart = getDriver().findElement(By.xpath("//div[@id='shopping_cart_container']"));

        addToCart.click();
        cart.click();

        WebElement checkoutButton = getDriver().findElement(By.xpath("//button[text()='Checkout']"));
        checkoutButton.click();

        WebElement firstName = getDriver().findElement(By.id("first-name"));
        WebElement lastName = getDriver().findElement(By.id("last-name"));
        WebElement postalCode = getDriver().findElement(By.id("postal-code"));

        firstName.sendKeys("Anton");
        lastName.sendKeys("Petrov");
        postalCode.sendKeys("425430");

        WebElement continueButton = getDriver().findElement(By.id("continue"));

        continueButton.click();

        WebElement finishButton = getDriver().findElement(By.xpath("//button[text()='Finish']"));

        finishButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));

        Assert.assertTrue(actualResult.getText().contains("THANK YOU FOR YOUR ORDER"));
    }

    @Test
    public void testLoginLockedUserMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("locked_out_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement error = getDriver().findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(error.getText().contains("Epic sadface: Sorry, this user has been locked out"));
    }

    @Test
    public void testZTADropDownListMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement dropDownList = getDriver().findElement(By.xpath("//select[@class='product_sort_container']"));
        Select selectDropDown = new Select(dropDownList);
        selectDropDown.selectByValue("za");
        WebElement zElement = getDriver().findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']"));

        Assert.assertTrue(zElement.getText().contains("Test"));
    }

    @Test
    public void testLowToHighDropDownListMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement dropDownList = getDriver().findElement(By.xpath("//select[@class='product_sort_container']"));
        Select selectDropDown = new Select(dropDownList);
        selectDropDown.selectByValue("lohi");
        List<WebElement> productPrice = getDriver().findElements(By.xpath("//div[@class='inventory_item_price']"));

        String prise1 = productPrice.get(0).getText();
        String prise2 = productPrice.get(productPrice.size()-1).getText();

        double priceDouble1 = Double.parseDouble(prise1.replaceAll("[^.0-9]", ""));
        double priceDouble2 = Double.parseDouble(prise2.replaceAll("[^.0-9]", ""));

        System.out.println(priceDouble1 + " < " + priceDouble2);

        Assert.assertTrue(priceDouble1 < priceDouble2);
    }

    @Test
    public void testHighToLowDropDownListMaximGolubtsov() {
        getDriver().get(SAUSEDEMO_URL);
        WebElement login = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement button = getDriver().findElement(By.xpath("//input[@id='login-button']"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement dropDownList = getDriver().findElement(By.xpath("//select[@class='product_sort_container']"));
        Select selectDropDown = new Select(dropDownList);
        selectDropDown.selectByValue("hilo");
        List<WebElement> productPrice = getDriver().findElements(By.xpath("//div[@class='inventory_item_price']"));

        String prise1 = productPrice.get(0).getText();
        String prise2 = productPrice.get(productPrice.size()-1).getText();

        double priceDouble1 = Double.parseDouble(prise1.replaceAll("[^.0-9]", ""));
        double priceDouble2 = Double.parseDouble(prise2.replaceAll("[^.0-9]", ""));

        System.out.println(priceDouble1 + " > " + priceDouble2);

        Assert.assertTrue(priceDouble1 > priceDouble2);
    }

    @Test
    public void IlyaKorolkovPopUpExistsTest() {
        getDriver().get(URL_IK);

        WebElement popUp = getDriver().findElement(By.className("fo-cookies-policy"));

        Assert.assertTrue(popUp.isDisplayed());
    }

    @Test
    public void IlyaKorolkovClosePopUpTest() throws InterruptedException {
        getDriver().get(URL_IK);

        WebElement closePopUpButton = getDriver().findElement(By.className("fo-cookies-policy__close-btn"));

        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click()", closePopUpButton);

        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        Thread.sleep(2000);

        List<WebElement> popUp = getDriver().findElements(By.className("fo-cookies-policy"));
        Assert.assertTrue(popUp.isEmpty());
    }

    @Ignore
    @Test
    public void IlyaKorolkovChangeRegionTest() throws InterruptedException {
        getDriver().get(URL_IK);

        final String region = "Свердловская обл.";

        WebElement changeRegionLink = getDriver().findElement(By.className("js-address-data"));
        changeRegionLink.click();

        Thread.sleep(3000);

        WebElement regionToSelect = getDriver().findElement(By.xpath("//div[@id='form_popup-polygons']//a[contains(text(), '" + region + "')]"));
        regionToSelect.click();

        Thread.sleep(1000);

        Assert.assertEquals(getDriver().findElement(By.className("js-address-data")).getText().trim(), region);
    }

    @Ignore
    @Test
    public void AlexeyKhomozovRegionListCountTest() {

        getDriver().get(URL_IK);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-geolocation");

        WebElement changeRegionButton = getDriver().findElement(By.className("xfnew-header__change-region"));
        changeRegionButton.click();

        List<WebElement> regionsCount = getDriver().findElements(By.xpath(
                "//div/a[contains(@class, 'change-region__region') and contains(@class, 'change-region__region-online')]"));
        int actualRegionsCount = regionsCount.size();
        int expectedRegionsCount = 55; //ожидаемое на момент реализации теста

        Assert.assertEquals(actualRegionsCount, expectedRegionsCount);
    }

    @Test
    public void IlyaKorolkovSearchTest() {
        getDriver().get(URL_IK);

        final String textToSearch = "хамон";

        getDriver().findElement(By.xpath("//div[@id='main-app']//input[@name='search']")).sendKeys(textToSearch + "\n");

        List<WebElement> searchResults = getDriver().findElements(By.xpath("//ul[@id='catalogItems']//a[contains(@class, 'xf-product-title__link')]"));

        for (int i = 0; i < searchResults.size(); i++) {
            Assert.assertTrue(searchResults.get(i).getText().toLowerCase().contains(textToSearch));
        }
    }

    @Test
    public void IlyaKorolkovLinkToPromosTest() {
        getDriver().get(URL_IK);

        String expectedUrl = "https://www.vprok.ru/promos";

        getDriver().findElement(By.xpath("//a[@target='_self' and contains(text(), 'Акции')]")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl);
    }

    @Test
    public void testArtsiomAzarankaTextBox() {
        getDriver().get("https://demoqa.com/text-box");

        String FullName = "Marko Polo";
        String UserEmail = "test@test.com";
        String CurrentAddress = "street";
        String PermanentAddress = "ulica";


        getDriver().findElement(By.xpath("//input[@placeholder='Full Name']")).sendKeys(FullName);
        getDriver().findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(UserEmail);
        getDriver().findElement(By.xpath("//textarea[@placeholder='Current Address']")).sendKeys(CurrentAddress);
        getDriver().findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys(PermanentAddress);

        getDriver().findElement(By.xpath("//button[@id='submit']")).click();

        WebElement name = getDriver().findElement(By.xpath("//p[@id='name']"));
        WebElement email = getDriver().findElement(By.xpath("//p[@id='email']"));
        WebElement currentAddress = getDriver().findElement(By.xpath("//p[@id='currentAddress']"));
        WebElement permanentAddress = getDriver().findElement(By.xpath("//p[@id='permanentAddress']"));

        Assert.assertEquals(name.getText(), "Name:" + FullName);
        Assert.assertEquals(email.getText(), "Email:" + UserEmail);
        Assert.assertEquals(currentAddress.getText(), "Current Address :" + CurrentAddress);
        Assert.assertEquals(permanentAddress.getText(), "Permananet Address :" + PermanentAddress);
        //тест написан на тот результат, который выдаёт сайт (есть баг - ошибка в слове Permanent на выводе результата)


    }

    @Test
    public void testArtsiomAzarankaButtons() {

        Actions actions = new Actions(getDriver());

        getDriver().get("https://demoqa.com/buttons");


        WebElement doubleclick = getDriver().findElement(By.xpath("//button[@id='doubleClickBtn']"));
        actions.doubleClick(doubleclick).perform();

        WebElement rightclick = getDriver().findElement(By.xpath("//button[@id='rightClickBtn']"));
        actions.contextClick(rightclick).perform();

        WebElement oneclicl = getDriver().findElement(By.xpath("//button[text()='Click Me']"));
        actions.click(oneclicl).perform();

        WebElement doubleClickMessage = getDriver().findElement(By.xpath("//p[@id='doubleClickMessage']"));
        WebElement rightClickMessage = getDriver().findElement(By.xpath("//p[@id='rightClickMessage']"));
        WebElement dynamicClickMessage = getDriver().findElement(By.xpath("//p[@id='dynamicClickMessage']"));

        Assert.assertEquals(doubleClickMessage.getText(), "You have done a double click");
        Assert.assertEquals(rightClickMessage.getText(), "You have done a right click");
        Assert.assertEquals(dynamicClickMessage.getText(), "You have done a dynamic click");

    }

    @Test
    public void testNataliaSavinovaContactUsButton() {
        getDriver().get(MAIN_PAGE_URL);
        getDriver().findElement(By.xpath("//a[@title = 'Contact Us']")).click();

        WebElement result = getDriver().findElement(By.xpath("//h1[@class = 'page-heading bottom-indent']"));

        Assert.assertEquals(result.getText(), "CUSTOMER SERVICE - CONTACT US");
    }

    @Ignore
    @Test
    public void testNataliaSavinovaSignInButton() {
        getDriver().get(MAIN_PAGE_URL);
        getDriver().findElement(By.xpath("//a[@title = 'Log in to your customer account']")).click();

        WebElement result = getDriver().findElement(By.xpath("//h1[@class = 'page-heading']"));

        Assert.assertEquals(result.getText(), "AUTHENTICATION");
    }

    @Ignore
    @Test
    public void testNataliaSavinovaSaleLink() {
        getDriver().get(MAIN_PAGE_URL);
        getDriver().findElement(By.xpath("//div[@id = 'htmlcontent_top']//li[@class = 'htmlcontent-item-1 col-xs-4']")).click();

        String result = getDriver().getCurrentUrl();

        Assert.assertEquals(result, "https://www.prestashop.com/en");
    }

    @Test
    public void NadezdhaDekhandLogo() {
        getDriver().get("https://gb.ru/");

        WebElement link = getDriver().findElement(By.className("mn-header__logo-link"));
        link.click();
        assertEquals(getDriver().getCurrentUrl(), "https://gb.ru/");
    }

    @Ignore
    @Test
    public void NadezdhaDekhandEnter() {
        getDriver().get("https://gb.ru/");

        WebElement userPlan = getDriver().findElement(By.xpath("//div[contains(@class,'mn-header__left')]//a[text()='Мероприятия']"));
        userPlan.click();
        WebElement privat = getDriver().findElement(By.linkText("Личные консультации"));
        privat.click();
        WebElement form = getDriver().findElement(By.linkText("Записаться"));
        form.click();
        WebElement name1User = getDriver().findElement(By.id("full_name-3"));
        WebElement name2User = getDriver().findElement(By.id("full_name-4"));
        WebElement emailUser = getDriver().findElement(By.id("email-2"));
        WebElement phoneUser = getDriver().findElement(By.id("phone-2"));
        WebElement login = getDriver().findElement(By.cssSelector("#wf-form-email-form > input.submit-button.w-button"));
        name1User.sendKeys("gdhghd");
        name2User.sendKeys("fgdrgtr");
        emailUser.sendKeys("abc@gmail.com");
        phoneUser.sendKeys("9999999999");


        login.click();
        assertEquals(getDriver().getCurrentUrl(), "https://gb.ru/events/personal-consultation#form");
    }

    @Ignore
    @Test
    public void testSearchAndreiShupaev() {
        getDriver().get("http://automationpractice.com/");
        WebElement input = getDriver().findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");
        WebElement dress = getDriver().findElement(By.className("lighter"));

        Assert.assertEquals(dress.getText(), "\"DRESS\"");

    }

    @Test
    public void testContactUsAndreiShupaev() {
        getDriver().get("http://automationpractice.com/");
        WebElement contactUs = getDriver().findElement(By.id("contact-link"));
        contactUs.click();

        WebElement email = getDriver().findElement(By.id("email"));
        email.sendKeys("email@gmail.com");

        WebElement order = getDriver().findElement(By.id("id_order"));
        order.sendKeys("35");

        WebElement message = getDriver().findElement(By.id("message"));
        message.sendKeys("I have a problem!");

        WebElement buttonSend = getDriver().findElement(By.id("submitMessage"));
        buttonSend.click();

        WebElement error = getDriver().findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li"));
        Assert.assertEquals(error.getText(), "Please select a subject from the list provided.");

    }

    @Ignore
    @Test
    public void testLogInWrongCredentialsAlexKapran () {

        getDriver().get("https://www.theperfectloaf.com/");
        getDriver().findElement(By.xpath("//a[@class='tpl-membership__button']" +
                "/span[@class='tpl-membership__arrow']")).click();

        getDriver().switchTo().frame("memberful-iframe-for-overlay");

        getDriver().findElement(By.xpath("//div[@class='mt-4 text-center']/button[@class='underline']")).click();
        getDriver().findElement(By.xpath("//div[@class='mb-3']" +
                "/input[@placeholder='Email']")).sendKeys("markus.lorg@gmail.com");
        getDriver().findElement(By.xpath("//div[@class='mb-3']" +
                "/input[@placeholder='Password']")).sendKeys("QA_Hamster1!");

        getDriver().findElement(By.xpath("//div[@data-display-if-target='#session_mode']/input[@value='Sign in']")).click();

        WebElement error = getDriver().findElement(By.xpath("//div[@class='error_explanation']/p"));
        Assert.assertEquals(error.getText(), "Wrong email or password.");
    }

    @Test
    public void testSearchAlexKapran(){
        getDriver().get("https://www.theperfectloaf.com/");
        getDriver().findElement(By.xpath("//a[@class='search_icon']/i[@class='fa fa-search search__icon']")).click();
        getDriver().findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Bread\n");

        List<WebElement> searchList = getDriver().findElements(By.xpath("//div[@class='ais-hits--item']/" +
                "/h2[@itemprop='name headline']//a[contains(@title,'Bread')]"));
        for (int i = 0; i < searchList.size(); i++){
            Assert.assertTrue(searchList.get(i).getText().toLowerCase().contains("bread"));
        }
    }

    @Test
    public void testErrorPasswordBabkinaKatya(){

        getDriver().get("https://pravoved.ru/");

        WebElement loginButton = getDriver().findElement(By.linkText("Войти"));

        loginButton.click();


        WebElement username = getDriver().findElement(By.id("email"));
        WebElement password = getDriver().findElement(By.id("password"));
        WebElement loginSubmit = getDriver().findElement(By.id("loginSubmit"));


        username.sendKeys("abc@gmail.com");
        password.sendKeys("yourPassword");
        loginSubmit.click();

        WebElement error = getDriver().findElement(By.xpath("//div[@id=\"wrapper\"]//*[contains(@class,\"errors\")]/li[2]"));

        Assert.assertEquals(error.getText(), "Неправильная пара логин-пароль!\n" + "Авторизоваться не удалось.");
    }

    @Test
    public void testSearchBabkinaKatya() {

        getDriver().get("https://pravoved.ru/");

        WebElement questionButton = getDriver().findElement(By.xpath("//div[@class='Header_navigation__1am_z']//a[@href='/questions/']"));

        questionButton.click();

        getDriver().findElement(By.id("questions-page-search")).sendKeys("договор\n");
        List<WebElement> itemList = getDriver().findElements(By.xpath("//div[contains(@class, 'prvd-questions-list')]//div[@class='divH3']/a"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase(Locale.ROOT).contains("договор"));
        }
    }
    @Test
    void testLoginErrorChapaevAleksei(){

        getDriver().get("https://www.yaplakal.com/act/Login/CODE/00/?return=");

        WebElement userName = getDriver().findElement(By.name("UserName"));
        userName.sendKeys("abc@gmail.com");

        WebElement userPassword = getDriver().findElement(By.name("PassWord"));
        userPassword.sendKeys("your_password");

        WebElement login = getDriver().findElement(By.name("submit"));
        login.click();

        WebElement error = getDriver().findElement(By.className("postcolor"));
        Assert.assertEquals(error.getText(), "Проверка на бота не пройдена (invalid-input-response)");

    }

    @Test
    void testOnMainPageChapaevAleksei(){

        getDriver().get("https://www.yaplakal.com/forum7/topic2342386.html");

        WebElement mainPage = getDriver().findElement(By.xpath("//*[@id=\"top-logo\"]/a/img"));
        mainPage.click();

        WebElement toMainPage = getDriver().findElement(By.xpath("//*[@id=\"tabs\"]/a[1]"));
        Assert.assertEquals(toMainPage.getText(), "Главная");
    }
}