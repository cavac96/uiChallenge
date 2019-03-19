package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Paths;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends NavegationBar{

    @FindBy(xpath = "//ul[@class='product-category-list']/descendant::a")
    private List<WebElement> productCategories = new ArrayList<WebElement>();

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement shoppingCartTotal;

    private List<WebElement> products = new ArrayList<WebElement>();

    private WebElement product;

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addProductFromCategoryPage(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(productCategories);
        findProductCategory();
        findProductsPerCategory();
        int index = findProductIndex();
        product = findProduct(index);
        addProduct();
    }

    public void findProductCategory(){
        int index = getRandomIndex(productCategories.size());
        goToProductCategory(index);
    }

    public int getRandomIndex(int max){
        return Utils.generateRandomInt(0, max-1);
    }

    public void goToProductCategory(int index){
        clickAction(productCategories.get(index));
    }

    public void findProductsPerCategory(){
        By locator = By.xpath(Paths.PRODUCTS_PER_CATEGORY);
        products = webDriver.findElements(locator);
    }

    public int findProductIndex(){
        int index = getRandomIndex(products.size());
        goToProduct(index);
        return index;
    }

    public void goToProduct(int index){
        clickAction(products.get(index));
    }

    public WebElement findProduct(int index){
        return products.get(index);
    }

    public void addProduct(){
        WebElement addButton = findAddButton();
        moveToElement(addButton);
        waitForElementToBeClickable(addButton);
        addButton.click();
    }

    public WebElement findAddButton(){
        By addButtonLocator = By.xpath(Paths.ADD_BUTTON);
        return product.findElement(addButtonLocator);
    }

    public String getTotalProducts(){
        return shoppingCartTotal.getText();
    }

}
