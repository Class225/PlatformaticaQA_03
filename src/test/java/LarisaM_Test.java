import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LarisaM_Test extends BaseTest {

    private static final String URL_PLAYGROUND = "http://uitestingplayground.com/";
    private static final String URL_MDN = "https://developer.mozilla.org/en-US/";
    private static final By RESOURCES_LINK_XPATH = By.xpath("//a[@href='/resources']");
    private static final By LINK_ON_SITE_3SCL_XPATH = By.xpath("//a[@href='https://www.w3schools.com']");
    private static final By NAV_BUTTON = By.xpath("//a[@id='navbtn_references']");
    private static final By LINK_ON_SITE_MDN_XPATH = By.xpath("//a[contains(text(),'MDN')]");
    private static final String REFERENCES = "References";

    @Test
    public void testLarisaMalushkinaGetElementCheckBox() {

        getDriver().get(URL_PLAYGROUND);

        WebElement goResources = getDriver().findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSite3Scl = getDriver().findElement(LINK_ON_SITE_3SCL_XPATH);
        openSite3Scl.click();
        WebElement actualResult = getDriver().findElement(NAV_BUTTON);

        Assert.assertEquals(actualResult.getText(),REFERENCES);
    }

    @Test
    public void testLarisaMalushkinaGetDynamicTable() {

        getDriver().get(URL_PLAYGROUND);

        WebElement goResources = getDriver().findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSiteMDN = getDriver().findElement(LINK_ON_SITE_MDN_XPATH);
        openSiteMDN.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), URL_MDN);
    }
}


