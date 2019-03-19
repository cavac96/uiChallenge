package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Paths;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends NavegationBar {

    @FindBy(xpath = "//div[@id='products-carousel']/div")
    private List<WebElement> products = new ArrayList<WebElement>();

    List <WebElement> addedProducts = new ArrayList<WebElement>();
    private WebElement productCard;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addProductsFromHomePage(int times) {
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        for (int i=0; i<times;i++){
            addProduct(index);
        }
    }

    public int findProductIndex(){
        int index = getRandomProductIndex();
        goToProduct(index);
        return index;
    }

    public void addProduct(int index){
        productCard = getProductCard(index);
        WebElement addButton = findAddProductButton();
        moveToElement(addButton);
        waitForElementToBeClickable(addButton);
        addButton.click();
    }

    public WebElement getProductCard(int index){
        return products.get(index);
    }

    public WebElement findAddProductButton(){
        By productLocator = By.xpath(".//descendant::a[@title='Agregar']");
        return productCard.findElement(productLocator);
    }

    public int getRandomProductIndex(){
        return Utils.generateRandomInt(0, products.size() - 1);
    }

    public void goToProduct(int index){
        clickAction(products.get(index));
    }

    public int addProductStock(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        productCard = getProductCard(index);
        WebElement addButton = findAddProductButton();
        waitForVisibilityOfElement(addButton);
        moveToElement(addButton);
        waitForElementToBeClickable(addButton);
        int stockSize = Integer.parseInt(getStockSize(index));
        for(int i = 0; i <stockSize; i++){
            addButton.click();
        }
        return index;
    }

    public String getStockSize(int index){
        By quantityLocation = By.xpath(Paths.STOCK_SIZE);
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
        By addedProductsLocator = By.xpath(Paths.ADDED_PRODUCTS);
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
        return products.get(index);
    }

    public WebElement findRemoveProductButton(WebElement product){
        By productLocator = By.xpath(Paths.REMOVE_BUTTON);
        return product.findElement(productLocator);
    }

    public int getRandomAddedProductIndex(){
        return Utils.generateRandomInt(0, addedProducts.size() - 1);
    }

    public String getProductCount(WebElement product){
        By productCountLocation = By.xpath(Paths.PRODUCT_COUNT);
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
        }
    }

    public WebElement findRemoveAllProductButton(WebElement product){
        By clearButtonLocator = By.xpath(Paths.CLEAR_BUTTON);
        return product.findElement(clearButtonLocator);
    }

    public void verifyProductDetails(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(products);
        int index = findProductIndex();
        productCard = getProductCard(index);
        openProductDetail();
    }

    public void openProductDetail(){
        WebElement detailsButton = findDetailsButton();
        moveToElement(detailsButton);
        waitForElementToBeClickable(detailsButton);
        detailsButton.click();
    }

    public WebElement findDetailsButton(){
        By detailsButtonLocator = By.xpath(Paths.DETAILS_BUTTON);
        return productCard.findElement(detailsButtonLocator);
    }

    public WebElement getProductDetailModalContent(){
        By productDetail = By.xpath(Paths.DETAILS_MODAL);
        return webDriver.findElement(productDetail);
    }

    public String getProductDetailsTitle(){
        WebElement productDetailsModal = getProductDetailModalContent();
        By productDetailTitle = By.xpath(Paths.DETAILS_MODAL_TITLE);
        waitForInvisiblePageLoader();
        waitForVisibilityOfElementLocated(productDetailTitle);
        return productDetailsModal.findElement(productDetailTitle).getText();
    }

    public String getProductName(){
        By productNameLocator = By.xpath(Paths.PRODUCT_NAME);
        return productCard.findElement(productNameLocator).getText();
    }

    public List<String> getAddedProductsName(){
        List<String> addedProductsList = new ArrayList<String>();
        findAddedProducts();
        for(WebElement product: addedProducts){
            String name = getProductName(product);
            addedProductsList.add(name);
        }
        return addedProductsList;
    }

    public String getProductName(WebElement product){
        By productNameLocator = By.xpath(Paths.PRODUCT_NAME);
        return product.findElement(productNameLocator).getText();
    }

    public String getProductAmount(WebElement product){
        By productAmountLocator = By.xpath(Paths.PRODUCT_COUNT);
        return product.findElement(productAmountLocator).getText();
    }
}