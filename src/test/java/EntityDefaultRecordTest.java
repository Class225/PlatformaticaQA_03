import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class EntityDefaultRecordTest extends BaseTest {

    private static final By[] FORM_LOCATORS = {
            By.id("string"),
            By.id("text"),
            By.id("int"),
            By.id("decimal"),
            By.id("date"),
            By.id("datetime")};

    private static final String[] FORM_INPUT_VALUES = {
            "Value of String Input",
            "Value of Text Input",
            "123",
            "987.14",
            "07/11/2021",
            "06/11/2021 17:22:46"};

    private static final By[] EMBED_LOCATORS = {
            By.id("t-11-r-1-string"),
            By.id("t-11-r-1-text"),
            By.id("t-11-r-1-int"),
            By.id("t-11-r-1-decimal"),
            By.id("t-11-r-1-date"),
            By.id("t-11-r-1-datetime")};

    private static final String[] EMBED_INPUT_VALUES = {
            "String Input Value",
            "Text Input Value",
            "88",
            "41.17",
            "02/02/2021",
            "03/03/2021 18:05:10"};

    private void navigateToDefaultPage() {
        Actions act = new Actions(getDriver());
        act.moveToElement(getDriver().findElement(By.className("nav-link"))).perform();
        TestUtils.scrollClick(getDriver(), By.xpath("//p[contains(text(),'Default')]"));
    }

    private void setElementValue(By by, String input) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(input);
    }

    private void fillFieldsFor(By[] locators, String... inputValues) {
        for (int i = 0; i < inputValues.length; i++) {
            if (locators[i].toString().contains("date")) {
                getDriver().findElement(locators[i]).click();
            }
            setElementValue(locators[i], inputValues[i]);
        }
    }

    private void createNewRecord(String... inputValues) {
        getDriver().findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();
        fillFieldsFor(FORM_LOCATORS, inputValues);
    }

    private void createNewEmbedRecord(String... inputValues) {
        TestUtils.scrollClick(getDriver(), By.xpath("//button[contains(text(), '+')]"));
        fillFieldsFor(EMBED_LOCATORS, inputValues);
    }

    private void assertFieldEquals(List<WebElement> webElements, String... inputValues) {
        Assert.assertTrue(webElements.size() != 0);
        for (int i = 0; i < inputValues.length; i++) {
            Assert.assertEquals(webElements.get(i).getText(), inputValues[i]);
        }
    }

    public void assertPreviewFormData(String[] inputValues) {
        List<WebElement> webElementsPreviewRecord = getDriver().findElements(By.xpath("//td[@class= 'pa-list-table-th']"));
        assertFieldEquals(webElementsPreviewRecord, inputValues);
    }

    public void assertFormData(String[] inputValues) {
        List<WebElement> webElementsForm = getDriver().findElements(By.xpath(" //span[@class = 'pa-view-field']"));
        assertFieldEquals(webElementsForm, inputValues);
    }

    public void assertEmbedData(String[] inputValues) {
        List<WebElement> webElementsEmbed = getDriver().findElements(By.xpath(" //td"));
        webElementsEmbed.remove(0);
        assertFieldEquals(webElementsEmbed, inputValues);
    }

    @Test
    public void testCreateNewRecordWithNewValues() {
        navigateToDefaultPage();

        createNewRecord(FORM_INPUT_VALUES);
        createNewEmbedRecord(EMBED_INPUT_VALUES);
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        assertPreviewFormData(FORM_INPUT_VALUES);
        getDriver().findElement(By.xpath("//td[@class= 'pa-list-table-th']")).click();
        assertFormData(FORM_INPUT_VALUES);
        assertEmbedData(EMBED_INPUT_VALUES);
    }
}
