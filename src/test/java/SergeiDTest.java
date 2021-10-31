import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SergeiDTest extends BaseTest {

    private static final String URL = "https://www.webstaurantstore.com/";

    @Test
    public void testSearches() {
        getDriver().get(URL);

        final String text = "table";
        getDriver().findElement(By.id("searchval")).sendKeys(text + "\n");

        List<WebElement> itemList = getDriver().findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        Assert.assertTrue(itemList.size() != 0);
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
        }
    }

}
