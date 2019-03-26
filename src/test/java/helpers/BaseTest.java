package helpers;

import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.saucerest.SauceREST;


import factories.WebDriverFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import utils.PropertiesReader;
import utils.ScreenShotManager;

public class BaseTest {

    protected static HomePage homePage;
    private static WebDriver webDriver;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            ScreenShotManager.takeScreenShot(webDriver, description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            webDriver.quit();
        }
    };

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(PropertiesReader.getValueByKey("browser.type"));
        webDriver.get(PropertiesReader.getValueByKey("ecofood.url"));
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }
}
