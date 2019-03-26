package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertiesReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverFactory {
    private final static Logger LOGGER = Logger.getLogger("WebDriverFactory");

    public static WebDriver getWebDriver(String browserTypeName){
        BrowserEnum browserType = getEnum(browserTypeName);
        String sauceUserName = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
        String sauceURL = PropertiesReader.getValueByKey("sauce.url");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("username", sauceUserName);
        capabilities.setCapability("accessKey", sauceAccessKey);
        capabilities.setCapability("platform", PropertiesReader.getValueByKey("platform"));

        WebDriver webDriver = null;
        switch (browserType){
            case CHROME:
                //return new ChromeDriver();
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("version", "72.0");
            default:
                LOGGER.log(Level.WARNING, "Not such a driver. Using ChromeDriver instead");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("version", "72.0");
        }
        try {
            webDriver = new RemoteWebDriver(new URL(sauceURL),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return webDriver;
    }

    private static BrowserEnum getEnum(String browserTypeName){
        BrowserEnum browserEnum;
        try{
            browserEnum = BrowserEnum.valueOf(browserTypeName.toUpperCase().trim());
        }catch (IllegalArgumentException e){
            browserEnum = BrowserEnum.DEF;
        }
        return browserEnum;
    }
}
