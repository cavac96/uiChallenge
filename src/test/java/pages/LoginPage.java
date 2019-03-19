package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Paths;

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
        waitForInvisiblePageLoader();
        waitForForm();
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        clickOnButton();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public void clickOnButton() {
        loginButton.click();
    }

    public void waitForForm() {
        waitForVisibilityOfElement(usernameField);
        waitForVisibilityOfElement(passwordField);
        waitForVisibilityOfElement(loginButton);
    }

    public String getModalText() {
        By modalLocation = By.xpath(Paths.LOGIN_MODAL_TEXT);
        waitForVisibilityOfElementLocated(modalLocation);
        WebElement modalContent = webDriver.findElement(modalLocation);
        return modalContent.getText();
    }

    public WebElement getModalButton() {
        By modalLocation = By.xpath(Paths.LOGIN_MODAL_BUTTON);
        waitForElementLocatedToBeClickable(modalLocation);
        return webDriver.findElement(modalLocation);
    }

    public void acceptModal() {
        getModalButton().click();
    }
}
