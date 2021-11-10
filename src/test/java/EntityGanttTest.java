import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;
import java.io.File;

import static java.lang.Thread.sleep;

public class EntityGanttTest extends BaseTest {


    @Test
    public void testEntityGantt() throws InterruptedException {



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
        sleep (1000);
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class = 'selected active']"))).click();
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        WebElement blueElement = getDriver().findElement(By.xpath
                ("//div[@aria-label = 'Name String Start Date 7/2/2021 End Date 7/3/2021 Duration 2 days']"));

        String blue = blueElement.getAttribute("aria-label");

        Assert.assertEquals(getDriver().findElement
                (By.xpath("//td[@aria-label = 'String column header Task']")).getText(), expectedResult1);
        Assert.assertTrue(blue.contains(subData));
    }
    @Test
    public void testViewRecord() throws InterruptedException {
        final String STRING_FIELD = "First Gantt";
        final String TEXT = "Testing";
        final String INNITIAL_INT = "1";
        final String DECIMAL = "01";
        final String ID_BUTTON_SAVE = "pa-entity-form-save-btn";
        final String STRING_INPUT = "//input[@id='string']";
        final String TEXT_INPUT = "//textarea[@id='text']";
        final String INT_INPUT = "//input[@id='int']";
        final String DECIMAL_INPUT = "//input[@id='decimal']";
        final String DATA = "10/11/2021";
        final String DATA_TIME = "10/11/2021 16:40:48";
        final String LIST_BUTTON = "//a[@href='index.php?action=action_list&list_type=table&entity_id=35']";
        final String ACTIONS_BUTTON = "//i[text()='menu']";
        final String VIEW_MODE_BOTTON = "//a[text()='view']";

        final String expectedResult = "First Gantt";
        TestUtils.scrollClick(getDriver(), By.xpath("//p[text()= ' Gantt ']"));
        getDriver().findElement(By.className("card-icon")).click();
        getDriver().findElement(By.xpath(STRING_INPUT)).sendKeys(STRING_FIELD);
        getDriver().findElement(By.xpath(TEXT_INPUT)).sendKeys(TEXT);
        getDriver().findElement(By.xpath(INT_INPUT)).sendKeys(INNITIAL_INT);
        getDriver().findElement(By.xpath(DECIMAL_INPUT)).sendKeys(DECIMAL);
        WebElement dat = getDriver().findElement(By.id("date"));
        dat.sendKeys(Keys.DELETE);
        dat.sendKeys(DATA);
        WebElement datTime = getDriver().findElement(By.id("datetime"));
        datTime.sendKeys(Keys.DELETE);
        datTime.sendKeys(DATA_TIME);
        getDriver().findElement(By.id(ID_BUTTON_SAVE)).click();
        getDriver().findElement(By.xpath(LIST_BUTTON)).click();
        getDriver().findElement(By.xpath(ACTIONS_BUTTON)).click();
        getDriver().findElement(By.xpath(VIEW_MODE_BOTTON)).click();
        String actualResult = getDriver().findElement(By.xpath("//span[@class='pa-view-field']")).getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
