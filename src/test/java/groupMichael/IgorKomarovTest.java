package groupMichael;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStoreHomePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IgorKomarovTest {

    private WebDriver driver;
    private BookStoreHomePage homePage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/books");
        homePage = new BookStoreHomePage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void testVerifyHeader() {
        Assert.assertEquals(homePage.getHeaderText(), "Book Store");
    }

    @Test
    public void testVerifyBooks() {
        List<String> booksNameList = new ArrayList<>();
        List<String> booksAuthorList = new ArrayList<>();
        List<String> booksPublisherList = new ArrayList<>();
        int count = 0;

        final List<String> EXPECTED_NAMES = Arrays.asList("Git Pocket Guide", "Learning JavaScript Design Patterns",
                "Designing Evolvable Web APIs with ASP.NET", "Speaking JavaScript", "You Don't Know JS",
                "Programming JavaScript Applications", "Eloquent JavaScript, Second Edition",
                "Understanding ECMAScript 6");

        final List<String> EXPECTED_AUTHORS = Arrays.asList("Richard E. Silverman", "Addy Osmani", "Glenn Block et al.",
                "Axel Rauschmayer", "Kyle Simpson", "Eric Elliott", "Marijn Haverbeke", "Nicholas C. Zakas");

        final List<String> EXPECTED_PUBLISHERS = Arrays.asList("O'Reilly Media", "O'Reilly Media", "O'Reilly Media", "O'Reilly Media", "O'Reilly Media",
                "O'Reilly Media", "No Starch Press", "No Starch Press");

        List<WebElement> resultList = homePage.getBooks();

        for (WebElement item : resultList) {
            List<String> itemInfo = Arrays.asList(item.getText().split("\n"));
            if (!itemInfo.isEmpty()) {
                if (itemInfo.size() == 3) {
                    booksNameList.add(itemInfo.get(0));
                    booksAuthorList.add(itemInfo.get(1));
                    booksPublisherList.add(itemInfo.get(2));
                }
                count += 1;
            }
        }
        Assert.assertEquals(booksNameList, EXPECTED_NAMES);
        Assert.assertEquals(booksAuthorList, EXPECTED_AUTHORS);
        Assert.assertEquals(booksPublisherList, EXPECTED_PUBLISHERS);
        Assert.assertEquals(count, 10);
    }

    @Test
    public void testVerifySearchPositive() {
        homePage.setSearchField("Git");

        List<WebElement> resultList = homePage.getBooks();
        resultList.forEach(item -> {
                    String itemInfo = item.getText().trim();
                    if (!itemInfo.equals("")) {
                        Assert.assertTrue(item.getText().toLowerCase().contains("git"));
                    }
                }
        );
    }

    @Test
    public void testVerifySearchNegative() {
        homePage.setSearchField("nothing");
        List<WebElement> resultList = homePage.getBooks();

        resultList.forEach(item -> {
                    String itemInfo = item.getText().trim();
                    Assert.assertEquals(itemInfo, "");
                }
        );
    }

    @Test
    public void testVerifyPagination() {

        final int[] SIZES = {5, 10, 20, 25, 50, 100};

        for (int size : SIZES) {
            homePage.setRowsPerPage(size);
            List<WebElement> resultList = homePage.getBooks();
            Assert.assertEquals(resultList.size(), size);
        }
    }
}