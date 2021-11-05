package old.groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Random;

@Ignore
public class AntonTorgash {
    private WebDriver driver;
    private final String URL_SCRATCH = "https://rs24.ru/home.htm";

    @BeforeMethod
    public void beforeSetting () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL_SCRATCH);
    }

    @AfterMethod
    public void afterSetting () {
        driver.quit();
    }

    @Test
    public void buttonDelivery  () {
        WebElement buttonDelivery = driver.findElement(By.xpath("//div[@class='row']//div[@class='col-xs-12 top-menu__inner']//a[@href='/delivery.htm']"));
        buttonDelivery.click();

        Assert.assertEquals( driver.getCurrentUrl(), "https://rs24.ru/delivery.htm");
    }

    @Test
    public void buttonPayment () {
        WebElement buttonPayment = driver.findElement(By.xpath("//div[@class='row']//div[@class='col-xs-12 top-menu__inner']//a[@href='/payments_info.htm']"));
        buttonPayment.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://rs24.ru/payments_info.htm");
    }

    @Test
    public void buttonContacts () {
        WebElement buttonContacts = driver.findElement(By.xpath("//div[@class='row']//div[@class='col-xs-12 top-menu__inner']//a[@href='/contacts.htm?city=8']"));
        buttonContacts.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://rs24.ru/contacts.htm?city=8");
    }

    @Test
    public void SignUp () throws InterruptedException {
        String expectedResult = "Телефон не подтвержден";

        WebElement buttonSignUp = driver.findElement(By.xpath("(//a[@rel='nofollow'][text()='Регистрация'])[1]"));
        buttonSignUp.click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@href='/customer_person_register']")).click();
        driver.findElement(By.xpath("//a[@class= 'select_sity'][text()='Красноярск']")).click();

        WebElement surName = driver.findElement(By.id("surname"));
        WebElement name = driver.findElement(By.id("name"));
        WebElement patronymic = driver.findElement(By.id("patronymic"));
        WebElement phone = driver.findElement(By.id("phone"));
        WebElement mail = driver.findElement(By.id("contact"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement passwordConfirm = driver.findElement(By.id("password-confirm"));

        Random random = new Random();
        int n = random.nextInt(1000) + 1;

        StringBuilder email= new StringBuilder();
        email.append("ivan").append(n).append("@mail.ru");
        System.out.println(email);

        StringBuilder userPassword = new StringBuilder();
        userPassword.append("Ivan").append(n).append("PASSWORD");
        System.out.println(userPassword);

        surName.sendKeys("Ivanov");
        name.sendKeys("Ivan");
        patronymic.sendKeys("Ivanovich");
        phone.sendKeys("8999999999");
        mail.sendKeys(email);
        password.sendKeys(userPassword);
        passwordConfirm.sendKeys(userPassword);

        driver.findElement(By.xpath("//*[text()='Разрешить отправку уведомлений']")).click();
        driver.findElement(By.xpath("//input[@class='input-other']")).sendKeys("Другое");
        driver.findElement(By.xpath("//*[text()='Частное лицо']")).click();
        driver.findElement(By.xpath("//a[@class='policy']")).click();
        driver.findElement(By.xpath("//div[@class='reg-individual__button reg-individual__item']/button")).click();
        WebElement error = driver.findElement(By.xpath("//span[@class='help-block form-error ']"));
        System.out.println(error.getText());

        Assert.assertEquals(error.getText(), expectedResult);
    }




}
