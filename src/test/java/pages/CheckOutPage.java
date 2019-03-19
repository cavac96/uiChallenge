package pages;

import models.Billing;
import models.PaymentMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Paths;
import utils.Utils;

import java.util.List;

public class CheckOutPage extends NavegationBar{

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[@id='address']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id='details']")
    private WebElement aptField;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countryDropDown;

    @FindBy(xpath = "//select[@id='dpto']")
    private WebElement departmentDropDown;

    @FindBy(xpath = "//input[@id='zip']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='card_number']")
    private WebElement cardNumberField;

    @FindBy(xpath = "//input[@id='exp_date']")
    private WebElement dueDateField;

    @FindBy(xpath = "//input[@id='code']")
    private WebElement cvvCodeField;

    @FindBy(xpath = "//button[@id='purchase']")
    private WebElement paymentButton;

    @FindBy(xpath = "//div[@class='radio']/label")
    private List<WebElement> paymentMethodList;


    public CheckOutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage fillInForms(PaymentMethod paymentMethod, Billing billing){
        waitForInvisiblePageLoader();
        waitForBillingDataForm();
        fillInBillingDataForm(billing);
        waitForPaymentMethodForm();
        fillInPaymentForm(paymentMethod);
        paymentButton.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public HomePage fillInForms(Billing billing){
        waitForInvisiblePageLoader();
        waitForBillingDataForm();
        fillInBillingDataForm(billing);
        waitForVisibilityOfElements(paymentMethodList);
        selectPaymentMethod();
        paymentButton.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public void waitForBillingDataForm(){
        waitForVisibilityOfElement(nameField);
        waitForVisibilityOfElement(lastnameField);
        waitForVisibilityOfElement(addressField);
        waitForVisibilityOfElement(aptField);
        waitForVisibilityOfElement(countryDropDown);
        waitForVisibilityOfElement(departmentDropDown);
        waitForVisibilityOfElement(postalCodeField);
        waitForVisibilityOfElement(phoneField);
        waitForVisibilityOfElement(emailField);
        waitForVisibilityOfElement(paymentButton);
    }

    public void waitForPaymentMethodForm(){
        waitForVisibilityOfElement(cardNumberField);
        waitForVisibilityOfElement(dueDateField);
        waitForVisibilityOfElement(cvvCodeField);
    }

    public void fillInBillingDataForm(Billing billing){
        nameField.sendKeys(billing.getName());
        lastnameField.sendKeys(billing.getLastName());
        addressField.sendKeys(billing.getAddress());
        aptField.sendKeys(billing.getApto());
        selectOption(countryDropDown);
        selectOption(departmentDropDown);
        postalCodeField.sendKeys(billing.getZip());
        phoneField.sendKeys(billing.getPhone());
        emailField.sendKeys(billing.getEmail());
    }

    public void selectOption(WebElement select){
        Select dropDown = new Select(select);
        int index = getRandomSelectIndex(dropDown.getOptions().size());
        dropDown.selectByIndex(index);
    }

    public int getRandomSelectIndex(int size){
        return Utils.generateRandomInt(1, size - 1);
    }

    public void fillInPaymentForm(PaymentMethod paymentMethod){
        cardNumberField.sendKeys(paymentMethod.getCardNumber());
        dueDateField.sendKeys(paymentMethod.getDueDate());
        cvvCodeField.sendKeys(paymentMethod.getCode());
    }

    public void acceptModal(){
        WebElement modal = getPurchaseModal();
        WebElement modalButton = getModalButton(modal);
        modalButton.click();
    }

    public WebElement getPurchaseModal(){
        By modalLocator = By.xpath(Paths.PURCHASE_MODAL);
        waitForVisibilityOfElementLocated(modalLocator);
        return webDriver.findElement(modalLocator);
    }

    public WebElement getModalButton(WebElement modal){
        By okButton = By.xpath(Paths.MODAL_BUTTON);
        return modal.findElement(okButton);
    }

    public String getModalTitle(){
        WebElement modal = getPurchaseModal();
        By modalTitleLocator = By.xpath(Paths.MODAL_TITLE);
        return modal.findElement(modalTitleLocator).getText();
    }

    public void selectPaymentMethod(){
        int index = getRandomPaymentMethod(paymentMethodList.size());
        clickAction(paymentMethodList.get(index));
    }

    public int getRandomPaymentMethod(int max){
        return Utils.generateRandomInt(1, max - 1);
    }
}
