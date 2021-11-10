import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

public class EntityFieldsTest extends BaseTest {

    @Test
    public void testCreateRecord() {
        final String expectedValue = "title";

        getDriver().findElement(By.xpath("//li[@id = 'pa-menu-item-45']/a")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id("title")).sendKeys(expectedValue);
        TestUtils.scrollClick(getDriver(), By.id("pa-entity-form-save-btn"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String actualValue = getDriver().findElement(By.xpath("//tr[@data-index='0']/td[2]/a")).getText();
        Assert.assertEquals(actualValue, expectedValue);
    }
}
