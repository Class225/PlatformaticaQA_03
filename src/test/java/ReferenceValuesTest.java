import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReferenceValuesTest extends BaseTest {
    private static final String LABEL_VALUE = "Label";
    private static final String FILTER1_VALUE = "FILTER1";
    private static final String FILTER2_VALUE = "FILTER2";

    @Test
    void testCreateRecord() {
        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), 'Reference values' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id("label")).sendKeys(LABEL_VALUE);
        getDriver().findElement(By.id("filter_1")).sendKeys(FILTER1_VALUE);
        getDriver().findElement(By.id("filter_2")).sendKeys(FILTER2_VALUE);
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        Assert.assertEquals(LABEL_VALUE, getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[2]/a")).getText());
        Assert.assertEquals(FILTER1_VALUE, getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[3]/a")).getText());
        Assert.assertEquals(FILTER2_VALUE, getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[4]/a")).getText());
    }

    @Test
    void testEditRecord() {
        testCreateRecord();

        getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[last()]//button")).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@role = 'menu']//a[contains(text(), 'edit')]"))).click();

        WebElement label = getDriver().findElement(By.id("label"));
        label.clear();
        label.sendKeys(LABEL_VALUE + " 1");

        WebElement filter1 = getDriver().findElement(By.id("filter_1"));
        filter1.clear();
        filter1.sendKeys(FILTER1_VALUE + " 1");

        WebElement filter2 = getDriver().findElement(By.id("filter_2"));
        filter2.clear();
        filter2.sendKeys(FILTER2_VALUE + " 1");

        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        Assert.assertEquals(LABEL_VALUE + " 1", getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[2]/a")).getText());
        Assert.assertEquals(FILTER1_VALUE + " 1", getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[3]/a")).getText());
        Assert.assertEquals(FILTER2_VALUE + " 1", getDriver().findElement(By.xpath("//tr[@data-index = '0']/td[4]/a")).getText());
    }
}