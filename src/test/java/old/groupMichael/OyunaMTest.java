package old.groupMichael;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Set;

@Ignore
public class OyunaMTest extends BaseTest {

    String URL = "https://biologie.ru/";
    String expectedResult = "https://www.instagram.com/alehina_ekaterina/";

    @Test
    public void testOyunaM_openInstagramTest() {

        getDriver().get(URL);

        WebElement element = getDriver().findElement(By.xpath("//div[@data-elem-id='1474624869390']"));
        element.click();

        Set<String> newWindowsSet = getDriver().getWindowHandles();
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }
}


