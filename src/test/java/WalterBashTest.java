import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.UUID;
public class WalterBashTest extends BaseTest {



    @Test
    private void SignUpAutomationpractice() {

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        NavigateToCreateAccountPage();
        CreateAccount(wait);
        InputData(wait);
        Assert();
    }

    private void NavigateToCreateAccountPage() {
        getDriver().get("http://automationpractice.com/index.php");
        getDriver().findElement(By.xpath("//*[@title='Log in to your customer account']")).click();
    }
    private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }

    private void CreateAccount(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='email_create']")));
        final String randomEmail = randomEmail();
        getDriver().findElement(By.xpath("//*[@id='email_create']")).sendKeys(randomEmail);
        getDriver().findElement(By.xpath("//*[@id='SubmitCreate']")).click();
    }

    private void InputData(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='id_gender1']")));
        getDriver().findElement(By.xpath("//*[@id='id_gender1']")).click();
        getDriver().findElement(By.name("customer_firstname")).sendKeys("Walter");
        getDriver().findElement(By.name("customer_lastname")).sendKeys("Bash");
        getDriver().findElement(By.id("passwd")).sendKeys("12345");
        getDriver().findElement(By.xpath("//*[@id='days']/option[18]")).click();
        getDriver().findElement(By.xpath("//*[@id='months']/option[5]")).click();
        getDriver().findElement(By.xpath("//*[@id='years']/option[28]")).click();
        getDriver().findElement(By.id("address1")).sendKeys("1 Main str");
        getDriver().findElement(By.id("city")).sendKeys("Ajax");
        getDriver().findElement(By.xpath("//*[@id='id_state']/option[6]")).click();
        getDriver().findElement(By.id("postcode")).sendKeys("12345");
        getDriver().findElement(By.id("phone")).sendKeys("+12343334532");
        getDriver().findElement(By.xpath("//*[@id='submitAccount']")).click();
    }

    private void Assert() {
        WebElement Result = getDriver().findElement(By.xpath("//*[@id='center_column']/h1"));

        Assert.assertEquals(Result.getText(), "MY ACCOUNT");
    }
}
