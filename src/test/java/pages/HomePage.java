package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@href=\"/login/\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@class='header-login']/\n" +
            "descendant::a[@href='/logout/']/preceding-sibling::a")
    private WebElement welcomeButton;

    @FindBy(xpath = "//div[@id='products-carousel']/div")
    private List<WebElement> products = new ArrayList<WebElement>();

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement shoppingCartTotal;

    @FindBy(xpath = "//ul[@class='nav-menu']/descendant::a[contains(text(),'CATÁLOGO')]")
    private WebElement catalogButton;

    List <WebElement> addedProducts = new ArrayList<WebElement>();

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationPage clickOnRegisterButton() {
        /*WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        By pageLoader = By.xpath("//div[@class='loader']");
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(pageLoader));
        registerButton = waitForElementToBeClickable(registerButton);
        registerButton.click();
        */
        //registerButton.findElement(By.xpath("//a[contains(text(),'Registrarse')]")).click();
        //webDriver.findElement(By.linkText("Registrarse")).click();

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
        //waitForInvisiblePageLoader();
        waitForElementToBeClickable(logoutButton);
        waitForInvisiblePageLoader();
        logoutButton.click();
        return PageFactory.initElements(webDriver, this.getClass());
    }

    public void addProductsFromHomePage() {
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        addProduct(index);
    }

    public void addProductsFromHomePage(int times) {
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        for (int i=0; i<times;i++){
            addProduct(index);
        }
    }

    public int getRandomProductIndex(){
        return Utils.generateRandomInt(0, products.size() - 1);
    }

    public void goToProduct(int index){
        clickAction(products.get(index));
    }

    public WebElement findAddProductButton(int index){
        By productLocator = By.xpath("//div[@id='products-carousel']/div[" + (index+1) + "]/descendant::a[@title='Agregar']");
        return webDriver.findElement(productLocator);
    }

    public int findProductIndex(){
        int index = getRandomProductIndex();
        goToProduct(index);
        return index;
    }

    public void addProduct(int index){
        WebElement addButton = findAddProductButton(index);
        moveToElement(addButton);
//webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitForElementToBeClickable(addButton);
        addButton.click();
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

    public int addProductStock(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        WebElement addButton = findAddProductButton(index);
        moveToElement(addButton);
        waitForElementToBeClickable(addButton);
        int stockSize = Integer.parseInt(getStockSize(index));
        for(int i = 0; i <stockSize; i++){
            addButton.click();
        }
        return index;
    }

    public String getStockSize(int index){
        By quantityLocation = By.xpath(".//div[@class='miso-prd-total']");
        return products.get(index).findElement(quantityLocation).getText();
    }

    public WebElement toRemoveProduct(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        findAddedProducts();
        int index = findAddedProductIndex();
        WebElement product = findAddedProduct(index);
        removeProduct(product);
        return product;
    }

    public void findAddedProducts(){
        By addedProductsLocator = By.xpath("//div[@id='products-carousel']/child::div[div[@class='miso-prd-qty' and text()>0]]");
        addedProducts = webDriver.findElements(addedProductsLocator);
    }

    public int findAddedProductIndex(){
        int index = getRandomAddedProductIndex();
        goToAddedProduct(index);
        return index;
    }

    public void goToAddedProduct(int index){
        clickAction(addedProducts.get(index));
    }

    public void removeProduct(WebElement product){
        WebElement removeButton = findRemoveProductButton(product);
        moveToElement(removeButton);
        waitForElementToBeClickable(removeButton);
        removeButton.click();
    }

    public WebElement findAddedProduct(int index){
        By productLocator = By.xpath("//div[@id='products-carousel']/child::div[div[@class='miso-prd-qty' and text()>0]]["+(index+1)+"]");
        return webDriver.findElement(productLocator);
    }

    public WebElement findRemoveProductButton(WebElement product){
        By productLocator = By.xpath(".//descendant::a[@title='Remover']");
        return product.findElement(productLocator);
    }

    public int getRandomAddedProductIndex(){
        return Utils.generateRandomInt(0, addedProducts.size() - 1);
    }

    public String getProductCount(WebElement product){
        By productCountLocation = By.xpath(".//div[@class='miso-prd-qty']");
        System.out.println(product.findElement(productCountLocation).getText());
        return product.findElement(productCountLocation).getText();
    }

    public void toEmptyShoppingCart(){
        findAddedProducts();
        WebElement removeAllButton;
        for(WebElement product: addedProducts){
            waitForVisibilityOfElement(product);
            removeAllButton = findRemoveAllProductButton(product);
            moveToElement(removeAllButton);
            waitForElementToBeClickable(removeAllButton);
            removeAllButton.click();
            System.out.println("x");
        }
    }

    public WebElement findRemoveAllProductButton(WebElement product){
        By productLocator = By.xpath(".//descendant::a[@title='Limpiar']");
        return product.findElement(productLocator);
    }
}