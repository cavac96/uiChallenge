import helpers.BaseTest;
import models.User;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import utils.ErrorMessages;
import utils.Messages;
import utils.PropertiesReader;
import utils.Utils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class EcofoodTest extends BaseTest {
    @Test
    public void successfulRegistration() {
        RegistrationPage registrationPage = homePage.getNavegationBar().clickOnRegisterButton();
        registrationPage.fillInForm(Utils.generateRandomUserInfo());
        assertThat(ErrorMessages.SUCCESSFUL_REGISTRATION, registrationPage.getAlertText(), equalTo(Messages.SUCCESSFUL_REGISTER));
        registrationPage.getWebDriverFacade().acceptAlert();
    }

    @Test
    public void successfulLogin() {
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        assertThat(ErrorMessages.SUCCESSFUL_LOGIN, homePage.getNavegationBar().getWelcomeText(), containsString("Bienvenido"));
    }

    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        homePage = loginPage.fillInForm(new User("a", "b"));
        assertThat(ErrorMessages.UNSUCCESSFUL_LOGIN, loginPage.getModalText(), equalTo(Messages.UNSUCCESSFUL_LOGIN));
        loginPage.acceptModal();
    }

    @Test
    public void logout(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.getNavegationBar().clickOnLogoutButton();
        assertThat(ErrorMessages.LOGOUT, homePage.getNavegationBar().getLoginText(), containsString("Iniciar sesión"));
    }

    @Test
    public void addProductsHomePageAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(1);
        assertThat(ErrorMessages.ADD_FROM_HOME, homePage.getNavegationBar().getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductsCatalogAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        CatalogPage catalogPage = homePage.getNavegationBar().clickOnCatalogButton();
        catalogPage.addProductFromCategoryPage();
        assertThat(ErrorMessages.ADD_FROM_CATALOG, catalogPage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void verifyAddedProductsAfterLogin(){
        homePage.addProductsFromHomePage(1);
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.getNavegationBar().verifyAddedProductsAfterLogin();
        assertThat(ErrorMessages.VERIFY_AFTER_LOGIN, homePage.getNavegationBar().getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductStockAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        int index = homePage.addProductStock();
        assertThat(ErrorMessages.ADD_STOCK, homePage.getNavegationBar().getTotalProducts(), equalTo(homePage.getStockSize(index)));
    }

    @Test
    public void decreaseQuantityAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(3);
        WebElement product = homePage.toRemoveProduct();
        assertThat(ErrorMessages.DECREASE_QUANTITY, homePage.getProductCount(product), equalTo("2"));
    }

    @Test
    public void emptyShoppingCartAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(3);
        homePage.addProductsFromHomePage(3);
        homePage.toEmptyShoppingCart();
        assertThat(ErrorMessages.EMPTY_CART, homePage.getNavegationBar().getTotalProducts(), equalTo("0"));
    }

    @Test
    public void verifyProductDetailsAuth(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.verifyProductDetails();
        assertThat(ErrorMessages.VERIFY_PRODUCT_DETAIL, homePage.getProductDetailsTitle(), equalTo(homePage.getProductName()));
    }

    @Test
    public void checkoutUnregisteredPaymentMethod(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(1);
        CheckOutPage checkOutPage = homePage.getNavegationBar().checkOut();
        checkOutPage.fillInForms(Utils.generateRandomPaymentMethod(), Utils.generateRandomBilling());
        assertThat(ErrorMessages.CHECKOUT_UNREGISTERED_PM, checkOutPage.getModalTitle(), equalTo("Compra Realizada"));
    }

    @Test
    public void checkoutRegisteredPaymentMethod(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(1);
        CheckOutPage checkOutPage = homePage.getNavegationBar().checkOut();
        checkOutPage.fillInForms(Utils.generateRandomBilling());
        assertThat(ErrorMessages.CHECKOUT_REGISTERED_PM, checkOutPage.getModalTitle(), equalTo("Compra Realizada"));
    }

    @Test
    public void verifyOrderDetails(){
        LoginPage loginPage = homePage.getNavegationBar().clickOnLoginButton();
        User user = new User(PropertiesReader.getValueByKey("ecofood.user"), PropertiesReader.getValueByKey("ecofood.password"));
        homePage = loginPage.fillInForm(user);
        homePage.addProductsFromHomePage(1);
        homePage.addProductsFromHomePage(1);
        List<String> homeList = homePage.getAddedProductsName();

        CheckOutPage checkOutPage = homePage.getNavegationBar().checkOut();
        homePage = checkOutPage.fillInForms(Utils.generateRandomBilling());
        checkOutPage.acceptModal();

        OrdersPage ordersPage = homePage.getNavegationBar().clickOnOrdersButton();
        ordersPage.verifyOrderDetails();
        List<String> orderList = ordersPage.getOrderProductsName();
        //ordersPage.acceptModal();
        assertThat(ErrorMessages.VERIFY_ORDER_DETAILS, homeList, equalTo(orderList));
    }
}
