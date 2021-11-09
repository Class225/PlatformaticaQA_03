import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EntityChevronTest extends BaseTest {

    private static final By ENTITYCHEVRON = By.xpath("//p[text()=' Chevron ']");
    private static final By CREATENEWFOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By STRINGSTATUSMENU = By.xpath("//div[@class='filter-option-inner-inner'][text()='Pending']");
    private static final By SAVEBUTTON = By.id("pa-entity-form-save-btn");
    private static final By SAVEDRATFBUTTON = By.id("pa-entity-form-draft-btn");
    private static final By STRINGSTATUSPENDING = By.xpath("//span[text()='Pending']");

    @Test
    public void testCreateRecord(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(SAVEBUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
    }

    @Test
    public void testCreateRecordPending(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(STRINGSTATUSMENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(STRINGSTATUSPENDING).click();
        getDriver().findElement(SAVEBUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Pending");
    }

    @Test
    public void testCreateRecordFulfillment(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(STRINGSTATUSMENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(By.xpath("//span[text()='Fulfillment']")).click();
        getDriver().findElement(SAVEBUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Fulfillment");
    }

    @Test
    public void testCreateRecordSent(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(STRINGSTATUSMENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(By.xpath("//span[text()='Sent']")).click();
        getDriver().findElement(SAVEBUTTON).click();
        getDriver().findElement(By.xpath("//a[text()='Sent']")).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Sent");
    }

    @Test
    public void testCreateRecordWithValidData() throws InterruptedException {
        final String InputText = "Test";
        final String InputInt = "1";
        final String InputDecimal = "2.00";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        SimpleDateFormat formatterTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date time = new Date();

        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(STRINGSTATUSMENU).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(STRINGSTATUSPENDING).click();
        getDriver().findElement(By.id("text")).sendKeys(InputText);
        Thread.sleep(1000);
        getDriver().findElement(By.id("int")).sendKeys(InputInt);
        getDriver().findElement(By.id("decimal")).sendKeys(InputDecimal);
        getDriver().findElement(By.id("date")).click();
        getDriver().findElement(By.id("datetime")).click();
        getDriver().findElement(By.xpath("//div[text() ='apptester1@tester.test']")).click();

        getDriver().findElement(SAVEBUTTON).click();

        List<WebElement> recordValues = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));

        Assert.assertEquals(recordValues.get(0).getText(), "Pending");
        Assert.assertEquals(recordValues.get(1).getText(), InputText);
        Assert.assertEquals(recordValues.get(2).getText(), InputInt);
        Assert.assertEquals(recordValues.get(3).getText(), InputDecimal);
        Assert.assertEquals(recordValues.get(4).getText(), formatter.format(date));
        Assert.assertEquals(recordValues.get(5).getText().substring(0,16), formatterTime.format(time));
        Assert.assertTrue(recordValues.get(6).getText().isEmpty());
        Assert.assertEquals(recordValues.get(7).getText(), "apptester1@tester.test");
    }

    @Test
    public void testCreateRecordSaveDraft(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(SAVEDRATFBUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//i[@class='fa fa-pencil']")).isDisplayed());
    }

}