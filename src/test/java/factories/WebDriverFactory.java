package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverFactory {
    private final static Logger LOGGER = Logger.getLogger("WebDriverFactory");

    public static WebDriver getWebDriver(String browserTypeName){
        BrowserEnum browserType = getEnum(browserTypeName);
        switch (browserType){
            case CHROME:
                return new ChromeDriver();
            default:
                LOGGER.log(Level.WARNING, "Not such a driver. Using ChromeDriver instead");
                return new ChromeDriver();
        }
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
