import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class EntityPlaceholderTest extends BaseTest {
    private final String fieldTitleId = "string";
    private final String fieldCommentsId = "text";
    private final String fieldIntId = "int";
    private final String fieldDecimalId = "decimal";
    private final String dateId = "date";
    private final String dateTimeId = "datetime";
    private final String fileId = "file";
    private final String fileImageId = "file_image";
    private final String buttonId = "pa-entity-form-save-btn";


    private final String fieldTitle = "TestString";
    private final String fieldComments = "TestText";
    private final String fieldInt = "27";
    private final String fieldDecimal = "27.00";

    private final String dateYear = "2017";
    private final String dateMonth = "Aug";
    private final String dateDay = "25";

    private final String dateTime = "12:20:20";
    private final String dateTimeMonth = "08";

    private final String filePath = "src/test/resources/";
    private final String fileName = "fileForTestTXT.txt";
    private final String fileImageName = "fileForTests.jpg";

    private final String userName = "apptester1@tester.test";

    private final String assertTitle = "//tr[@data-index = '0']/td[2]/a";
    private final String assertComment = "//tr[@data-index = '0']/td[3]/a";
    private final String assertInt = "//tr[@data-index = '0']/td[4]/a";
    private final String assertDecimal = "//tr[@data-index = '0']/td[5]/a";
    private final String assertDate = "//tr[@data-index = '0']/td[6]/a";
    private final String assertDateTime = "//tr[@data-index = '0']/td[7]/a";
    private final String assertFile = "//tr[@data-index = '0']/td[8]/a";
    private final String assertFileImg = "//tr[@data-index = '0']/td[9]/a";
    private final String assertUser = "//tr[@data-index = '0']/td[10]";


    @Test
    public void testCreateRecord() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(dateDay).append("/").append(dateTimeMonth).append("/").append(dateYear);
        String DateValue = stringBuilder.toString();

        stringBuilder.append(" ").append(dateTime);
        String DateTimeValue = stringBuilder.toString();

        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), ' Placeholder ' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id(fieldTitleId)).sendKeys(fieldTitle);
        getDriver().findElement(By.id(fieldCommentsId)).sendKeys(fieldComments);
        getDriver().findElement(By.id(fieldIntId)).sendKeys(fieldInt);
        getDriver().findElement(By.id(fieldDecimalId)).sendKeys(fieldDecimal);

        getDriver().findElement(By.id(dateId)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//th[@title = 'Select Month']"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//th[@title = 'Select Year']"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-action = 'selectYear'][contains(text()," + dateYear + ")]"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-action = 'selectMonth'][contains(text(),'" + dateMonth + "')]"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@data-action = 'selectDay']/div[contains(text(),'" + dateDay + "')]/.."))).click();

        WebElement dateTime = getDriver().findElement(By.id(dateTimeId));
        dateTime.click();
        dateTime.clear();
        dateTime.sendKeys(DateTimeValue);

        getDriver().findElement(By.id(fileId)).sendKeys(new File((filePath + fileName + "")).getAbsolutePath());
        getDriver().findElement(By.id(fileImageId)).sendKeys(new File((filePath + fileImageName + "")).getAbsolutePath());
        getDriver().findElement(By.id(buttonId)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(assertTitle)).getText(), fieldTitle);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertComment)).getText(), fieldComments);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertInt)).getText(), fieldInt);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertDecimal)).getText(), fieldDecimal);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertDate)).getText(), DateValue);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertDateTime)).getText(), DateTimeValue);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertFile)).getText(), fileName);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertFileImg)).getText(), fileImageName);
        Assert.assertEquals(getDriver().findElement(By.xpath(assertUser)).getText(), userName);
    }

}
