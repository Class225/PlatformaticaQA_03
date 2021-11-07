import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReferenceValuesTest extends BaseTest {
    private static final String LABEL = "Label";
    private static final String FILTER1 = "FILTER1";
    private static final String FILTER2 = "FILTER2";

    @Test
    void testCreateRecord() {
        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), 'Reference values' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id("label")).sendKeys(LABEL);
        getDriver().findElement(By.id("filter_1")).sendKeys(FILTER1);
        getDriver().findElement(By.id("filter_2")).sendKeys(FILTER2);
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        Assert.assertEquals(LABEL,getDriver().findElement(By.xpath("//tr[@data-index = '0']//a[contains(text(),'Label')]")).getText());
        Assert.assertEquals(FILTER1,getDriver().findElement(By.xpath("//tr[@data-index = '0']//a[contains(text(),'FILTER1')]")).getText());
        Assert.assertEquals(FILTER2,getDriver().findElement(By.xpath("//tr[@data-index = '0']//a[contains(text(),'FILTER2')]")).getText());
    }
}
