package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    //@FindBy(xpath = "//input[@id='name']")
    @FindBy(id = "name")
    private WebElement nameField;

    //@FindBy(xpath = "//input[@id='lastname']")
    @FindBy(id = "lastname")
    private WebElement lastNameField;

    //@FindBy(xpath = "//input[@id='username']")
    @FindBy(id = "username")
    private WebElement usernameField;

    //@FindBy(xpath = "//input[@id='email']")
    @FindBy(id = "email")
    private WebElement emailField;

    //@FindBy(xpath = "//input[@id='pass1']")
    @FindBy(id = "pass1")
    private WebElement passwordField;

    //@FindBy(xpath = "//input[@id='pass2']")
    @FindBy(id = "pass2")
    private WebElement passwordConfirmationField;

    //@FindBy(xpath = "//button[@id='register']")
    @FindBy(id = "register")
    private WebElement registerButton;

    private Alert registerAlert;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillInForm(String name, String lastname, String username, String email, String password, String passwordConfirmation) {
        waitForInvisiblePageLoader();
        waitForRegisterForm();

        nameField.sendKeys(name);
        lastNameField.sendKeys(lastname);
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        passwordConfirmationField.sendKeys(passwordConfirmation);

        clickOnRegisterButton();
    }

    public void clickOnRegisterButton(){
        registerButton.click();
    }

    public String getAlertText(){
        waitForAlert();
        registerAlert = webDriver.switchTo().alert();
        return registerAlert.getText();
    }


    public void waitForRegisterForm(){
        waitForVisibilityOfElement(nameField);
        waitForVisibilityOfElement(lastNameField);
        waitForVisibilityOfElement(usernameField);
        waitForVisibilityOfElement(emailField);
        waitForVisibilityOfElement(passwordField);
        waitForVisibilityOfElement(passwordConfirmationField);
        waitForElementToBeClickable(registerButton);
    }

}
