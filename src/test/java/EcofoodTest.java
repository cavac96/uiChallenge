import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CatalogPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.Messages;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcofoodTest {
    WebDriver webDriver;
    HomePage homePage;


    @Test
    public void successfulRegistration() {
        RegistrationPage registrationPage = homePage.clickOnRegisterButton();
        registrationPage.fillInForm("uuu",
                "uuu", "uuu", "uuu@email.com",
                "123", "123");

        assertThat("User already registered", registrationPage.getAlertText(), equalTo(Messages.SUCCESSFUL_REGISTER));
        registrationPage.acceptAlert();
    }

    @Test
    public void successfulLogin() {
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        assertThat("Unsuccessful login", homePage.getWelcomeText(), containsString("Bienvenido"));
    }

    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "b");
        assertThat("Unsuccessful login failed", loginPage.getModalText(), equalTo(Messages.UNSUCCESSFUL_LOGIN));
        loginPage.acceptModal();
    }

    @Test
    public void logout(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        homePage.clickOnLogoutButton();
        assertThat("Unsuccessful logout", homePage.getLoginText(), containsString("Iniciar sesión"));
    }

    @Test
    public void addProductsHomePageAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        homePage.addProductsFromHomePage();
        assertThat("Product not added", homePage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductsCatalogAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        CatalogPage catalogPage = homePage.clickOnCatalogButton();
        catalogPage.addProductFromCategoryPage();
        assertThat("Product not added", catalogPage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void verifyAddedProductsAfterLogin(){
        homePage.addProductsFromHomePage();
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        homePage.verifyAddedProductsAfterLogin();
        assertThat("Products not saved", homePage.getTotalProducts(), equalTo("1"));
    }

    @Test
    public void addProductStockAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        int index = homePage.addProductStock();
        assertThat("Product stock could not be added", homePage.getTotalProducts(), equalTo(homePage.getStockSize(index)));
    }

    @Test
    public void decreaseQuantityAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        homePage.addProductsFromHomePage(3);
        WebElement product = homePage.toRemoveProduct();
        assertThat("Error removing product", homePage.getProductCount(product), equalTo("2"));
    }

    @Test
    public void emptyShoppingCartAuth(){
        LoginPage loginPage = homePage.clickOnLoginButton();
        homePage = loginPage.fillInForm("a", "a");
        homePage.addProductsFromHomePage(3);
        homePage.addProductsFromHomePage(3);
        homePage.toEmptyShoppingCart();
        assertThat("Error emptying the shopping cart", homePage.getTotalProducts(), equalTo("0"));
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("http://ecofoodmarket.herokuapp.com/");
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @After
    public void after() {
        webDriver.quit();
    }

}
