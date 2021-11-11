import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

public class VT_EntityChevronTest extends BaseTest {

    private static final By ENTITY_CHEVRON = By.xpath("//p[text()=' Chevron ']");
    private static final By MAIN_MENU = By.xpath("//a[@class='nav-link']");
    private static final By CREATE_NEW_FOLDER = By.xpath("//div[@class='card-icon']/i");
    private static final By TEXT_FIELD = By.xpath("//span[@class='bmd-form-group']/textarea");
    private static final By INT_FIELD = By.xpath("//span[@class='bmd-form-group']/input[@id='int']");
    private static final By DEC_FIELD = By.xpath("//div[@id='_field_container-decimal']//span/input");
    private static final By DATE_FIELD = By.xpath("//input[@id='date']");
    private static final By DATETIME_FIELD = By.xpath("//div[@id='_field_container-datetime']/input");
    private static final By SAVE_BUTTON = By.id("pa-entity-form-save-btn");

    @Test
    public void testCheckingTheDisplayInTheList(){
        final String expectedResult = "Sent";

        Actions action = new Actions(getDriver());
        WebElement menu = getDriver().findElement(MAIN_MENU);
        action.moveToElement(menu).moveToElement(getDriver().findElement(ENTITY_CHEVRON)).click().build().perform();

        getDriver().findElement(CREATE_NEW_FOLDER).click();
        getDriver().findElement(TEXT_FIELD).sendKeys("qwerty");
        getDriver().findElement(INT_FIELD).sendKeys("123");
        getDriver().findElement(DEC_FIELD).sendKeys("456");
        getDriver().findElement(DATE_FIELD).click();
        getDriver().findElement(DATETIME_FIELD).click();
        TestUtils.scrollClick(getDriver(),SAVE_BUTTON);

        WebElement sentButton = getDriver().findElement(By.xpath("//button[@class='btn btn-round btn-sm btn-success']"));
        sentButton.click();
        WebElement allButton = getDriver().findElement(By.xpath("//a[text()='All']"));
        allButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//tbody//td[2]/a"));
        Assert.assertEquals(actualResult.getText(),expectedResult);
    }
}
