import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class EntityReferenceValuesTwoTest extends BaseTest {
    @Test
    public void testCreateNewRecord() {
        final String expectedValue = "String";

        TestUtils.scrollClick(getDriver(), By.xpath("//p[contains(text(),'Reference values')]"));
        getDriver().findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();
        getDriver().findElement(By.id("label")).sendKeys(expectedValue);
        getDriver().findElement(By.xpath("//input[@id='filter_1']")).sendKeys(expectedValue);
        getDriver().findElement(By.xpath("//input[@id='filter_2']")).sendKeys(expectedValue);
        getDriver().findElement(By.xpath("//button[@id='pa-entity-form-save-btn']")).click();

        List<WebElement> list = getDriver().findElements(By.xpath("//*[@id='pa-all-entities-table']//td\n"));

        Assert.assertEquals(list.get(1).getText(), expectedValue);
        Assert.assertEquals(list.get(2).getText(), expectedValue);
        Assert.assertEquals(list.get(3).getText(), expectedValue);
        Assert.assertTrue(getDriver().findElement(By.xpath("//i[@class='fa fa-check-square-o']")).isDisplayed());
    }
}
