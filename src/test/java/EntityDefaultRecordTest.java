import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.Arrays;
import java.util.List;

public class EntityDefaultRecordTest extends BaseTest {

    private static final By ENTITIES_MENU_LOCATOR = By.className("nav-link");
    private static final By DEFAULT_MENU_LOCATOR = By.xpath("//p[contains(text(),'Default')]");
    private static final By ADD_NEW_FORM_BUTTON_LOCATOR = By.xpath("//i[contains(text(), 'create_new_folder')]");
    private static final By STRING_FIELD_LOCATOR = By.id("string");
    private static final String STRING_INPUT = "Value of String Input";
    private static final By TEXT_FIELD_LOCATOR = By.id("text");
    private static final String TEXT_INPUT = "Value of Text Input";
    private static final By INT_FIELD_LOCATOR = By.id("int");
    private static final String INT_INPUT = "123";
    private static final By DECIMAL_FIELD_LOCATOR = By.id("decimal");
    private static final String DECIMAL_INPUT = "987.14";
    private static final By DATE_FIELD_LOCATOR = By.id("date");
    private static final String DATE_INPUT = "07/11/2021";
    private static final By DATETIME_FIELD_LOCATOR = By.id("datetime");
    private static final String DATETIME_INPUT = "06/11/2021 17:22:46";
    private static final By USER_FIELD_LOCATOR = By.className("filter-option-inner-inner");
    private static final By USER_LOCATOR = By.xpath("//span[contains(text(),'apptester2@tester.test')]");
    private static final By ADD_BUTTON_LOCATOR = By.xpath("//button[contains(text(), '+')]");
    private static final By STRING_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-string");
    private static final String STRING_EMBED_INPUT = "String Input Value";
    private static final By TEXT_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-text");
    private static final String TEXT_EMBED_INPUT = "Text Input Value";
    private static final By INT_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-int");
    private static final String INT_EMBED_INPUT = "88";
    private static final By DECIMAL_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-decimal");
    private static final String DECIMAL_EMBED_INPUT = "41.17";
    private static final By DATE_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-date");
    private static final String DATE_EMBED_INPUT = "02/02/2021";
    private static final By DATETIME_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-datetime");
    private static final String DATETIME_EMBED_INPUT = "03/03/2021 18:05:10";
    private static final By SAVE_BUTTON_LOCATOR = By.id("pa-entity-form-save-btn");

    @Test
    public void testCreateNewRecordWithNewValues() throws InterruptedException {

        Actions act = new Actions(getDriver());
        act.moveToElement(getDriver().findElement(ENTITIES_MENU_LOCATOR)).perform();

        TestUtils.scrollClick(getDriver(), DEFAULT_MENU_LOCATOR);

        getDriver().findElement(ADD_NEW_FORM_BUTTON_LOCATOR).click();

        getDriver().findElement(STRING_FIELD_LOCATOR).clear();
        getDriver().findElement(STRING_FIELD_LOCATOR).sendKeys(STRING_INPUT);

        getDriver().findElement(TEXT_FIELD_LOCATOR).clear();
        getDriver().findElement(TEXT_FIELD_LOCATOR).sendKeys(TEXT_INPUT);

        getDriver().findElement(INT_FIELD_LOCATOR).clear();
        getDriver().findElement(INT_FIELD_LOCATOR).sendKeys(INT_INPUT);

        getDriver().findElement(DECIMAL_FIELD_LOCATOR).clear();
        getDriver().findElement(DECIMAL_FIELD_LOCATOR).sendKeys(DECIMAL_INPUT);

        getDriver().findElement(DATE_FIELD_LOCATOR).clear();
        getDriver().findElement(DATE_FIELD_LOCATOR).sendKeys(DATE_INPUT);

        getDriver().findElement(DATETIME_FIELD_LOCATOR).clear();
        getDriver().findElement(DATETIME_FIELD_LOCATOR).sendKeys(DATETIME_INPUT);

        TestUtils.scrollClick(getDriver(), ADD_BUTTON_LOCATOR);

        getDriver().findElement(STRING_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(STRING_EMBED_FIELD_LOCATOR).sendKeys(STRING_EMBED_INPUT);

        getDriver().findElement(TEXT_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(TEXT_EMBED_FIELD_LOCATOR).sendKeys(TEXT_EMBED_INPUT);

        getDriver().findElement(INT_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(INT_EMBED_FIELD_LOCATOR).sendKeys(INT_EMBED_INPUT);

        getDriver().findElement(DECIMAL_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(DECIMAL_EMBED_FIELD_LOCATOR).sendKeys(DECIMAL_EMBED_INPUT);

//        WebElement wb = getDriver().findElement(DATE_EMBED_FIELD_LOCATOR);
//        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
//        jse.executeScript("arguments[0].value='" + DATE_EMBED_INPUT + "';", wb);

        getDriver().findElement(DATE_EMBED_FIELD_LOCATOR).click();
        getDriver().findElement(DATE_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(DATE_EMBED_FIELD_LOCATOR).sendKeys(DATE_EMBED_INPUT);

        getDriver().findElement(DATETIME_EMBED_FIELD_LOCATOR).click();
        getDriver().findElement(DATETIME_EMBED_FIELD_LOCATOR).clear();
        getDriver().findElement(DATETIME_EMBED_FIELD_LOCATOR).sendKeys(DATETIME_EMBED_INPUT);

        getDriver().findElement(SAVE_BUTTON_LOCATOR).click();

        //Assert.assertTrue(getDriver().findElement(By.xpath("//a[contains(text(), '" + STRING_INPUT + "')]")).isDisplayed());

/*        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][1]")).getText(), STRING_INPUT);

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][2]")).getText(), TEXT_INPUT);

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][3]")).getText(), INT_INPUT);

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][4]")).getText(), DECIMAL_INPUT);

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][5]")).getText(), DATE_INPUT);

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//td[@class= 'pa-list-table-th'][6]")).getText(), DATETIME_INPUT);*/

        List<String> inputList = Arrays.asList(STRING_INPUT, TEXT_INPUT, INT_INPUT, DECIMAL_INPUT, DATE_INPUT, DATETIME_INPUT);

        List<WebElement> inputListLocators = getDriver().findElements(By.xpath("//td[@class= 'pa-list-table-th']"));
        Assert.assertTrue(inputListLocators.size() != 0);
        for (int i = 0; i < inputList.size(); i++) {
            Assert.assertEquals(inputListLocators.get(i).getText(), inputList.get(i));
        }

        getDriver().findElement(By.xpath("//tbody")).click();

        List<WebElement> inputListDetails = getDriver().findElements(By.xpath(" //span[@class = 'pa-view-field']"));
        Assert.assertTrue(inputListDetails.size() != 0);
        for (int i = 0; i < inputList.size(); i++) {
            Assert.assertEquals(inputListDetails.get(i).getText(), inputList.get(i));
        }

        List<String> inputEmbedList = Arrays.asList("1", STRING_EMBED_INPUT, TEXT_EMBED_INPUT, INT_EMBED_INPUT,
                DECIMAL_EMBED_INPUT, DATE_EMBED_INPUT, DATETIME_EMBED_INPUT);

        List<WebElement> inputEmbedListLocators = getDriver().findElements(By.xpath(" //td"));
        Assert.assertTrue(inputListDetails.size() != 0);
        for (int i = 0; i < inputEmbedList.size(); i++) {
            Assert.assertEquals(inputEmbedListLocators.get(i).getText(), inputEmbedList.get(i));
        }
    }
}
