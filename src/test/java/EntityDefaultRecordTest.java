import base.BaseTest;
import org.openqa.selenium.By;
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
    private static final By TEXT_FIELD_LOCATOR = By.id("text");
    private static final By INT_FIELD_LOCATOR = By.id("int");
    private static final By DECIMAL_FIELD_LOCATOR = By.id("decimal");
    private static final By DATE_FIELD_LOCATOR = By.id("date");
    private static final By DATETIME_FIELD_LOCATOR = By.id("datetime");
    private static final By ADD_BUTTON_LOCATOR = By.xpath("//button[contains(text(), '+')]");
    private static final By STRING_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-string");
    private static final By TEXT_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-text");
    private static final By INT_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-int");
    private static final By DECIMAL_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-decimal");
    private static final By DATE_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-date");
    private static final By DATETIME_EMBED_FIELD_LOCATOR = By.id("t-11-r-1-datetime");
    private static final By SAVE_BUTTON_LOCATOR = By.id("pa-entity-form-save-btn");

    private static final String STRING_INPUT = "Value of String Input";
    private static final String TEXT_INPUT = "Value of Text Input";
    private static final String INT_INPUT = "123";
    private static final String DECIMAL_INPUT = "987.14";
    private static final String DATE_INPUT = "07/11/2021";
    private static final String DATETIME_INPUT = "06/11/2021 17:22:46";
    private static final String STRING_EMBED_INPUT = "String Input Value";
    private static final String TEXT_EMBED_INPUT = "Text Input Value";
    private static final String INT_EMBED_INPUT = "88";
    private static final String DECIMAL_EMBED_INPUT = "41.17";
    private static final String DATE_EMBED_INPUT = "02/02/2021";
    private static final String DATETIME_EMBED_INPUT = "03/03/2021 18:05:10";

    private static final List<By> FORM_LOCATORS_LIST = Arrays.asList(
            STRING_FIELD_LOCATOR,
            TEXT_FIELD_LOCATOR,
            INT_FIELD_LOCATOR,
            DECIMAL_FIELD_LOCATOR,
            DATE_FIELD_LOCATOR,
            DATETIME_FIELD_LOCATOR);

    private static final List<String> FORM_INPUT_LIST = Arrays.asList(
            STRING_INPUT,
            TEXT_INPUT,
            INT_INPUT,
            DECIMAL_INPUT,
            DATE_INPUT,
            DATETIME_INPUT);

    private static final List<By> FORM_EMBED_LOCATORS_LIST = Arrays.asList(
            STRING_EMBED_FIELD_LOCATOR,
            TEXT_EMBED_FIELD_LOCATOR,
            INT_EMBED_FIELD_LOCATOR,
            DECIMAL_EMBED_FIELD_LOCATOR,
            DATE_EMBED_FIELD_LOCATOR,
            DATETIME_EMBED_FIELD_LOCATOR);

    private static final List<String> FORM_EMBED_INPUT_LIST = Arrays.asList(
            STRING_EMBED_INPUT,
            TEXT_EMBED_INPUT,
            INT_EMBED_INPUT,
            DECIMAL_EMBED_INPUT,
            DATE_EMBED_INPUT,
            DATETIME_EMBED_INPUT);

    public void setElementValue(By by, String input) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(input);
    }

    @Test
    public void testCreateNewRecordWithNewValues() {

        Actions act = new Actions(getDriver());
        act.moveToElement(getDriver().findElement(ENTITIES_MENU_LOCATOR)).perform();

        TestUtils.scrollClick(getDriver(), DEFAULT_MENU_LOCATOR);

        getDriver().findElement(ADD_NEW_FORM_BUTTON_LOCATOR).click();

        for (int i = 0; i < FORM_INPUT_LIST.size(); i++) {
            setElementValue(FORM_LOCATORS_LIST.get(i), FORM_INPUT_LIST.get(i));
        }

        TestUtils.scrollClick(getDriver(), ADD_BUTTON_LOCATOR);

        for (int i = 0; i < FORM_EMBED_INPUT_LIST.size(); i++) {
            if (FORM_EMBED_LOCATORS_LIST.get(i).toString().contains("date")) {
                getDriver().findElement(FORM_EMBED_LOCATORS_LIST.get(i)).click();
            }
            setElementValue(FORM_EMBED_LOCATORS_LIST.get(i), FORM_EMBED_INPUT_LIST.get(i));
        }

        getDriver().findElement(SAVE_BUTTON_LOCATOR).click();

        List<WebElement> inputListLocators = getDriver().findElements(By.xpath("//td[@class= 'pa-list-table-th']"));
        Assert.assertTrue(inputListLocators.size() != 0);
        for (int i = 0; i < FORM_INPUT_LIST.size(); i++) {
            Assert.assertEquals(inputListLocators.get(i).getText(), FORM_INPUT_LIST.get(i));
        }

        getDriver().findElement(By.xpath("//tbody")).click();

        List<WebElement> inputListDetails = getDriver().findElements(By.xpath(" //span[@class = 'pa-view-field']"));
        Assert.assertTrue(inputListDetails.size() != 0);
        for (int i = 0; i < FORM_INPUT_LIST.size(); i++) {
            Assert.assertEquals(inputListDetails.get(i).getText(), FORM_INPUT_LIST.get(i));
        }

        List<WebElement> inputEmbedListLocators = getDriver().findElements(By.xpath(" //td"));
        inputEmbedListLocators.remove(0);
        Assert.assertTrue(inputEmbedListLocators.size() != 0);
        for (int i = 0; i < FORM_EMBED_INPUT_LIST.size(); i++) {
            Assert.assertEquals(inputEmbedListLocators.get(i).getText(), FORM_EMBED_INPUT_LIST.get(i));
        }
    }
}
