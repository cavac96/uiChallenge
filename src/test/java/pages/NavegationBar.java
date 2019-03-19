package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Paths;

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
        waitForInvisiblePageLoader();
        registerButton.click();
        return PageFactory.initElements(webDriver, RegistrationPage.class);
    }

    public LoginPage clickOnLoginButton() {
        waitForInvisiblePageLoader();
        loginButton.click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public String getWelcomeText() {
        waitForVisibilityOfElement(welcomeButton);
        return welcomeButton.getText();
    }

    public String getLoginText() {
        waitForVisibilityOfElement(loginButton);
        return loginButton.getText();
    }

    public HomePage clickOnLogoutButton() {
        waitForElementToBeClickable(logoutButton);
        waitForInvisiblePageLoader();
        logoutButton.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public String getTotalProducts(){
        return shoppingCartTotal.getText();
    }

    public CatalogPage clickOnCatalogButton(){
        waitForElementToBeClickable(catalogButton);
        waitForInvisiblePageLoader();
        catalogButton.click();
        return PageFactory.initElements(webDriver, CatalogPage.class);
    }

    public void verifyAddedProductsAfterLogin(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElement(shoppingCartTotal);
    }

    public CheckOutPage checkOut(){
        clickOnShoppingCart();
        return clickOnCheckOutButton();
    }

    public void clickOnShoppingCart(){
        waitForElementToBeClickable(shoppingCartButton);
        waitForInvisiblePageLoader();
        shoppingCartButton.click();
    }

    public CheckOutPage clickOnCheckOutButton(){
        By checkOutButtonLocator = By.xpath(Paths.CHECKOUT_BUTTON);
        WebElement checkoutButton = webDriver.findElement(checkOutButtonLocator);
        moveToElement(checkoutButton);
        waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();
        return PageFactory.initElements(webDriver, CheckOutPage.class);
    }

    public OrdersPage clickOnOrdersButton(){
        waitForElementToBeClickable(ordersButton);
        waitForInvisiblePageLoader();
        ordersButton.click();
        return PageFactory.initElements(webDriver, OrdersPage.class);
    }
}
