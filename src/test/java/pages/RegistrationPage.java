package pages;

import models.User;
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

    public RegistrationPage(WebDriver webDriver){
        super(webDriver);
    }

    public void fillInForm(User user) {
        getWebDriverFacade().waitForInvisiblePageLoader();
        waitForRegisterForm();
        nameField.sendKeys(user.getName());
        lastNameField.sendKeys(user.getLastname());
        usernameField.sendKeys(user.getUsername());
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        passwordConfirmationField.sendKeys(user.getPasswordConfirmation());
        clickOnRegisterButton();
    }

    public void clickOnRegisterButton(){
        registerButton.click();
    }

    public String getAlertText(){
        getWebDriverFacade().waitForAlert();
        registerAlert = getWebDriverFacade().switchToAlert();
        return registerAlert.getText();
    }


    public void waitForRegisterForm(){
        getWebDriverFacade().waitForVisibilityOfElement(nameField);
        getWebDriverFacade().waitForVisibilityOfElement(lastNameField);
        getWebDriverFacade().waitForVisibilityOfElement(usernameField);
        getWebDriverFacade().waitForVisibilityOfElement(emailField);
        getWebDriverFacade().waitForVisibilityOfElement(passwordField);
        getWebDriverFacade().waitForVisibilityOfElement(passwordConfirmationField);
        getWebDriverFacade().waitForElementToBeClickable(registerButton);
    }

}
