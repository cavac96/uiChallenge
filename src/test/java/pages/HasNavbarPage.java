package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.components.NavegationBar;

public class HasNavbarPage extends BasePage{

    private NavegationBar navegationBar;

    public HasNavbarPage(WebDriver webDriver) {
        super(webDriver);
        //TODO PO?
        navegationBar = PageFactory.initElements(webDriver, NavegationBar.class);
    }

    public NavegationBar getNavegationBar() {
        return navegationBar;
    }
}
