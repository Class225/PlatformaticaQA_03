import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupMichaelTest extends BaseTest_ {

    @Test
    public void testIgorKomarovVerifyAlert_1() {
        getDriver().get("https://demoqa.com/alerts");
        getDriver().findElement(By.xpath("//button[@id='alertButton']")).click();
        Assert.assertEquals(getDriver().switchTo().alert().getText(), "You clicked a button");
    }



    @Test

    public void testOlAn1 () {
        getDriver().get("http://automationpractice.com/index.php");
        WebElement singIn = getDriver().findElement(By.xpath("//div[@class='header_user_info']"));
        singIn.click();
        String title = "Login - My Store";
        Assert.assertEquals(getDriver().getTitle(), title);
        WebElement email = getDriver().findElement(By.id("email"));
        WebElement singIn1 = getDriver().findElement(By.id("SubmitLogin"));
        email.sendKeys("123");
        singIn1.click();
        WebElement error1 = getDriver().findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err1 = "Invalid email address.";
        Assert.assertEquals(error1.getText(), err1);
        WebElement email2 = getDriver().findElement(By.id("email"));
        WebElement singIn2 = getDriver().findElement(By.id("SubmitLogin"));
        email2.clear();
        email2.sendKeys("123@gmail.com");
        singIn2.click();
        WebElement error2 = getDriver().findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err2 = "Password is required.";
        Assert.assertEquals(error2.getText(), err2);
        WebElement pass = getDriver().findElement(By.id("passwd"));
        WebElement singIn3 = getDriver().findElement(By.id("SubmitLogin"));
        pass.sendKeys("12345");
        singIn3.click();
        WebElement error3 = getDriver().findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err3 = "Authentication failed.";
        Assert.assertEquals(error3.getText(), err3);
    }

    @Test

    public void testOlAnPage2 () throws InterruptedException {
        getDriver().get("https://demoqa.com/automation-practice-form");
        WebElement firstname = getDriver().findElement(By.id("firstName"));
        firstname.sendKeys("123");
        WebElement lastname = getDriver().findElement(By.id("lastName"));
        lastname.sendKeys("5678");
        WebElement genderF = getDriver().findElement(By.xpath("//input[@name='gender'][@value='Female']"));
        getDriver().findElement(By.xpath("//label[contains(text(),'Female')]")).click();
    }

    private final String URL = "https://www.flowerchimp.co.id/";

    @Test
    public void testCountTextBox(){
        getDriver().get(URL);
        final String text = "box";

        WebElement Cakes = getDriver().findElement(By.xpath("//a[@href='/collections/trinity-collection-flower-shop-flower-chimp'][@class='hidden-product-link']"));
        Cakes.click();
        List<WebElement> itemList = getDriver().findElements(By.xpath("//span[text()='Trinity Box Deluxe Collection - Dainty Dreams']"));

        for (int i=0; i< itemList.size(); i++){
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
        }
    }

    @Test
    public void testOlgAnHW16() throws InterruptedException {
        getDriver().get("https://github.com/SergeiDemyanenko/PlatformaticaQA_03");

        WebElement code = getDriver().findElement(By.xpath("//span[@data-content='Code']"));
        code.click();
        Assert.assertEquals(getDriver().getTitle(), "GitHub - SergeiDemyanenko/PlatformaticaQA_03");
        Thread.sleep(1000);
        WebElement issues = getDriver().findElement(By.xpath("//a[@id='issues-tab']"));
        issues.click();
        Thread.sleep(1000);
        Assert.assertEquals(getDriver().getTitle(), "Issues · SergeiDemyanenko/PlatformaticaQA_03 · GitHub");
        WebElement pull_requests = getDriver().findElement(By.xpath("//span[@data-content='Pull requests']"));
        pull_requests.click();
        Thread.sleep(1000);
        Assert.assertEquals(getDriver().getTitle(), "Pull requests · SergeiDemyanenko/PlatformaticaQA_03 · GitHub");

        WebElement watch = getDriver().findElement(By.xpath("//a[@class='tooltipped tooltipped-s btn-sm btn']"));
//        watch.click();
//        WebElement name = driverChr.findElement(By.xpath("//input[@id='login_field']"));
//        name.sendKeys("zh@gmail.com");
//        WebElement pass = driverChr.findElement(By.id("password"));
//        pass.sendKeys("xxxxx");
//        WebElement submit = driverChr.findElement(By.xpath("//input[@value='Sign in']"));
//        submit.click();
        WebElement star = getDriver().findElement(By.xpath("//a[@data-hydro-click-hmac='7dd326fb77fbcc304fda4d7499fa4077b06a468f38d0608c4062d8b0568a6db3']"));
//        star.click();
//
        WebElement fork = getDriver().findElement(By.xpath("//a[@data-hydro-click-hmac='1c5d2096e9f9e799909d1ef6009f95a6e4688b06254400cd094bf5fb530fe9b4']"));
//        fork.click();
    }
}

