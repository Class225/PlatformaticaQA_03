import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

public class KorenevskyyTest extends BaseTest {

    private final String baseUrl = "https://www.brandfarmsmn.com/";

    @Test
    public void farmAppleLinkTest() {
        String expected = "Apples!";
        getDriver().get(baseUrl);

        getDriver().findElement(By.xpath("//a[@target='_self']")).click();
        getDriver().findElement(By.xpath("//a[@href='apples.html']")).click();

        String actual = getDriver().findElement(By.xpath("//span[@class='emphasize']")).getText();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void farmEggsLinkTest() {

        getDriver().get(baseUrl);
        getDriver().findElement(By.xpath("//a[@href='eggs.html']")).click();
        WebElement eggsImage = getDriver().findElement(By.xpath("//img[@src='./images/eggsincartonweb.jpg']"));

        Assert.assertTrue(eggsImage.isDisplayed());
    }

    @Test
    public void DemoQAelementsTest() {

        getDriver().get("https://demoqa.com/");
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        getDriver().findElement(By.xpath("//h5[contains(text(), 'Elements')]\n"));

        WebElement headerImage = getDriver().findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']"));
        Assert.assertTrue(headerImage.isDisplayed());
    }
}
