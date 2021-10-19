import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Test_QA_Group_Timur {
    private WebDriver driver;
    private static final String URL = "https://www.x-kom.pl/";

    public WebDriverWait getWait() {
        return new WebDriverWait(driver, 10);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void ZhmakaAndrey() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath
                ("//div[@id='block_top_menu']//a[@title='Women']")).click();
        driver.findElement(By.xpath
                ("//div[@class='product-image-container']/a[@title='Blouse']")).click();

        WebElement frame = driver.findElement(By.xpath
                ("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath
                ("//p[@id='add_to_cart']/button[@class='exclusive']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath
                ("//div[@class='button-container']/a[@title='Proceed to checkout']")).click();
        driver.findElement(By.xpath
                ("//div[@id='center_column']/p[contains(@class,cart_navigation)]/a[@title='Proceed to checkout']")).click();
        driver.findElement(By.xpath
                ("//form[@id='login_form']//input[@data-validate='isEmail']")).sendKeys("mail5432@22.com");
        driver.findElement(By.xpath
                ("//form[@id='login_form']//input[@data-validate='isPasswd']")).sendKeys("123456789");
        driver.findElement(By.xpath
                ("//form[@id='login_form']//button[@id='SubmitLogin']")).click();
        driver.findElement(By.xpath
                ("//div[@id='center_column']//button[@name='processAddress']")).click();
        WebElement checkboxTermsOfService = driver.findElement(By.id("cgv"));
        checkboxTermsOfService.click();
        if (checkboxTermsOfService.isSelected()) {
            driver.findElement(By.xpath
                    ("//form[@id='form']//button[@name='processCarrier']")).click();
        }
        driver.findElement(By.xpath
                ("//div[@id = 'HOOK_PAYMENT']//a[@class='bankwire']")).click();
        driver.findElement(By.xpath("//p[@id = 'cart_navigation']/button[@type = 'submit']")).click();
        WebElement orderComplete = driver.findElement(By.xpath("//p[@class = 'cheque-indent']/strong[@class = 'dark']"));

        Assert.assertEquals(orderComplete.getText(), "Your order on My Store is complete.");
    }

    @Test
    public void searcheTestKorelov() {
        driver.get("https://petrovich.ru/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("газобетон");

        WebElement find = driver.findElement(By.xpath("//button[text()=\"Найти\"]"));
        find.click();

//        WebElement listList = driver.findElement(By.xpath("//*[@data-test=\"view-list\"]"));
//        listList.click();

        WebElement blockList = driver.findElement(By.xpath("//*[@data-test=\"view-block\"]"));
        blockList.click();

        List<WebElement> itemList = driver.findElements(By.xpath("//*[@data-test=\"product-title\"]"));
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
        driver.get("https://petrovich.ru/");
        WebElement enterButton = driver.findElement(By.xpath("//*[@data-test=\"login-link\"]"));
        enterButton.click();
        WebElement fogPass = driver.findElement(By.xpath("//a[text()=\"Забыли пароль?\"]"));
        fogPass.click();
        WebElement title = driver.findElement(By.xpath("//*[@class=\"form__title\"]"));
        title.getText();
        System.out.println(title.getText());
        Assert.assertEquals(title.getText(), "Восстановление пароля:");

    }

    @Test
    public void testFelix_IX() throws InterruptedException {
        driver.get("https://www.spirit-of-metal.com/");

        driver.findElement(By.id("search-toggle")).click();
        String text = "skepticism";
        driver.findElement(By.id("GlobalSearchField")).sendKeys(text + "\n");
        driver.findElement(By.xpath("//*[@id=\"actuality\"]/div/div/section[1]/div/ul/a")).click();
        sleep(500);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1430);");

        driver.findElement(By.xpath("//*[@id=\"infinitescroll\"]/div[5]/a")).click();

        WebElement q = driver.findElement(By.xpath("//*[@id=\"profile\"]/div[2]/span[2]"));

        Assert.assertEquals(q.getText(), "Ordeal");
    }

    @Test
    public void testAlexeyLugovoy() {
        driver.get("https://www.anekdot.ru/");
        WebElement anekdoty = driver.findElement(By.xpath("//ul/li/a[text()='Анекдоты']"));
        Actions navesti = new Actions(driver);
        navesti.moveToElement(anekdoty).build().perform();
        WebElement bestMonth = driver.findElement(By.xpath("//ul/li/a[@title='Самые смешные анекдоты за месяц']"));
        bestMonth.click();
        WebElement first = driver.findElement(By.xpath("//div[@data-id='1251367']/div[@class='text']"));
        System.out.println(first.getText());
        WebElement h1 = driver.findElement(By.cssSelector("body h1"));
        Assert.assertEquals(h1.getText().toUpperCase(), "САМЫЕ СМЕШНЫЕ АНЕКДОТЫ ЗА МЕСЯЦ!");
    }

    @Test
    public void evgenyRogoznev() {
        driver.get("https://hh.ru/");
        WebElement signIn = driver.findElement(By.xpath("//*[@data-qa='login']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", signIn);
        driver.findElement(By.xpath("//*[@data-qa='account-signup-email']")).sendKeys("falseLogin");
        WebElement submitBtn = driver.findElement(By.xpath("//*[@data-qa='account-signup-submit']"));
        submitBtn.click();
        List<WebElement> errors = driver.findElements(By.xpath("//*[text()='Пожалуйста, укажите email или телефон']"));
        Assert.assertEquals(errors.size(), 1,
                "Сообщение с ошибкой \"Пожалуйста, укажите email или телефон\" отсутствует или их несколько.");

    }

    @Test
    public void Hlopuska() throws InterruptedException {

        driver.get("https://naverisk.com/");
        JavascriptExecutor scrollingPage = (JavascriptExecutor) driver;
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        Assert.assertEquals(3970, 3970);

        //Scrolling Up
        scrollingPage.executeScript("scroll(0,-2700);");
        Assert.assertEquals(-2700, -2700);

        //Find and click button in a top bar
        WebElement solutionsBtn = driver.findElement(new By.ByXPath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/a"));
        solutionsBtn.click();
        Assert.assertEquals("Solutions", "Solutions");

        //Move to the next page
        driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/ul/li[2]/a")).click();
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        driver.findElement(By.linkText("Contact us")).click();
        scrollingPage.executeScript("window.scrollBy(0,500)", "");

        //To get && compare title
        String actualTitle = driver.getTitle();
        String expectedTitle = "24/7 Global Support | Naverisk RMM & PSA Software";
        Assert.assertEquals(actualTitle, expectedTitle);

        //To accept cookies
        WebElement acceptCookie = driver.findElement(By.id("cn-accept-cookie"));
        acceptCookie.click();
    }


    @Test
    public void malikTimur() {

        driver.get("https://demoqa.com");

        driver.findElement(By.xpath("//div[@class = 'home-banner']")).click();
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.toolsqa.com/selenium-training/");


        driver.findElement(By.xpath("//input[@class= 'navbar__search--input']")).sendKeys("selenium\n");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.toolsqa.com/search?keyword=selenium");


    }

    @Test
    public void testLoginZA() {

        driver.get("https://thehostbest.ru/my-custom-development/");

        WebElement username = driver.findElement(By.id("form-field-name"));
        WebElement mobile = driver.findElement(By.id("form-field-field_1"));
        WebElement email = driver.findElement(By.id("form-field-email"));
        WebElement money = driver.findElement(By.id("form-field-field_504ba40"));
        WebElement timeForCreate = driver.findElement(By.id("form-field-field_b8a2f4b"));
        WebElement login = driver.findElement(By.className("elementor-button"));
        WebElement l = driver.findElement(By.xpath(""));

        username.sendKeys("1111");
        mobile.sendKeys("11111");
        email.sendKeys("abc@gmail.com");
        money.sendKeys("1111");
        timeForCreate.sendKeys("11111");
        login.click();


        Assert.assertEquals(driver.getCurrentUrl(), "https://thehostbest.ru/my-custom-development/");
    }

    @Test
    public void findWatchesZA() {
        driver.get(URL);
        driver.findElement(By.xpath("//input[contains(@class,'suk8z4-0')]")).sendKeys("Apple Watch \n");
        driver.findElement(By.xpath("//a/span[text() = 'Inteligentne zegarki']")).click();
        driver.findElement(By.xpath("//span[text() = 'Smartwatche']")).click();

        List<WebElement> listOfPage1 = (driver.findElements(
                By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
        List<WebElement> listOfPage2 = new ArrayList<>();
        List<WebElement> listOfPage3 = new ArrayList<>();
        WebElement btn = driver.findElement(By.xpath("//div[@class = 'sc-1xy3kzh-1 LdmOz']"));
        String el = listOfPage1.get(0).findElement(By.xpath
                ("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();

        if (btn.getText().equals("1")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")));
            driver.findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")).click();
            if (btn.getText().equals("2")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath
                        ("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage2 = (driver.findElements(
                        By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
                el = listOfPage2.get(0).findElement(By.xpath
                        ("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();
            }
        }
        if (btn.getText().equals("2")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")));
            driver.findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")).click();
            if (btn.getText().equals("3")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath
                        ("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage3 = (driver.findElements(
                        By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
            }
        }
        List<WebElement> listWatches = new ArrayList<>();
        listWatches.addAll(listOfPage1);
        listWatches.addAll(listOfPage2);
        listWatches.addAll(listOfPage3);
        System.out.println(listWatches.size());
    }
}
