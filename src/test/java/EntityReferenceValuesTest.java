import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;
import java.util.List;

import static java.lang.Thread.sleep;

public class EntityReferenceValuesTest extends BaseTest {
    private static final String ID_LABEL = "label";
    private static final String ID_FILTER1 = "filter_1";
    private static final String ID_FILTER2 = "filter_2";
    private static final String ID_BUTTON_SAVE = "pa-entity-form-save-btn";
    private static final String ID_BUTTON_SAVE_DRAFT = "pa-entity-form-draft-btn";
    private static final String ID_ENTITY = "//p[contains(text(),'Reference values')]";
    private static final String ID_LIST_RECORDS = "//tr[@data-index]";

    private static final String LABEL_VALUE = "Label";
    private static final String FILTER1_VALUE = "FILTER1";
    private static final String FILTER2_VALUE = "FILTER2";

    private static final String ASSERT_LOCATOR_LABEL = "//tr[@data-index = '0']/td[2]/a";
    private static final String ASSERT_LOCATOR_FILTER1 = "//tr[@data-index = '0']/td[3]/a";
    private static final String ASSERT_LOCATOR_FILTER2 = "//tr[@data-index = '0']/td[4]/a";

    public void sendSpecificInputs(String label, String filter1, String filter2){
        getDriver().findElement(By.id(ID_LABEL)).sendKeys(label);
        getDriver().findElement(By.id(ID_FILTER1)).sendKeys(filter1);
        getDriver().findElement(By.id(ID_FILTER2)).sendKeys(filter2);
    }

    public void clickAddCard(){
        getDriver().findElement(By.xpath("//div[@class='card-icon']")).click();
    }

    @Test
    public void testCreateRecord() {
        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), 'Reference values' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id(ID_LABEL)).sendKeys(LABEL_VALUE);
        getDriver().findElement(By.id(ID_FILTER1)).sendKeys(FILTER1_VALUE);
        getDriver().findElement(By.id(ID_FILTER2)).sendKeys(FILTER2_VALUE);
        getDriver().findElement(By.id(ID_BUTTON_SAVE)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_LABEL)).getText(),LABEL_VALUE);
        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_FILTER1)).getText(),FILTER1_VALUE);
        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_FILTER2)).getText(),FILTER2_VALUE);
    }

    @Test
    public void testEditRecord() throws InterruptedException {
        testCreateRecord();

        getDriver().findElement(By.xpath("//div[@class = 'dropdown pull-left']//button")).click();
        sleep(1000);
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@role = 'menu']//a[contains(text(), 'edit')]"))).click();

        WebElement label = getDriver().findElement(By.id(ID_LABEL));
        label.clear();
        label.sendKeys(LABEL_VALUE + " 1");

        WebElement filter1 = getDriver().findElement(By.id(ID_FILTER1));
        filter1.clear();
        filter1.sendKeys(FILTER1_VALUE + " 1");

        WebElement filter2 = getDriver().findElement(By.id(ID_FILTER2));
        filter2.clear();
        filter2.sendKeys(FILTER2_VALUE + " 1");

        getDriver().findElement(By.id(ID_BUTTON_SAVE)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_LABEL)).getText(),LABEL_VALUE + " 1");
        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_FILTER1)).getText(),FILTER1_VALUE + " 1");
        Assert.assertEquals(getDriver().findElement(By.xpath(ASSERT_LOCATOR_FILTER2)).getText(),FILTER2_VALUE + " 1");
    }

    @Test
    public void testNewInputDraft() {
        final String textDraft = "Draft";
        TestUtils.scrollClick(getDriver(), getDriver().findElement(By.xpath(ID_ENTITY)));

        clickAddCard();
        sendSpecificInputs(LABEL_VALUE, FILTER1_VALUE, FILTER2_VALUE);
        getDriver().findElement(By.id(ID_BUTTON_SAVE)).click();

        clickAddCard();
        sendSpecificInputs(LABEL_VALUE + textDraft, FILTER1_VALUE, FILTER2_VALUE);
        getDriver().findElement(By.id(ID_BUTTON_SAVE_DRAFT)).click();

        List<WebElement> listOfRecords = getDriver().findElements(By.xpath(ID_LIST_RECORDS));
        for (int i = 0; i < listOfRecords.size(); i++) {
            if (listOfRecords.get(i).getText().contains(textDraft)) {
                Assert.assertTrue(getDriver().findElement(By.xpath(ID_LIST_RECORDS + "[" + ++i + "]/td/i[@class='fa fa-pencil']")) != null);
            }
        }
    }
}