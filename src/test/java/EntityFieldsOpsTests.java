import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntityFieldsOpsTests extends BaseTest {

    private static final String Multireference1 = "//*[@id='_field_container-multireference']/div[1]";
    private static final String Multireference2 = "//*[@id='_field_container-multireference']/div[2]";
    private static final String Multireference3 = "//*[@id='_field_container-multireference']/div[3]";

    private static final String saveBtn = "pa-entity-form-save-btn";

    @Test
    public void testFieldsOpsInput() {

        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), ' Reference values ' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.id("label")).sendKeys("John Doe");
        getDriver().findElement(By.id("filter_1")).sendKeys("French");
        getDriver().findElement(By.id("filter_2")).sendKeys("France");
        getDriver().findElement(By.id(saveBtn)).click();

        getDriver().findElement(By.className("card-icon")).click();
        getDriver().findElement(By.id("label")).sendKeys("Anna Belle");
        getDriver().findElement(By.id("filter_1")).sendKeys("Italian");
        getDriver().findElement(By.id("filter_2")).sendKeys("Italy");
        getDriver().findElement(By.id(saveBtn)).click();

        getDriver().findElement(By.className("card-icon")).click();
        getDriver().findElement(By.id("label")).sendKeys("Juan Carlos");
        getDriver().findElement(By.id("filter_1")).sendKeys("Spanish");
        getDriver().findElement(By.id("filter_2")).sendKeys("Spain");
        getDriver().findElement(By.id(saveBtn)).click();

        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), ' Fields Ops ' )]")).click();
        getDriver().findElement(By.className("card-icon")).click();

        getDriver().findElement(By.xpath("//div[@id='_field_container-switch']/div/div/label/span")).click(); // switch to On
        getDriver().findElement(By.xpath("//div[@id='_field_container-switch']/div/div")).click(); // switch to Off

        getDriver().findElement(By.xpath("//*[@id='dropdown']/option[2]")).click(); // done
        getDriver().findElement(By.xpath("//*[@id='dropdown']/option[1]")).click(); // pending

        getDriver().findElement(By.id("_field_container-reference")).click(); //reference
        getDriver().findElement(By.xpath("//*[@id='reference']/option[2]")).click();

        getDriver().findElement(By.xpath("//div[@id='_field_container-multireference']/div[1]/label")).click();
        getDriver().findElement(By.xpath("//div[@id='_field_container-multireference']/div[2]/label")).click();
        getDriver().findElement(By.xpath("//div[@id='_field_container-multireference']/div[3]/label")).click();

        getDriver().findElement(By.id("_field_container-reference_with_filter")).click();
        getDriver().findElement(By.xpath("//*[@id='reference_with_filter']/option[2]")).click();

        getDriver().findElement(By.xpath("//*[@id='add-row-15']/td/button")).click();

        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();

        String URL = getDriver().getCurrentUrl();
        Assert.assertEquals(URL, "https://ref2.ahome.work/index.php?action=action_list&entity_id=13&mod=2" );
    }
}


