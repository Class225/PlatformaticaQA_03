import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.TestUtils;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityChevronTest extends BaseTest {

    private static final By SAVE_BUTTON = By.id("pa-entity-form-save-btn");
    private static final By SAVE_DRAFT_BUTTON = By.id("pa-entity-form-draft-btn");
    private static final By STRING_DROPDOWN = By.id("string");
    private static final By STRING_STATUS_MENU = By.xpath("//div[@class='filter-option-inner-inner'][text()='Pending']");
    private static final By STRING_STATUS_PENDING = By.xpath("//span[text()='Pending']");

    public void openNewChevronCreationWidget() {
        Actions moveMouse = new Actions(getDriver());
        moveMouse.moveToElement(getDriver().findElement(By.xpath("//a[@href='#menu-list-parent']"))).perform();
        TestUtils.scrollClick(getDriver(), getDriver().findElement(By.xpath("//p[text()=' Chevron ']")));
        getDriver().findElement(By.xpath("//i[text()='create_new_folder']")).click();
    }

    public void selectByVisibleText(String visibleText) {
        Select select = new Select(getDriver().findElement(STRING_DROPDOWN));
        select.selectByVisibleText(visibleText);
    }

    @Test
    public void testCreateRecord() {
        openNewChevronCreationWidget();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
    }

    @Test
    public void testSaveDraftPending() {
        String testingData = "Pending";
        openNewChevronCreationWidget();
        selectByVisibleText(testingData);
        getDriver().findElement(SAVE_DRAFT_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']//i[@class='fa fa-pencil']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//table//a[contains(text(), 'Pending')]")).getText(), testingData);
    }

    @Test
    public void testSaveDraftFulfillment() {
        String testingData = "Fulfillment";
        openNewChevronCreationWidget();
        selectByVisibleText(testingData);
        getDriver().findElement(SAVE_DRAFT_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']//i[@class='fa fa-pencil']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//table//a[contains(text(), 'Fulfillment')]")).getText(), testingData);
    }

    @Test
    public void testSaveDraftSent(){
        String testingData = "Sent";
        openNewChevronCreationWidget();
        selectByVisibleText(testingData);
        getDriver().findElement(SAVE_DRAFT_BUTTON).click();
        getDriver().findElement(By.xpath("//a[@href='index.php?action=action_list&list_type=table&entity_id=36&stage=Sent']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']//i[@class='fa fa-pencil']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//table//a[contains(text(), 'Sent')]")).getText(), testingData);
    }

    @Test
    public void testCreateRecordPending() {
        openNewChevronCreationWidget();
        getDriver().findElement(STRING_STATUS_MENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(By.xpath("//span[text()='Pending']")).click();
        getDriver().findElement(SAVE_BUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Pending");
    }

    @Ignore
    @Test
    public void testCreateRecordWithValidData() throws InterruptedException {
        final String InputText = "Test";
        final String InputInt = "1";
        final String InputDecimal = "2.00";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        SimpleDateFormat formatterTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date time = new Date();

        openNewChevronCreationWidget();
        getDriver().findElement(STRING_STATUS_MENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(STRING_STATUS_PENDING).click();
        getDriver().findElement(By.id("text")).sendKeys(InputText);
        Thread.sleep(1000);
        getDriver().findElement(By.id("int")).sendKeys(InputInt);
        getDriver().findElement(By.id("decimal")).sendKeys(InputDecimal);
        getDriver().findElement(By.id("date")).click();
        getDriver().findElement(By.id("datetime")).click();
        getDriver().findElement(By.xpath("//div[text() ='apptester1@tester.test']")).click();

        getDriver().findElement(SAVE_BUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Pending");
        Assert.assertEquals(recordValues.get(1).getText(), InputText);
        Assert.assertEquals(recordValues.get(2).getText(), InputInt);
        Assert.assertEquals(recordValues.get(3).getText(), InputDecimal);
        Assert.assertEquals(recordValues.get(4).getText(), formatter.format(date));
        Assert.assertEquals(recordValues.get(5).getText().substring(0, 16), formatterTime.format(time));
        Assert.assertTrue(recordValues.get(6).getText().isEmpty());
        Assert.assertEquals(recordValues.get(7).getText(), "apptester1@tester.test");
    }

}