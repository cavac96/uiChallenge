package pages;

import facades.WebDriverFacade;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private WebDriverFacade webDriverFacade;

    public BasePage(WebDriver webDriver){
        this.webDriverFacade = new WebDriverFacade(webDriver);
    }

    public WebDriverFacade getWebDriverFacade() {
        return webDriverFacade;
    }
}
