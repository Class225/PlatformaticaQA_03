import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

public class EntityChevronTest extends BaseTest {

    private static final By ENTITIES_TAB_IN_MENU = By.xpath("//a[@href='#menu-list-parent']");
    private static final By ENTITYCHEVRON = By.xpath("//p[text()=' Chevron ']");
    private static final By CREATENEWFOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By SAVEBUTTON = By.id("pa-entity-form-save-btn");
    private static final By SAVE_DRAFT_BUTTON = By.id("pa-entity-form-draft-btn");
    private static final By STRING_DROPDOWN = By.id("string");

    public void moveMouseToEntityTabInMenu(){
        Actions moveMouse = new Actions(getDriver());
        moveMouse.moveToElement(getDriver().findElement(ENTITIES_TAB_IN_MENU)).perform();
    }

    public void selectByVisibleText(String visibleText) {
        Select select = new Select(getDriver().findElement(STRING_DROPDOWN));
        select.selectByVisibleText(visibleText);
    }


    @Test
    public void testCreateRecord(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(SAVEBUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
    }

    @Test
    public void testSaveDraftPending(){
        String testingData = "Pending";
        moveMouseToEntityTabInMenu();
        TestUtils.scrollClick(getDriver(), getDriver().findElement(ENTITYCHEVRON));
        getDriver().findElement(CREATENEWFOLDER).click();
        selectByVisibleText(testingData);
        getDriver().findElement(SAVE_DRAFT_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']//i[@class='fa fa-pencil']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//table//a[contains(text(), 'Pending')]")).getText(), testingData);
    }

    @Test
    public void testSaveDraftFulfillment(){
        String testingData = "Fulfillment";
        moveMouseToEntityTabInMenu();
        TestUtils.scrollClick(getDriver(), getDriver().findElement(ENTITYCHEVRON));
        getDriver().findElement(CREATENEWFOLDER).click();
        selectByVisibleText(testingData);
        getDriver().findElement(SAVE_DRAFT_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']//i[@class='fa fa-pencil']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//table//a[contains(text(), 'Fulfillment')]")).getText(), testingData);
    }

}
