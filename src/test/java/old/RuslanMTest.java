package old;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class RuslanMTest extends BaseTest {

    private static final String URL = "https://www.dmv.ca.gov/portal/";

    @Test
    public void testWeb() {

        getDriver().get(URL);

        WebElement closeButton = getDriver().findElement(By.id("dmv-modal__close"));
        closeButton.click();

        WebElement searchInput = getDriver().findElement(By.id("site-header-search-input"));
        WebElement submitButton = getDriver().findElement(By.className("site-header__search-submit"));
        searchInput.sendKeys("sqrtef");
        submitButton.click();

        WebElement noResult = getDriver().findElement(By.className("search-term"));

        Assert.assertEquals(noResult.getText(), "\"sqrtef\"");
    }

    @Test
    public void testWeb2() {

        getDriver().get(URL);

        String expectedResult = "SMOG INSPECTIONS";

        WebElement closeButton = getDriver().findElement(By.id("dmv-modal__close"));
        closeButton.click();

        WebElement vehicleReg = getDriver().findElement(By.id("1086"));
        vehicleReg.click();

        WebElement smogInsp = getDriver().findElement(By.xpath("//li[@id='menu-item-7339']"));
        smogInsp.click();

        WebElement inspectionsTitle = getDriver().findElement(By.xpath("//h1[@class='hero__title']"));
        String actualResult = inspectionsTitle.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testWeb3() {

        getDriver().get(URL);

        String expectedResult = "次へ";

        WebElement applyId = getDriver().findElement(By.xpath("//a[@id='dmv-modal1__apply-real-id']"));
        applyId.click();

        WebElement startApp = getDriver().findElement(By.xpath("//a[@class='wp-block-button__link']"));
        startApp.click();

        WebElement chooseLanguage = getDriver().findElement(By.xpath("//div[@id='ja']/../.."));
        chooseLanguage.click();

        WebElement japaneseLanguage = getDriver().findElement(By.xpath("//div/button[@class='arrow-button forward']/span[text()='" + expectedResult +"']"));
        String actualResult = japaneseLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testWeb4() {

        getDriver().get(URL);

        String expectedResult = "English";

        WebElement applyId = getDriver().findElement(By.xpath("//a[@id='dmv-modal1__apply-real-id']"));
        applyId.click();

        WebElement startApp = getDriver().findElement(By.xpath("//a[text()='Start application']"));
        startApp.click();

        WebElement chooseLanguage = getDriver().findElement(By.xpath("//div[@id='en']/../.."));
        chooseLanguage.click();

        WebElement englishLanguage = getDriver().findElement(By.xpath("//div[@class='row']/span[text()='" + expectedResult +"']"));
        String actualResult = englishLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
