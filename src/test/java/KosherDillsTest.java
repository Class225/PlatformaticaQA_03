import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.List;

public class KosherDillsTest extends BaseTest {

    private final By ERROR = By.xpath("//div[@class='alert alert-info']/b");

    @Test
    public void testGoogleSearchRuAmerica() {

        getDriver().get("https://www.google.com");
        getDriver().findElement(By.xpath("//input[@class='gLFyf gsfi']"))
                .sendKeys("RuAmerica\n");

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//cite[text()='https://ruamerica.com']"))
                .getText(), "https://ruamerica.com");

    }

    @Test
    public void testDropDownSelectAmazonCharity() {

        Actions action = new Actions(getDriver());
        getDriver().get("https://www.amazon.com");

        WebElement select = getDriver().findElement(By.xpath("//*[@id='nav-link-accountList']"));
        action.moveToElement(select).perform();
        getDriver().findElement(By.xpath("//*[@id='nav-al-wishlist']/a[3]")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//*[@id='ge-stories']/div[1]/h2"))
                .getText(), "Over 1 million charities. See their stories.");
    }

    @Ignore
    @Test
    public void testDoubleDropDownSelectBidFax () {

        getDriver().get("https://bidfax.info");
        System.out.println(getDriver().getPageSource());

        getDriver().findElement(By.xpath("(//span[@class='drop-down'])[1]")).click();
        getDriver().findElement(By.xpath("//a[normalize-space()='Aston martin']")).click();
        getDriver().findElement(By.xpath("(//span[@class='drop-down'])[2]")).click();
        getDriver().findElement(By.xpath("//a[normalize-space()='Db9']")).click();

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
            List<WebElement> cars = getDriver().findElements(By.xpath("//div[@class='caption']/a/h2"));
            Assert.assertTrue(carsCheck(cars));
                getDriver().findElement(By.xpath("//a[normalize-space()='»']")).click();
                if (getDriver().findElements(ERROR).size() > 0) break;
        }
    }

    public boolean carsCheck (List<WebElement> var1){
        int trueCount = 0;
        for (int i = 0; i < var1.size(); i++) {
            if (var1.get(i).getText().toLowerCase().contains("db9"))
                trueCount++;
        }
        if (trueCount == var1.size()){return true;}
        return false;
    }

    @Test
    public void testBatterySelectRbc () {
        getDriver().get("https://rbc.sm.ua/");
        getDriver().findElement(By.xpath("//input[@id='input_search']")).sendKeys("apc 500\n");

        List<WebElement> upsList = getDriver().findElements(By.xpath("//div[@class='us-module-title']/a"));
        for (int i = 0; i < upsList.size(); i++) {

            Assert.assertTrue(upsList.get(i).getText().toLowerCase().contains("500"));
        }
    }
}