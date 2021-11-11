import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class EntityChildRecordsLoopTest extends BaseTest {

    public static final By START_BALANCE_FIELD = By.xpath("//input[@data-field_name = 'start_balance']");
    public static final By SAVE_BUTTON = By.id("pa-entity-form-save-btn");

    public void createChildRecordsLoopWithNoRecords(){
        WebElement childRecordsLoopMenuOption = getDriver().findElement(By.xpath("//li//p[.= ' Child records loop ']/.."));
        TestUtils.scrollClick(getDriver(), childRecordsLoopMenuOption);
        WebElement newChildRecordsLoopIcon = getDriver().findElement(By.className("card-icon"));
        newChildRecordsLoopIcon.click();
        getDriver().findElement(START_BALANCE_FIELD).sendKeys("50");
        getDriver().findElement(SAVE_BUTTON).click();
    }

    public void selectFromTheDropList(String option){
        WebElement actionsIcon = getDriver().findElement(By.xpath("//button//div[@class='ripple-container']/.."));
        actionsIcon.click();
        List<WebElement> actionsDropDown = getDriver().findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right show']/li/a"));
        for(WebElement element: actionsDropDown){
            if(element.getText().equals(option)){
                element.click();
                break;
            }
        }
    }

    @Test
    public void testEditChildRecordsLoopWithNoRecords(){
        createChildRecordsLoopWithNoRecords();

        selectFromTheDropList("edit");

        final String NEW_START_BALANCE_VALUE = "60.00";
        final String SUB_RECORD_AMOUNT_VALUE = "3.00";
        final String SUB_RECORD_ITEM_VALUE = "10000";
        final String newEndBalanceValue = "63.00";

        getDriver().findElement(START_BALANCE_FIELD).sendKeys(Keys.CONTROL + "a");
        getDriver().findElement(START_BALANCE_FIELD).sendKeys(NEW_START_BALANCE_VALUE);
        WebElement newSubRecordIcon = getDriver().findElement(By.xpath("//td[@class = 'pa-add-row-btn-col']/button"));
        newSubRecordIcon.click();
        WebElement subRecordAmountField = getDriver().findElement(By.xpath("//tbody/tr[last()-1]//textarea[@data-field_name = 'amount']"));
        WebElement subRecordItemField = getDriver().findElement(By.xpath("//tbody/tr[last()-1]//textarea[@data-field_name = 'item']"));
        subRecordAmountField.sendKeys(Keys.CONTROL + "a");
        subRecordAmountField.sendKeys(SUB_RECORD_AMOUNT_VALUE);
        subRecordItemField.sendKeys(SUB_RECORD_ITEM_VALUE);
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr/td[2][@class = 'pa-list-table-th']/a")).getText(), NEW_START_BALANCE_VALUE);

        selectFromTheDropList("view");
        WebElement startBalanceOnViewPage = getDriver().findElement(By.xpath("//div[2]/div/span[@class = 'pa-view-field']"));
        WebElement subRecordsAmountOnViewPage = getDriver().findElement(By.xpath("//tbody/tr[1]/td[2]"));
        WebElement subRecordsItemsOnViewPage = getDriver().findElement(By.xpath("//tbody/tr[1]/td[3]"));

        Assert.assertEquals(startBalanceOnViewPage.getText(), NEW_START_BALANCE_VALUE);
        Assert.assertEquals(subRecordsAmountOnViewPage.getText(), SUB_RECORD_AMOUNT_VALUE);
        Assert.assertEquals(subRecordsItemsOnViewPage.getText(), SUB_RECORD_ITEM_VALUE);
    }
}
