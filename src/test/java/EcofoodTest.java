import models.Billing;
import models.PaymentMethod;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import utils.ErrorMessages;
import utils.FileHandler;
import utils.Messages;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcofoodTest {
    WebDriver webDriver;
    HomePage homePage;
    User user;
    User loginInfo;
    PaymentMethod paymentMethod;
    Billing billing;

    @Test
    public void successfulRegistration() {
        RegistrationPage registrationPage = homePage.clickOnRegisterButton();
        registrationPage.fillInForm(user);
        assertThat(ErrorMessages.SUCCESSFUL_REGISTRATION, registrationPage.getAlertText(), equalTo(Messages.SUCCESSFUL_REGISTER));
        registrationPage.acceptAlert();
    }

    @Test
    public void successfulLogin() {
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        assertThat(ErrorMessages.SUCCESSFUL_LOGIN, homePage.getWelcomeText(), containsString("Bienvenido"));
    }

    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(new User("a", "b"));
        assertThat(ErrorMessages.UNSUCCESSFUL_LOGIN, loginPage.getModalText(), equalTo(Messages.UNSUCCESSFUL_LOGIN));
        loginPage.acceptModal();
    }

    @Test
    public void logout(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.clickOnLogoutButton();
        assertThat(ErrorMessages.LOGOUT, homePage.getLoginText(), containsString("Iniciar sesión"));
    }

    @Test
    public void addProductsHomePageAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(1);
        assertThat(ErrorMessages.ADD_FROM_HOME, homePage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductsCatalogAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        CatalogPage catalogPage = homePage.clickOnCatalogButton();
        catalogPage.addProductFromCategoryPage();
        assertThat(ErrorMessages.ADD_FROM_CATALOG, catalogPage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void verifyAddedProductsAfterLogin(){
        homePage.addProductsFromHomePage(1);
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.verifyAddedProductsAfterLogin();
        assertThat(ErrorMessages.VERIFY_AFTER_LOGIN, homePage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductStockAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        int index = homePage.addProductStock();
        assertThat(ErrorMessages.ADD_STOCK, homePage.getTotalProducts(), equalTo(homePage.getStockSize(index)));
    }

    @Test
    public void decreaseQuantityAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(3);
        WebElement product = homePage.toRemoveProduct();
        assertThat(ErrorMessages.DECREASE_QUANTITY, homePage.getProductCount(product), equalTo("2"));
    }

    @Test
    public void emptyShoppingCartAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(3);
        homePage.addProductsFromHomePage(3);
        homePage.toEmptyShoppingCart();
        assertThat(ErrorMessages.EMPTY_CART, homePage.getTotalProducts(), equalTo("0"));
    }

    @Test
    public void verifyProductDetailsAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.verifyProductDetails();
        assertThat(ErrorMessages.VERIFY_PRODUCT_DETAIL, homePage.getProductDetailsTitle(), equalTo(homePage.getProductName()));
    }

    @Test
    public void checkoutUnregisteredPaymentMethod(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(1);
        CheckOutPage checkOutPage = homePage.checkOut();
        checkOutPage.fillInForms(paymentMethod, billing);
        assertThat(ErrorMessages.CHECKOUT_UNREGISTERED_PM, checkOutPage.getModalTitle(), equalTo("Compra Realizada"));
    }

    @Test
    public void checkoutRegisteredPaymentMethod(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(1);
        CheckOutPage checkOutPage = homePage.checkOut();
        checkOutPage.fillInForms(billing);
        assertThat(ErrorMessages.CHECKOUT_REGISTERED_PM, checkOutPage.getModalTitle(), equalTo("Compra Realizada"));
    }

    @Test
    public void verifyOrderDetails(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm(loginInfo);
        homePage.addProductsFromHomePage(1);
        homePage.addProductsFromHomePage(1);

        List<String> homeList = homePage.getAddedProductsName();

        CheckOutPage checkOutPage = homePage.checkOut();
        homePage = checkOutPage.fillInForms(billing);
        checkOutPage.acceptModal();
        OrdersPage ordersPage = homePage.clickOnOrdersButton();
        ordersPage.verifyOrderDetails();

        List<String> orderList = ordersPage.getOrderProductsName();
        //ordersPage.acceptModal();
        System.out.println();
        assertThat(ErrorMessages.VERIFY_ORDER_DETAILS, homeList, equalTo(orderList));
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("http://ecofoodmarket.herokuapp.com/");
        homePage = PageFactory.initElements(webDriver, HomePage.class);

        user = FileHandler.readTextFileUser();
        paymentMethod = FileHandler.readTextFilePaymentMethod();
        billing = FileHandler.readTextFileBilling();
        loginInfo = FileHandler.readTextFileLogin();
    }

    public WebDriver gd() {
        return new FirefoxDriver();
    }

    @After
    public void after() {
        webDriver.quit();
    }

}
