import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class EntityDefaultTest extends BaseTest {

    @Test
    public void testNewRecord(){
        String[] expectedArray = new String[]{
                "new String",
                "new Text",
                "25688",
                "100.55"};

        getDriver().findElement(By.xpath("//div[@class='logo d-flex']"));
        getDriver().findElement(By.xpath("//i[contains(text(),'list')]")).click();
        getDriver().findElement(By.xpath("//p[contains(text(),' Default ')]")).click();
        getDriver().findElement(By.xpath("//div[@class='card-icon']//i[@class='material-icons']")).click();

        WebElement stringTextField = getDriver().findElement(By.xpath("//div[@id='_field_container-string']//input[@id='string']"));
        WebElement textTextField = getDriver().findElement(By.xpath("//textarea[@id='text']"));
        WebElement intTextField = getDriver().findElement(By.xpath("//input[@id='int']"));
        WebElement decimalTextField = getDriver().findElement(By.xpath("//input[@id='decimal']"));
        WebElement dateField = getDriver().findElement(By.xpath("//input[@id='date']"));
        WebElement datetimeTextField = getDriver().findElement(By.xpath("//input[@id='datetime']"));

        stringTextField.clear();
        textTextField.clear();
        intTextField.clear();
        decimalTextField.clear();
        dateField.clear();
        datetimeTextField.clear();

        stringTextField.sendKeys("new String");
        textTextField.sendKeys("new Text");
        intTextField.sendKeys("25688");
        decimalTextField.sendKeys("100.55");
        dateField.click();
        datetimeTextField.click();

        WebElement plusButton = getDriver().findElement(By.xpath("//tr[@id='add-row-11']//button"));

        TestUtils.jsClick(getDriver(),plusButton);

        WebElement stringEmbedD = getDriver().findElement(By.xpath("//textarea[@id='t-11-r-1-string']"));
        WebElement testEmbedD = getDriver().findElement(By.xpath("//textarea[@id='t-11-r-1-text']"));
        WebElement intEmbedD = getDriver().findElement(By.xpath("//textarea[@id='t-11-r-1-int']"));
        WebElement decimalEmbedD = getDriver().findElement(By.xpath("//textarea[@id='t-11-r-1-decimal']"));

        stringEmbedD.clear();
        testEmbedD.clear();
        intEmbedD.clear();
        decimalEmbedD.clear();

        stringEmbedD.sendKeys("new String");
        testEmbedD.sendKeys("new Text");
        intEmbedD.sendKeys("25688");
        decimalEmbedD.sendKeys("10.55");

        getDriver().findElement(By.xpath("//input[@id='t-11-r-1-date']")).click();
        getDriver().findElement(By.xpath("//input[@id='t-11-r-1-datetime']")).click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));

        TestUtils.jsClick(getDriver(),saveButton);

        List<WebElement> result = getDriver().findElements(By.xpath("//td[@class='pa-list-table-th']"));
        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertEquals(result.get(i).getText(), expectedArray[i]);
        }
    }
}
