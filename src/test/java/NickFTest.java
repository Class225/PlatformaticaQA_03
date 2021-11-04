import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NickFTest extends BaseTest{

    private static final String MAIN_URL = "https://tehnoskarb.ua/";

    @Test
    public void testSearches() {
        getDriver().get(MAIN_URL);

        getDriver().findElement(By.xpath("//*[@class='base-text-field__input']")).sendKeys("apple");
                //className("base-text-field__input")).sendKeys("apple");
        getDriver().findElement(By.className("search-desktop__icon")).click();

        // ожидаем пока элементы станут кликабельные
        WebDriverWait myWait = new WebDriverWait(getDriver(), 5);
        myWait.until(ExpectedConditions.titleContains("apple"));
        myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-model-content d-f fl-d-c']/h2/a")));

        List<WebElement> itemList = getDriver().findElements(By.xpath("//div[@class='c-model-content d-f fl-d-c']"));

        for (int i = 0; i < itemList.size(); i++) {

            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("apple"));
        }
    }

}


