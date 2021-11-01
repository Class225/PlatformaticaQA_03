import base.BaseTest;
import com.google.gson.internal.bind.util.ISO8601Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AnnaGTest extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/lyrics.html";

    private WebDriver driver;

    private static final String BOTTLES = " bottles of beer";
    private static final String BOTTLE = " bottle of beer";
    private static final String WALL = " on the wall";
    private static final String TAKE = "Take one down and pass it around";
    private static final String COMA_SPACE = ", ";
    private static final String DOT = ".";
    private static final String GO = "Go to the store and buy some more";
    private static final String NO_MORE = "No more";
    private static final String NEW_RESULT = "";
    private static final String RESULT = "";
    private static final String ENTER = "\n";


    private  String songLyric() {
        StringBuilder expectedResult = new StringBuilder();

        for (int i = 99; i >= 0; i--) {
            if (i == 99) {

                expectedResult
                        .append(i)
                        .append(BOTTLES)
                        .append(WALL)
                        .append(COMA_SPACE)
                        .append(i)
                        .append(BOTTLES)
                        .append(DOT)
                        .append(ENTER);


            } else if (i == 0) {
                expectedResult
                        .append(TAKE)
                        .append(COMA_SPACE)
                        .append(NO_MORE.toLowerCase(Locale.ROOT))
                        .append(BOTTLES)
                        .append(WALL)
                        .append(DOT)
                        .append(NO_MORE)
                        .append(BOTTLES)
                        .append(WALL)
                        .append(COMA_SPACE)
                        .append(NO_MORE.toLowerCase(Locale.ROOT))
                        .append(BOTTLES)
                        .append(DOT)
                        .append(ENTER);
                expectedResult
                        .append(GO)
                        .append(COMA_SPACE)
                        .append(99)
                        .append(BOTTLES)
                        .append(WALL)
                        .append(DOT);


            } else {
                if (i != 1) {
                    expectedResult
                            .append(TAKE)
                            .append(COMA_SPACE)
                            .append(i)
                            .append(BOTTLES)
                            .append(WALL)
                            .append(DOT)
                            .append(i)
                            .append(BOTTLES)
                            .append(WALL)
                            .append(COMA_SPACE)
                            .append(i)
                            .append(BOTTLES)
                            .append(DOT)
                            .append(ENTER);


                } else if (i == 1) {
                    expectedResult
                            .append(TAKE)
                            .append(COMA_SPACE)
                            .append(i)
                            .append(BOTTLE)
                            .append(WALL)
                            .append(DOT)
                            .append(i)
                            .append(BOTTLE)
                            .append(WALL)
                            .append(COMA_SPACE)
                            .append(i)
                            .append(BOTTLE)
                            .append(DOT)
                            .append(ENTER);


                }
            }
        }
        return String.valueOf(expectedResult);
    }

                @Test

                public void testBottles99Song () {
                    getDriver().get(URL);
                    List<WebElement> pAllTags = getDriver().findElements(By.xpath("//div[@id = 'main']/p")); // поиск всех элементов
                    String expectedResult = songLyric();
                    // List<WebElement> pFirst = driver.findElement(By.xpath("//div[@id = 'main']/p[1]")); // потск по одному элементу

                    //List<String> songLyric = new ArrayList<>(); // каждый раз будет добавляться новый p из 100

                    //or(int i = 0; i < songLyric.size(); i++){ // пройдеися по всем элементам, и обратимся в каждому элементу и считывать тест
                    // и складывать в лист
                    //songLyric. add(pAllTags.get(i).getText()); // метод эдд добавляет по одному
                    // берем лист songLyrics, в который хотим что-то складывать add что хотим сложить или можно через кавычки вставить
                    //тескт написанный*/


                    String actualResult = "";
                    for (int i = 0; i < pAllTags.size(); i++) {
                        actualResult = actualResult + pAllTags.get(i).getText();
                    }
                    Assert.assertEquals(actualResult, expectedResult);


                }

            }
