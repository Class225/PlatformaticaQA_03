import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

public class EntityReferenceValuesTest extends BaseTest {

    @Test
    public void testCreateNewRecord(){

        String expectedLabelValue = "Nike";
        String expectedFilter1Value = "Sport goods";
        String expectedFilter2Value = "Sport equipments";

        TestUtils.scrollClick(getDriver(), By.xpath("//p[contains(text(),'Reference values')]"));
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id("label")).sendKeys(expectedLabelValue);
        getDriver().findElement(By.id("filter_1")).sendKeys(expectedFilter1Value);
        getDriver().findElement(By.id("filter_2")).sendKeys(expectedFilter2Value);
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String actualLabelValue = getDriver().findElement(By.xpath("//tr[@data-index='0']/td[2]/a")).getText();
        Assert.assertEquals(actualLabelValue, expectedLabelValue);

        String actualFilter1Value = getDriver().findElement(By.xpath("//tr[@data-index='0']/td[3]/a")).getText();
        Assert.assertEquals(actualFilter1Value, expectedFilter1Value);

        String actualFilter2Value = getDriver().findElement(By.xpath("//tr[@data-index='0']/td[4]/a")).getText();
        Assert.assertEquals(actualFilter2Value, expectedFilter2Value);

    }
}
