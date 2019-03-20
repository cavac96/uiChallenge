package helpers;

import factories.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import utils.PropertiesReader;

public class BaseTest{

    private WebDriver webDriver;
    protected HomePage homePage;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(PropertiesReader.getValueByKey("browser.type"));
        webDriver.get(PropertiesReader.getValueByKey("ecofood.url"));
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @After
    public void after() {
        webDriver.quit();
    }
}
