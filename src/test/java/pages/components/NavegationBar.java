package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.*;

public class NavegationBar extends BasePage{
    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@href=\"/login/\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@class='header-login']/\n" +
            "descendant::a[@href='/logout/']/preceding-sibling::a")
    private WebElement welcomeButton;

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement shoppingCartTotal;

    @FindBy(xpath = "//ul[@class='nav-menu']/descendant::a[contains(text(),'CATÁLOGO')]")
    private WebElement catalogButton;

    @FindBy(xpath = "//div[@class='cart-icon-holder']/div[contains(@class,'cart-shopping')]")
    private WebElement shoppingCartButton;

    @FindBy(xpath = "//ul[@class='nav-menu']/descendant::a[contains(text(),'MIS ORDENES')]")
    private WebElement ordersButton;

    public NavegationBar(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationPage clickOnRegisterButton() {
        getWebDriverFacade().waitForInvisiblePageLoader();
        registerButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), RegistrationPage.class);
    }

    public LoginPage clickOnLoginButton() {
        getWebDriverFacade().waitForInvisiblePageLoader();
        loginButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), LoginPage.class);
    }

    public String getWelcomeText() {
        getWebDriverFacade().waitForVisibilityOfElement(welcomeButton);
        return welcomeButton.getText();
    }

    public String getLoginText() {
        getWebDriverFacade().waitForVisibilityOfElement(loginButton);
        return loginButton.getText();
    }

    public HomePage clickOnLogoutButton() {
        getWebDriverFacade().waitForElementToBeClickable(logoutButton);
        getWebDriverFacade().waitForInvisiblePageLoader();
        logoutButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), HomePage.class);
    }

    public String getTotalProducts(){
        return shoppingCartTotal.getText();
    }

    public CatalogPage clickOnCatalogButton(){
        getWebDriverFacade().waitForElementToBeClickable(catalogButton);
        getWebDriverFacade().waitForInvisiblePageLoader();
        catalogButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), CatalogPage.class);
    }

    public void verifyAddedProductsAfterLogin(){
        getWebDriverFacade().waitForInvisiblePageLoader();
        getWebDriverFacade().waitForVisibilityOfElement(shoppingCartTotal);
    }

    public CheckOutPage checkOut(){
        clickOnShoppingCart();
        return clickOnCheckOutButton();
    }

    public void clickOnShoppingCart(){
        getWebDriverFacade().waitForElementToBeClickable(shoppingCartButton);
        getWebDriverFacade().waitForInvisiblePageLoader();
        shoppingCartButton.click();
    }

    public CheckOutPage clickOnCheckOutButton(){
        //By checkOutButtonLocator = By.xpath(Paths.CHECKOUT_BUTTON);
        By checkOutButtonLocator = By.xpath("//button[parent::div[@class='mini-shopcart-action'] and contains(@class,'btn-checkout')]");
        WebElement checkoutButton = getWebDriverFacade().getWebDriver().findElement(checkOutButtonLocator);
        getWebDriverFacade().moveToElement(checkoutButton);
        getWebDriverFacade().waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), CheckOutPage.class);
    }

    public OrdersPage clickOnOrdersButton(){
        getWebDriverFacade().waitForElementToBeClickable(ordersButton);
        getWebDriverFacade().waitForInvisiblePageLoader();
        ordersButton.click();
        return PageFactory.initElements(getWebDriverFacade().getWebDriver(), OrdersPage.class);
    }
}
