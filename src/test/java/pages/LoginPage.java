package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    //TODO: Is it betterehere? or in getModalText when it is needed?
//    @FindBy(xpath = "//div[@class=\"modal-body\"]")
//    private WebElement errorModalText;
//
//    @FindBy(xpath = "//div[@class=\"modal-footer\"]/button[contains(text(),'Intentar de Nuevo')]")
//    private WebElement errorModalButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage fillInForm(String username, String password){
        waitForInvisiblePageLoader();
        waitForForm();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        clickOnButton();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public void clickOnButton(){
        loginButton.click();
    }

    public void waitForForm(){
        waitForVisibilityOfElement(usernameField);
        waitForVisibilityOfElement(passwordField);
        waitForVisibilityOfElement(loginButton);
    }

    public String getModalText(){
        By modalLocation = By.xpath("//div[@class='modal-body' and contains(text(), 'Usuario y/o')]");
        waitForVisibilityOfElementLocated(modalLocation);
        WebElement modalContent = webDriver.findElement(modalLocation);
        return modalContent.getText();
    }

    public WebElement getModalButton(){
        By modalLocation = By.xpath("//button[contains(text(), 'Intentar de Nuevo')]");
        waitForElementLocatedToBeClickable(modalLocation);
        return webDriver.findElement(modalLocation);
    }

    public void acceptModal() {
        getModalButton().click();
    }
}
