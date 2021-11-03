import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class QA_Group_Timur_Test extends BaseTest {

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 10);
    }

    @Test
    public void ZhmakaAndrey() {
        getDriver().get("http://automationpractice.com/index.php");

        getDriver().findElement(By.xpath("//div[@id='block_top_menu']//a[@title='Women']")).click();
        getDriver().findElement(By.xpath("//div[@class='product-image-container']/a[@title='Blouse']")).click();

        WebElement frame = getDriver().findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        getDriver().switchTo().frame(frame);
        getDriver().findElement(By.xpath("//p[@id='add_to_cart']/button[@class='exclusive']")).click();
        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.xpath("//div[@class='button-container']/a[@title='Proceed to checkout']")).click();
        getDriver().findElement(By.xpath("//div[@id='center_column']/p[contains(@class,cart_navigation)]/a[@title='Proceed to checkout']")).click();
        getDriver().findElement(By.xpath("//form[@id='login_form']//input[@data-validate='isEmail']")).sendKeys("mail5432@22.com");
        getDriver().findElement(By.xpath("//form[@id='login_form']//input[@data-validate='isPasswd']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//form[@id='login_form']//button[@id='SubmitLogin']")).click();
        getDriver().findElement(By.xpath("//div[@id='center_column']//button[@name='processAddress']")).click();
        WebElement checkboxTermsOfService = getDriver().findElement(By.id("cgv"));
        checkboxTermsOfService.click();
        if (checkboxTermsOfService.isSelected()) {
            getDriver().findElement(By.xpath("//form[@id='form']//button[@name='processCarrier']")).click();
        }
        getDriver().findElement(By.xpath("//div[@id = 'HOOK_PAYMENT']//a[@class='bankwire']")).click();
        getDriver().findElement(By.xpath("//p[@id = 'cart_navigation']/button[@type = 'submit']")).click();
        WebElement orderComplete = getDriver().findElement(By.xpath("//p[@class = 'cheque-indent']/strong[@class = 'dark']"));

        Assert.assertEquals(orderComplete.getText(), "Your order on My Store is complete.");
    }

    @Test
    public void searcheTestKorelov() {
        getDriver().get("https://petrovich.ru/");
        WebElement search = getDriver().findElement(By.name("q"));
        search.sendKeys("газобетон");

        WebElement find = getDriver().findElement(By.xpath("//button[text()=\"Найти\"]"));
        find.click();

//        WebElement listList = driver.findElement(By.xpath("//*[@data-test=\"view-list\"]"));
//        listList.click();

        WebElement blockList = getDriver().findElement(By.xpath("//*[@data-test=\"view-block\"]"));
        blockList.click();

        List<WebElement> itemList = getDriver().findElements(By.xpath("//*[@data-test=\"product-title\"]"));
        int count = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("газобетон"));
            System.out.println(itemList.get(i).getText());
            count++;

        }
        System.out.println(count);
    }

    @Test
    public void resetLoginKorelov() {
        getDriver().get("https://petrovich.ru/");
        WebElement enterButton = getDriver().findElement(By.xpath("//*[@data-test=\"login-link\"]"));
        enterButton.click();
        WebElement fogPass = getDriver().findElement(By.xpath("//a[text()=\"Забыли пароль?\"]"));
        fogPass.click();
        WebElement title = getDriver().findElement(By.xpath("//*[@class=\"form__title\"]"));
        title.getText();
        System.out.println(title.getText());
        Assert.assertEquals(title.getText(), "Восстановление пароля:");

    }

    @Test
    public void testFelix_IX() throws InterruptedException {
        getDriver().get("https://www.spirit-of-metal.com/");

        getDriver().findElement(By.id("search-toggle")).click();
        String text = "skepticism";
        getDriver().findElement(By.id("GlobalSearchField")).sendKeys(text + "\n");
        getDriver().findElement(By.xpath("//*[@id=\"actuality\"]/div/div/section[1]/div/ul/a")).click();
        sleep(500);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("scroll(0, 1430);");

        getDriver().findElement(By.xpath("//*[@id=\"infinitescroll\"]/div[5]/a")).click();

        WebElement q = getDriver().findElement(By.xpath("//*[@id=\"profile\"]/div[2]/span[2]"));

        Assert.assertEquals(q.getText(), "Ordeal");
    }

    @Test
    public void testAlexeyLugovoy() {
        getDriver().get("https://www.anekdot.ru/");
        WebElement anekdoty = getDriver().findElement(By.xpath("//ul/li/a[text()='Анекдоты']"));
        Actions navesti = new Actions(getDriver());
        navesti.moveToElement(anekdoty).build().perform();
        WebElement bestMonth = getDriver().findElement(By.xpath("//ul/li/a[@title='Самые смешные анекдоты за месяц']"));
        bestMonth.click();
        WebElement h1 = getDriver().findElement(By.cssSelector("body h1"));
        Assert.assertEquals(h1.getText().toUpperCase(), "САМЫЕ СМЕШНЫЕ АНЕКДОТЫ ЗА МЕСЯЦ!");
    }

    @Test
    public void evgenyRogoznev() {
        getDriver().get("https://hh.ru/");
        WebElement signIn = getDriver().findElement(By.xpath("//*[@data-qa='login']"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click();", signIn);
        getDriver().findElement(By.xpath("//*[@data-qa='account-signup-email']")).sendKeys("falseLogin");
        WebElement submitBtn = getDriver().findElement(By.xpath("//*[@data-qa='account-signup-submit']"));
        submitBtn.click();
        List<WebElement> errors = getDriver().findElements(By.xpath("//*[text()='Пожалуйста, укажите email или телефон']"));
        Assert.assertEquals(errors.size(), 1,
                "Сообщение с ошибкой \"Пожалуйста, укажите email или телефон\" отсутствует или их несколько.");

    }

    @Test
    public void Hlopushka() throws InterruptedException {

        getDriver().get("https://naverisk.com/");
        JavascriptExecutor scrollingPage = (JavascriptExecutor) getDriver();
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        Assert.assertEquals(3970, 3970);

        scrollingPage.executeScript("scroll(0,-2700);");
        Assert.assertEquals(-2700, -2700);

        WebElement solutionsBtn = getDriver().findElement(new By.ByXPath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/a"));
        solutionsBtn.click();
        Assert.assertEquals("Solutions", "Solutions");

        getDriver().findElement(By.xpath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/ul/li[2]/a")).click();
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        getDriver().findElement(By.linkText("Contact us")).click();
        scrollingPage.executeScript("window.scrollBy(0,500)", "");

        String actualTitle = getDriver().getTitle();
        String expectedTitle = "24/7 Global Support | Naverisk RMM & PSA Software";
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement acceptCookie = getDriver().findElement(By.id("cn-accept-cookie"));
        acceptCookie.click();
    }

    @Test
    public void malikTimur() {

        getDriver().get("https://demoqa.com");

        getDriver().findElement(By.xpath("//div[@class = 'home-banner']")).click();
        List<String> browserTabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(browserTabs.get(1));
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.toolsqa.com/selenium-training/");


        getDriver().findElement(By.xpath("//input[@class= 'navbar__search--input']")).sendKeys("selenium\n");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.toolsqa.com/search?keyword=selenium");


    }

    @Test
    public void testLoginZA() {
        getDriver().get("https://thehostbest.ru/my-custom-development/");

        WebElement username = getDriver().findElement(By.id("form-field-name"));
        WebElement mobile = getDriver().findElement(By.id("form-field-field_1"));
        WebElement email = getDriver().findElement(By.id("form-field-email"));
        WebElement money = getDriver().findElement(By.id("form-field-field_504ba40"));
        WebElement timeForCreate = getDriver().findElement(By.id("form-field-field_b8a2f4b"));
        WebElement login = getDriver().findElement(By.className("elementor-button"));

        username.sendKeys("1111");
        mobile.sendKeys("11111");
        email.sendKeys("abc@gmail.com");
        money.sendKeys("1111");
        timeForCreate.sendKeys("11111");
        login.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://thehostbest.ru/my-custom-development/");
    }

    @Test
    public void findWatchesZA() {
        getDriver().get("https://www.x-kom.pl/");

        getDriver().findElement(By.xpath("//input[contains(@class,'suk8z4-0')]")).sendKeys("Apple Watch \n");
        getDriver().findElement(By.xpath("//a/span[text() = 'Inteligentne zegarki']")).click();
        getDriver().findElement(By.xpath("//span[text() = 'Smartwatche']")).click();
        String count = getDriver().findElement(By.xpath("//ul[@class = 'sc-1fme39r-5 sc-1fme39r-6 kcwhtJ']//span[@class = 'sc-1fme39r-0 gAgTIZ']")).getText();
        String countWatches = count.substring(1,count.length()-1);

        List<WebElement> listOfPage1 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
        List<WebElement> listOfPage2 = new ArrayList<>();
        List<WebElement> listOfPage3 = new ArrayList<>();
        WebElement btn = getDriver().findElement(By.xpath("//div[@class = 'sc-1xy3kzh-1 LdmOz']"));
        String el = listOfPage1.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();

        if (btn.getText().equals("1")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")));
            getDriver().findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")).click();
            if (btn.getText().equals("2")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage2 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
                el = listOfPage2.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();
            }
        }
        if (btn.getText().equals("2")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")));
            getDriver().findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")).click();
            if (btn.getText().equals("3")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage3 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
            }
        }
        List<WebElement> listWatches = new ArrayList<>();
        listWatches.addAll(listOfPage1);
        listWatches.addAll(listOfPage2);
        listWatches.addAll(listOfPage3);
        Assert.assertEquals(listWatches.size(), Integer.parseInt(countWatches));
    }

    @Test
    public void borisSevastyanov() {
        getDriver().get("http://nkmz.com/");

        getDriver().findElement(By.xpath("//*[@id=\"side-list\"]/a[3]/li")).click();

        WebElement title = getDriver().findElement(By.xpath("/html/body/div[2]/div[1]/div/h1"));

        Assert.assertEquals(title.getText(),"Работа с молодежью");
    }

    @Test
    public void testBorisSevastyanovBrewery() {
        getDriver().get("https://www.homebrewery.com.ua/");

        getDriver().findElement(By.xpath("//input[@name='keyword']")).sendKeys("Hoegaarden\n");

        WebElement product = getDriver().findElement(By.xpath("//a[@data-product = '487']"));

        Assert.assertEquals(product.getText(), "Набор для приготовления пшеничного пива Hoegaarden");
    }

    @Test
    public void IrynaKuEnterExistingEmailTest() {
        getDriver().get("http://automationpractice.com/");
        WebElement signInButton = getDriver().findElement(By.className("login"));
        signInButton.click();

        WebElement emailField = getDriver().findElement(By.id("email_create"));
        emailField.sendKeys("test@test.com");

        WebElement createAccountButton = getDriver().findElement(By.id("SubmitCreate"));
        createAccountButton.click();
        boolean errorMessage = getDriver().findElement(By.xpath("//li[contains(text(), 'An account using this email address has')]")).isDisplayed();
        Assert.assertTrue(errorMessage, "Asserting error message here");
    }

    @Test
    public void IrynaKuRegisterNewUserTest() {
        getDriver().get("http://automationpractice.com/");
        WebElement signInButton = getDriver().findElement(By.className("login"));
        signInButton.click();
        StringBuilder email;
        StringBuilder randomString = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < 8; i++) {
            randomString.append((char) (97 + rand.nextInt(25)));
        }

        email = randomString.append("@mail.com");

        WebElement emailField = getDriver().findElement(By.id("email_create"));
        emailField.sendKeys(email);

        WebElement createAccountButton = getDriver().findElement(By.id("SubmitCreate"));
        createAccountButton.click();

        WebElement gender = getDriver().findElement(By.id("id_gender1"));
        gender.click();

        WebElement firstName = getDriver().findElement(By.xpath("//input[@id='customer_firstname']"));
        firstName.sendKeys("FirstName");

        WebElement lastName = getDriver().findElement(By.id("customer_lastname"));
        lastName.sendKeys("LastName");

        WebElement password = getDriver().findElement(By.id("passwd"));
        password.sendKeys("newUserpassword");

        Select day = new Select(getDriver().findElement(By.id("days")));
        day.selectByValue(String.valueOf(rand.nextInt(31 - 1) + 1));

        Select month = new Select(getDriver().findElement(By.id("months")));
        month.selectByIndex(rand.nextInt(12 - 1) + 1);

        Select year = new Select(getDriver().findElement(By.id("years")));
        year.selectByIndex(rand.nextInt(121 - 1) + 1);

        WebElement newsletter = getDriver().findElement(By.id("newsletter"));
        newsletter.click();

        WebElement address = getDriver().findElement(By.id("address1"));
        address.sendKeys("Test street " /*+ rand.nextInt(100)*/);

        WebElement city = getDriver().findElement(By.id("city"));
        city.sendKeys("Test City");

        Select state = new Select(getDriver().findElement(By.id("id_state")));
        state.selectByIndex(rand.nextInt(54 - 1) + 1);

        WebElement postcode = getDriver().findElement(By.id("postcode"));
        postcode.sendKeys(String.valueOf(rand.nextInt(99999 - 10000) + 10000));

        Select country = new Select(getDriver().findElement(By.id("id_country")));
        country.selectByValue("21");

        WebElement mobile = getDriver().findElement(By.id("phone_mobile"));
        mobile.sendKeys("+126754378921");

        WebElement alias = getDriver().findElement(By.id("alias"));
        alias.sendKeys("My address alias");

        WebElement registerButton = getDriver().findElement(By.id("submitAccount"));
        registerButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://automationpractice.com/index.php?controller=my-account");

    }

    @Test
    public void IrynaKuAddToCartTest() {

        getDriver().get("http://automationpractice.com/");
        WebElement womenCategory = getDriver().findElement(By.xpath("//li/a[@title='Women']"));
        womenCategory.click();

        WebElement topsCategory = getDriver().findElement(By.xpath("//div/a[@title='Tops']"));
        topsCategory.click();

        WebElement productCard = getDriver().findElement(By.xpath("//div[@class='left-block']/div/a[@title='Faded Short Sleeve T-shirts']"));
        Actions action = new Actions(getDriver());
        action.moveToElement(productCard);
        action.perform();

        WebElement moreButton = getDriver().findElement(By.xpath("//span[text()='More']"));
        moreButton.click();

        Select size = new Select(getDriver().findElement(By.id("group_1")));
        size.selectByValue("2");

        WebElement color = getDriver().findElement(By.id("color_14"));
        color.click();

        WebElement addToCart = getDriver().findElement(By.id("add_to_cart"));
        addToCart.click();

        WebElement message = getDriver().findElement(By.xpath("//i[@class='icon-ok']/.."));

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(message));

        String actualResult = message.getText();
        String expectedResult = "Product successfully added to your shopping cart";
        Assert.assertEquals(actualResult, expectedResult);

    }
}
