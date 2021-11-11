import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class EntityDefaultRecordTest extends BaseTest {

    private static final By[] FORM_LOCATORS_LIST = {
            By.id("string"),
            By.id("text"),
            By.id("int"),
            By.id("decimal"),
            By.id("date"),
            By.id("datetime")};

    private static final String[] FORM_INPUT_LIST = {
            "Value of String Input",
            "Value of Text Input",
            "123",
            "987.14",
            "07/11/2021",
            "06/11/2021 17:22:46"};

    private static final By[] FORM_EMBED_LOCATORS_LIST = {
            By.id("t-11-r-1-string"),
            By.id("t-11-r-1-text"),
            By.id("t-11-r-1-int"),
            By.id("t-11-r-1-decimal"),
            By.id("t-11-r-1-date"),
            By.id("t-11-r-1-datetime")};

    private static final String[] FORM_EMBED_INPUT_LIST = {
            "String Input Value",
            "Text Input Value",
            "88",
            "41.17",
            "02/02/2021",
            "03/03/2021 18:05:10"};

    private void setElementValue(By by, String input) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(input);
    }

    private void inputDataToMultipleFields(By[] locatorsList, String... inputDataList) {
        for (int i = 0; i < inputDataList.length; i++) {
            if (locatorsList[i].toString().contains("date")) {
                getDriver().findElement(locatorsList[i]).click();
            }
            setElementValue(locatorsList[i], inputDataList[i]);
        }
    }

    private void assertCorrectDataDisplayed(List<WebElement> webElementsList, String... inputDataList) {
        Assert.assertTrue(webElementsList.size() != 0);
        for (int i = 0; i < inputDataList.length; i++) {
            Assert.assertEquals(webElementsList.get(i).getText(), inputDataList[i]);
        }
    }

    @Test
    public void testCreateNewRecordWithNewValues() {
        Actions act = new Actions(getDriver());
        act.moveToElement(getDriver().findElement(By.className("nav-link"))).perform();
        TestUtils.scrollClick(getDriver(), By.xpath("//p[contains(text(),'Default')]"));

        getDriver().findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();
        inputDataToMultipleFields(FORM_LOCATORS_LIST, FORM_INPUT_LIST);

        TestUtils.scrollClick(getDriver(), By.xpath("//button[contains(text(), '+')]"));
        inputDataToMultipleFields(FORM_EMBED_LOCATORS_LIST, FORM_EMBED_INPUT_LIST);

        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        List<WebElement> webElementPreviewFormList = getDriver().findElements(By.xpath("//td[@class= 'pa-list-table-th']"));
        assertCorrectDataDisplayed(webElementPreviewFormList, FORM_INPUT_LIST);

        getDriver().findElement(By.xpath("//td[@class= 'pa-list-table-th']")).click();

        List<WebElement> webElementFormList = getDriver().findElements(By.xpath(" //span[@class = 'pa-view-field']"));
        assertCorrectDataDisplayed(webElementFormList, FORM_INPUT_LIST);

        List<WebElement> webElementFormEmbedList = getDriver().findElements(By.xpath(" //td"));
        webElementFormEmbedList.remove(0);
        assertCorrectDataDisplayed(webElementFormEmbedList, FORM_EMBED_INPUT_LIST);
    }
}
