import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntityChevronTest extends BaseTest {

    private static final By ENTITYCHEVRON = By.xpath("//p[text()=' Chevron ']");
    private static final By CREATENEWFOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By SAVEBUTTON = By.id("pa-entity-form-save-btn");

    @Test
    public void testCreateRecord(){
        getDriver().findElement(ENTITYCHEVRON).click();
        getDriver().findElement(CREATENEWFOLDER).click();
        getDriver().findElement(SAVEBUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tr[@data-index='0']")).isDisplayed());
    }

}
