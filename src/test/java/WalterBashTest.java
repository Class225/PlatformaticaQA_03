import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WalterBashTest {

    WebDriver driver;

    @BeforeTest

    private void ChromeDriverSetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    private void SignUpAutomationpractice() {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        NavigateToCreateAccountPage();
        CreateAccount(wait);
        InputData(wait);
        Assert();
    }

    private void CreateAccount(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='email_create']")));
        driver.findElement(By.xpath("//*[@id='email_create']")).sendKeys("bash34@aol.com");
        driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();
    }

    private void NavigateToCreateAccountPage() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//*[@title='Log in to your customer account']")).click();
    }

    private void InputData(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='id_gender1']")));
        driver.findElement(By.xpath("//*[@id='id_gender1']")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Walter");
        driver.findElement(By.name("customer_lastname")).sendKeys("Bash");
        driver.findElement(By.id("passwd")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@id='days']/option[18]")).click();
        driver.findElement(By.xpath("//*[@id='months']/option[5]")).click();
        driver.findElement(By.xpath("//*[@id='years']/option[28]")).click();
        driver.findElement(By.id("address1")).sendKeys("1 Main str");
        driver.findElement(By.id("city")).sendKeys("Ajax");
        driver.findElement(By.xpath("//*[@id='id_state']/option[6]")).click();
        driver.findElement(By.id("postcode")).sendKeys("12345");
        driver.findElement(By.id("phone")).sendKeys("+12343334532");
        driver.findElement(By.xpath("//*[@id='submitAccount']")).click();
    }

    private void Assert() {
        WebElement Result = driver.findElement(By.xpath("//*[@id='center_column']/h1"));

        Assert.assertEquals(Result.getText(), "MY ACCOUNT");
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
