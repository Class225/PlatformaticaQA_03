import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GroupMielofonIsMineTest extends BaseTest {

    private final static String URL_VW = "https://www.vw.com/";

    @Test
    public void testVitalyZverevFirst() {
        getDriver().get(URL_VW);
        getDriver().findElement(By.xpath("//button[contains(@class, 'StyledTopBarButton')]")).click();
        getDriver().findElement(By.xpath("//a[@title='Models']")).click();
        WebElement modelName = getDriver().findElement(By.xpath("//a[@href='/en/models/passat.html']//h3"));
        getWait().until(ExpectedConditions.visibilityOf(modelName));
        List<WebElement> listElementsModels = getDriver().findElements(By.xpath("//h3[contains(@class, 'StyledTextComponent')]"));
        List<String> listModels = new ArrayList<>();

        for (WebElement element : listElementsModels) {
            listModels.add(element.getText());
        }

        Assert.assertTrue(listModels.contains("Passat"));
        Assert.assertEquals(listModels.size(), 15);
    }

    @Test
    public void testVitalyZverevSecond() {
        getDriver().get(URL_VW);
        getDriver().findElement(By.xpath("//a[@title='Learn More ']")).click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-labelledby='Input_zip_modal']")));

        WebElement zip = getDriver().findElement(By.xpath("//input[@label='Enter your ZIP code']"));
        zip.clear();
        zip.sendKeys("10019\n");

        WebElement firstCost = getDriver().findElement(By.xpath("//div[text()='Starting MSRP']/parent::div/following-sibling::div/div"));
        getWait().until(ExpectedConditions.visibilityOf(firstCost));

        List<WebElement> secondCost = getDriver().findElements(By.xpath("//sup[contains(@class, 'StyledTextComponent')]/parent::div"));
        scroll(getDriver(), secondCost.get(0));

        String first = firstCost.getText().replaceAll("[^0-9]", "").substring(0, 5);
        String second = secondCost.get(0).getText().replaceAll("[^0-9]", "").substring(0, 5);

        Assert.assertEquals(first, second);
    }

    @Ignore
    @Test
    public void testSearchAnastasiaKaz() {

        getDriver().get("https://rp5.by");

        WebElement search = getDriver().findElement(By.id("searchStr"));
        WebElement button = getDriver().findElement(By.id("searchButton"));

        search.sendKeys("лида");
        button.click();

        WebElement text = getDriver().findElement(By.id("leftNavi"));
        Assert.assertEquals(text.getText(), "Результат поиска");
    }

    @Test
    public void testLogotipAnastasiaKaz() {

        getDriver().get("https://rp5.by");

        WebElement search = getDriver().findElement(By.id("searchStr"));
        WebElement button = getDriver().findElement(By.id("searchButton"));

        search.sendKeys("лида");
        button.click();

        WebElement logotip = getDriver().findElement(By.id("logo"));
        Assert.assertTrue(logotip.isDisplayed());
    }

    @Ignore
    @Test
    public void testFAQAnastasiaKaz() {
        getDriver().get("https://rp5.by");

        WebElement question = getDriver().findElement(By.xpath("//div[@id='topMenuContent']/a[5]"));
        question.click();

        WebElement lastQuestion = getDriver().findElement(By.xpath("//ol/li[9]/b"));
        Assert.assertEquals(lastQuestion.getText(), "Что означает определение \"обложной\" во фразах \"обложной дождь\" или \"обложной снег\"?");
    }

    @Test
    public void testAlenaKuts1() {
        getDriver().get("https://stepik.org/catalog");

        String expectedTitle = getDriver().findElement(By.xpath("(//a[@class='course-list-card__link-wrapper'])[1]/div[@class='course-list-card__title']")).getText();
        WebElement catalog = getDriver().findElement(By.xpath("(//a[@class='course-list-card__link-wrapper'])[1]"));
        catalog.click();

        String actualTitle = getDriver().findElement(By.xpath("//div[contains (@class, 'catalog__category')]//h1[@class = 'catalog-block__title']")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testAlenaKuts2() {
        getDriver().get("https://stepik.org/catalog");

        String strExpectedNumber = getDriver().findElement(By.xpath("(//a[@class='course-list-card__link-wrapper'])[3]/div[@class='course-list-card__courses']")).getText();
        int expectedNumber = Integer.parseInt(strExpectedNumber.replaceAll("[^0-9]", ""));
        WebElement catalog = getDriver().findElement(By.xpath("(//a[@class='course-list-card__link-wrapper'])[3]"));
        catalog.click();

        List<WebElement> itemList = getDriver().findElements(By.xpath("//div[@data-list-type='default']//li[@class = 'course-cards__item']"));
        int actualNumber = itemList.size();
        Assert.assertEquals(actualNumber, expectedNumber);
    }

    @Test
    public void testJuliaVorobej() {
        getDriver().get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        WebElement search = getDriver().findElement(By.id("searchInput"));
        WebElement button = getDriver().findElement(By.id("searchButton"));
        search.sendKeys("миелофон");
        button.click();
        WebElement result = getDriver().findElement(By.id("firstHeading"));
        Assert.assertEquals(result.getText().toLowerCase(Locale.ROOT), "миелофон");
    }

    @Test
    public void testIlyaZozuliaFirst() {
        getDriver().get("https://www.stratege.ru");

        WebElement buttonGames = getDriver().findElement(By.id("menu-games"));
        buttonGames.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.stratege.ru/games");
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 10);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }
}
