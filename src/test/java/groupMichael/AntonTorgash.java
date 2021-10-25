package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AntonTorgash {
    private WebDriver driver;
    private final String URL_SCRATCH = "https://scratch.mit.edu/";

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
    public void buttonCreateTest () {
        WebElement buttonCreate = driver.findElement(By.xpath("//span[text()='Создавай']"));
        buttonCreate.click();

        Assert.assertEquals( driver.getCurrentUrl() , "https://scratch.mit.edu/projects/editor/?tutorial=getStarted");
    }
    @Test
    public void buttonExploreTest () {
        WebElement buttonExplore = driver.findElement(By.xpath("//span[text()='Исследуй']"));
        buttonExplore.click();

        WebElement categoryStudios = driver.findElement(By.xpath("//li[@class='active']/span[text()='Проекты']"));
        categoryStudios.click();

        driver.findElement(By.xpath("//span[text()='Все']")).click();
        driver.findElement(By.xpath("//span[text()='Мультипликации']")).click();
        driver.findElement(By.xpath("//span[text()='Искусство']")).click();
        driver.findElement(By.xpath("//span[text()='Игры']")).click();
        driver.findElement(By.xpath("//span[text()='Музыка']")).click();
        driver.findElement(By.xpath("//span[text()='Загрузить ещё']")).click();

        WebElement iconElement = driver.findElement(By.xpath("//a[@href='/about']/span[text()='О проекте']"));
        iconElement.click();
        WebElement donation = driver.findElement(By.xpath("//button[@class='button about-button']"));
        donation.click();

        WebElement firstName = driver.findElement(By.id("BillingNameFirst"));
        WebElement lastName = driver.findElement(By.id("BillingNameLast"));
        WebElement address = driver.findElement(By.id("BillingAddress"));
        WebElement city = driver.findElement(By.id("BillingCity"));

        firstName.sendKeys("anton");
        lastName.sendKeys("ANTON");
        address.sendKeys("address");
        city.sendKeys("City");

        WebElement state = driver.findElement(By.id("BillingState"));
        state.click();
        driver.findElement(By.xpath("//select[@id='BillingState']/option[text()='Michigan']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://secure.donationpay.org/scratchfoundation/");









    }




}
