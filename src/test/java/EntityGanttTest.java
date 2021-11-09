import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;
import java.io.File;

public class EntityGanttTest extends BaseTest {

    @Test
    public void testEntityGantt() {
        final String expectedResult1 = "String";
        final String text = "Text Gantt";
        final String i = "2";
        final String decimal = "2.2";
        final String data = "02/07/2021";
        final String dataTime = "02/07/2021 02.27.54";
        final String subData = "7/2/2021";
        WebDriver driver;
        driver = getDriver();

        By by = new By.ByXPath("//a[@href = 'index.php?action=action_list&entity_id=35&mod=2']");
        WebElement blackField = getDriver().findElement(By.xpath("//div[@data-color = 'rose']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(blackField).build().perform();
        TestUtils.scrollClick(driver, by);

        getDriver().findElement(By.className("card-icon")).click();
        getDriver().findElement(By.id("string")).sendKeys(expectedResult1);
        getDriver().findElement(By.id("text")).sendKeys(text);
        getDriver().findElement(By.id("int")).sendKeys(i);
        getDriver().findElement(By.id("decimal")).sendKeys(decimal);

        WebElement dat = getDriver().findElement(By.id("date"));
        dat.sendKeys(Keys.DELETE);
        dat.sendKeys(data);
        WebElement datTime = getDriver().findElement(By.id("datetime"));
        datTime.sendKeys(Keys.DELETE);
        datTime.sendKeys(dataTime);

        WebElement file = getDriver().findElement(By.id("file"));
        TestUtils.scroll(driver, file);

        file.sendKeys(new File(("src/test/java/resoursesYK/TEXT.txt")).getAbsolutePath());

        getDriver().findElement(By.id("_field_container-user")).click();
        getDriver().findElement(By.xpath("//li[@class = 'selected active']")).click();

        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        WebElement blueElement = getDriver().findElement(By.xpath
                ("//div[@aria-label = 'Name String Start Date 7/2/2021 End Date 7/3/2021 Duration 2 days']"));

        String blue = blueElement.getAttribute("aria-label");

        Assert.assertEquals(getDriver().findElement
                (By.xpath("//td[@aria-label = 'String column header Task']")).getText(), expectedResult1);
        Assert.assertTrue(blue.contains(subData));
    }
}
