package old.groupMichael;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

@Ignore
public class HannaRTest extends BaseTest {
    private final String URL = "https://www.flowerchimp.co.id/";

    @Test
    public void testCountTextBox(){
        getDriver().get(URL);
        final String EXPECTED_TEXT = "box";

        WebElement Cakes = getDriver().findElement(By.xpath("//a[@href='/collections/trinity-collection-flower-shop-flower-chimp'][@class='hidden-product-link']"));
        Cakes.click();
        List<WebElement> itemList = getDriver().findElements(By.xpath("//span[text()='Trinity Box Deluxe Collection - Dainty Dreams']"));

        for (int i=0; i< itemList.size(); i++){
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(EXPECTED_TEXT));
        }
    }
}
