import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntityReferencevaluesInputTest extends BaseTest {
    final String expectedResult = "Label";

    private static final String ADD_CARD = "//div[@class='card-icon']";
    private static final String SAVE_CARD = "//i[@class='fa fa-check-square-o']";
    private static final String SAVE_DRAFT_CARD = "//i[@class='fa fa-pencil']";

    public void sendInputs(){
        getDriver().findElement(By.xpath("//input[@ name='entity_form_data[label]']")).sendKeys("Label");
        getDriver().findElement(By.id("filter_1")).sendKeys("Filter1");
        getDriver().findElement(By.id("filter_2")).sendKeys("Filter2");
    }
    public void clickAddCard(){
        getDriver().findElement(By.xpath(ADD_CARD)).click();
    }

    @Test
    public void testNewInput() {
        getDriver().findElement(By.xpath("//div[@id = 'menu-list-parent']//p[contains(text(), 'Reference values' )]")).click();
        clickAddCard();
        sendInputs();
        getDriver().findElement(By.id("pa-entity-form-save-btn")).click();
        String actualWithSaveBtn = getDriver().findElement(By.xpath("//tr[@data-index='0']/td[2]/a")).getText();

        Assert.assertEquals(actualWithSaveBtn, expectedResult);

        clickAddCard();
        sendInputs();
        getDriver().findElement(By.id("pa-entity-form-draft-btn")).click();
        String actualWithSaveDraftBtn = getDriver().findElement(By.xpath("//tr[@data-index='1']/td[2]/a")).getText();

        Assert.assertEquals(actualWithSaveDraftBtn, expectedResult);

        Assert.assertNotEquals(SAVE_CARD, SAVE_DRAFT_CARD);

        clickAddCard();
        sendInputs();
        getDriver().findElement(By.xpath("//button[@class='btn btn-dark']")).click();
        String actualWithCancelBtn = getDriver().findElement(By.xpath("//span[contains(text(),'Showing 1 to 2 of 2 rows')]")).getText();

        Assert.assertEquals(actualWithCancelBtn,"Showing 1 to 2 of 2 rows");
    }
}
