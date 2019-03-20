package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage fillInForm(User user) {
        getWebDriverFacade().waitForInvisiblePageLoader();
        waitForForm();
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        clickOnButton();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), HomePage.class);
    }

    public void clickOnButton() {
        loginButton.click();
    }

    public void waitForForm() {
        getWebDriverFacade().waitForVisibilityOfElement(usernameField);
        getWebDriverFacade().waitForVisibilityOfElement(passwordField);
        getWebDriverFacade().waitForVisibilityOfElement(loginButton);
    }

    public String getModalText() {
        //By modalLocation = By.xpath(Paths.LOGIN_MODAL_TEXT);
        By modalLocation = By.xpath("//div[@class='modal-body' and contains(text(), 'Usuario y/o')]");
        getWebDriverFacade().waitForVisibilityOfElementLocated(modalLocation);
        WebElement modalContent = getWebDriverFacade().findElementByLocator(modalLocation);
        return modalContent.getText();
    }

    public WebElement getModalButton() {
        //By modalLocation = By.xpath(Paths.LOGIN_MODAL_BUTTON);
        By modalLocation = By.xpath("//button[contains(text(), 'Intentar de Nuevo')]");
        getWebDriverFacade().waitForElementLocatedToBeClickable(modalLocation);
        return getWebDriverFacade().findElementByLocator(modalLocation);
    }

    public void acceptModal() {
        getModalButton().click();
    }
}
