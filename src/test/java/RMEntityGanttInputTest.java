import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.TestUtils;

@Ignore
public class RMEntityGanttInputTest extends BaseTest {

    @Test
    public void testValuesAreShown() {
        final String expectedValue = "String";

        TestUtils.scrollClick(getDriver(), By.xpath("//div[@id='menu-list-parent']/ul/li[12]/a/p"));
        getDriver().findElement(By.xpath("//div/i[@class='material-icons']")).click();

        getDriver().findElement(By.xpath("//input[@id='string']")).sendKeys(expectedValue);
        getDriver().findElement(By.xpath("//div/p/span/textarea[@id='text']")).sendKeys("Text");
        getDriver().findElement(By.xpath("//input[@id='int']")).sendKeys("1");
        getDriver().findElement(By.xpath("//input[@id='decimal']")).sendKeys("1.1");
        getDriver().findElement(By.xpath("//input[@id='date']")).click();
        getDriver().findElement(By.xpath("//input[@id='date']")).sendKeys(Keys.DELETE);
        getDriver().findElement(By.xpath("//input[@id='date']")).sendKeys("11/10/2019");
        getDriver().findElement(By.xpath("//input[@id='datetime']")).click();
        getDriver().findElement(By.xpath("//input[@id='datetime']")).sendKeys(Keys.DELETE);
        getDriver().findElement(By.xpath("//input[@id='datetime']")).sendKeys("11/11/2019 11:00:00");

        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        String actualValue = getDriver().findElement(By.xpath("//tbody/tr/td[@aria-label='String column header Task']")).getText();
        Assert.assertEquals(actualValue, expectedValue);

        boolean blueVisualBlock = getDriver().findElement(By.xpath("//div[@class='e-gantt-child-progressbar-inner-div e-gantt-child-progressbar ']")).isDisplayed();
        Assert.assertTrue(blueVisualBlock);

        String date = getDriver().findElement(By.xpath("//tbody/tr/td[@aria-colindex='2']")).getText();
        Assert.assertEquals(date, "11/10/2019");
    }
}
