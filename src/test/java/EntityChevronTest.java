import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EntityChevronTest extends BaseTest {

    private static final By ENTITYCHEVRON = By.xpath("//p[text()=' Chevron ']");
    private static final By CREATENEWFOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By STRINGSTATUSMENU = By.xpath("//div[@class='filter-option-inner-inner'][text()='Pending']");
    private static final By SAVEBUTTON = By.id("pa-entity-form-save-btn");

    @Test
    public void testCreateRecord() {
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(SAVEBUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
    }

    @Test
    public void testCreateRecordPending() {
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(STRINGSTATUSMENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(By.xpath("//span[text()='Pending']")).click();
        getDriver().findElement(SAVEBUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Pending");
    }

}

