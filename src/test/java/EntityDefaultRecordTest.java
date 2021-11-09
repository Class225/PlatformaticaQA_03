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
    private static final By ADD_EMBED_RECORD_BUTTON_LOCATOR = By.xpath("//button[contains(text(), '+')]");
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

    private void setElementValue(By by, String input) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(input);
    }

    private void inputDataToMultipleFields(List<By> locatorsList, List<String> inputDataList) {
        for (int i = 0; i < inputDataList.size(); i++) {
            if (locatorsList.get(i).toString().contains("date")) {
                getDriver().findElement(locatorsList.get(i)).click();
            }
            setElementValue(locatorsList.get(i), inputDataList.get(i));
        }
    }

    private void compareDataDisplayedWithEntered(List<WebElement> webElementsList, List<String> inputDataList) {
        System.out.println("compare method starts");
        Assert.assertTrue(webElementsList.size() != 0);
        System.out.println("webElementList is more then 0");
        for (int i = 0; i < inputDataList.size(); i++) {
            Assert.assertEquals(webElementsList.get(i).getText(), inputDataList.get(i));
            System.out.println(webElementsList.get(i).getText() + inputDataList.get(i));
        }
    }

    @Test
    public void testCreateNewRecordWithNewValues() {
        Actions act = new Actions(getDriver());
        act.moveToElement(getDriver().findElement(ENTITIES_MENU_LOCATOR)).perform();
        TestUtils.scrollClick(getDriver(), DEFAULT_MENU_LOCATOR);

        getDriver().findElement(ADD_NEW_FORM_BUTTON_LOCATOR).click();

        inputDataToMultipleFields(FORM_LOCATORS_LIST, FORM_INPUT_LIST);

        TestUtils.scrollClick(getDriver(), ADD_EMBED_RECORD_BUTTON_LOCATOR);

        inputDataToMultipleFields(FORM_EMBED_LOCATORS_LIST, FORM_EMBED_INPUT_LIST);

        getDriver().findElement(SAVE_BUTTON_LOCATOR).click();

        List<WebElement> webElementPreviewFormList = getDriver().findElements(By.xpath("//td[@class= 'pa-list-table-th']"));
        compareDataDisplayedWithEntered(webElementPreviewFormList, FORM_INPUT_LIST);
        System.out.println("Page is displayed: " + getDriver().findElement(By.xpath("//span[contains(text(),'Showing 1 to 1 of 1 rows')]")).getText());

       // getDriver().findElement(By.xpath("//tbody")).click();

        getDriver().findElement(By.xpath("//td[@class= 'pa-list-table-th']")).click();

  //      System.out.println("Page is displayed: " + getDriver().findElement(By.xpath("//span[contains(text(),'Showing 1 to 1 of 1 rows')]")).getText());
        System.out.println("Page is displayed: " + getDriver().findElement(By.xpath("//h4")).getText());
        System.out.println("Page is displayed: " + getDriver().findElement(By.xpath("//h4[contains(text(),'EmbedD')]")).getText());

        List<WebElement> webElementFormList = getDriver().findElements(By.xpath(" //span[@class = 'pa-view-field']"));
        System.out.println("List of webElementFormList: " + webElementFormList);
        compareDataDisplayedWithEntered(webElementFormList, FORM_INPUT_LIST);

        List<WebElement> webElementFormEmbedList = getDriver().findElements(By.xpath(" //td"));
        webElementFormEmbedList.remove(0);
        compareDataDisplayedWithEntered(webElementFormEmbedList, FORM_EMBED_INPUT_LIST);
    }
}
