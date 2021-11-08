package old;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class GroupMichaelTest extends BaseTest {

    @Test
    public void testIgorKomarovVerifyAlert_1() {
        getDriver().get("https://demoqa.com/alerts");
        getDriver().findElement(By.xpath("//button[@id='alertButton']")).click();
        Assert.assertEquals(getDriver().switchTo().alert().getText(), "You clicked a button");
    }

    @Ignore
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
        WebElement star = getDriver().findElement(By.xpath("//a[@data-hydro-click-hmac='7dd326fb77fbcc304fda4d7499fa4077b06a468f38d0608c4062d8b0568a6db3']"));
        WebElement fork = getDriver().findElement(By.xpath("//a[@data-hydro-click-hmac='1c5d2096e9f9e799909d1ef6009f95a6e4688b06254400cd094bf5fb530fe9b4']"));
    }

    @Test
    public void testOlgAnHW18() throws InterruptedException {
        getDriver().get("https://www.webstaurantstore.com/");
        getDriver().findElement(By.id("searchval")).sendKeys("stainless work table 30x60");
        getDriver().findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        List<WebElement> itemList = getDriver().findElements(By.xpath("//div[@id='details']/a[@data-testid='itemDescription']"));
        for (int i =0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("table"));
        }
        List<WebElement> pagesList = getDriver().findElements(By.xpath("//ul[@unselectable='unselectable']/li"));
        int n = pagesList.size()-3;
        pagesList.get(n).click();
        List<WebElement> nameList = new ArrayList<>();
        List<WebElement> cartList = new ArrayList<>();
        do {
            nameList = getDriver().findElements(By.xpath("//div[@class='add-to-cart']//input[@name='addToCartButton']/../../../../../div[@id='details']/a[@data-testid='itemDescription']"));
            cartList = getDriver().findElements(By.xpath("//div[@class='add-to-cart']//input[@name='addToCartButton']"));
            if (cartList.isEmpty()) {
                pagesList.get((0)).click();
                n--;
                Assert.assertTrue(n > 0);
            }
        } while (cartList.size()==0);
        cartList.get(cartList.size()-1).click();
        getDriver().findElement(By.xpath("//div[@class='notification__action']/a[contains(text(), 'View Cart')]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Empty Cart')]")).click();
    }
}


