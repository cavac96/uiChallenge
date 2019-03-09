package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends BasePage{

    @FindBy(xpath = "//ul[@class='product-category-list']/descendant::a")
    private List<WebElement> productCategories = new ArrayList<WebElement>();

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement shoppingCartTotal;

    private List<WebElement> products = new ArrayList<WebElement>();

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addProductFromCategoryPage(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(productCategories);
        findProductCategory();
        findProductsPerCategory();
        int productIndex = findProduct();
        addProduct(productIndex);
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
        By locator = By.xpath("//div[@id='products_by_category']/child::div");
        products = webDriver.findElements(locator);
    }

    public int findProduct(){
        int index = getRandomIndex(products.size());
        goToProduct(index);
        return index;
    }

    public void goToProduct(int index){
        clickAction(products.get(index));
    }

    public void addProduct(int index){
        WebElement addButton = findAddButton(index);
        moveToElement(addButton);
        waitForElementToBeClickable(addButton);
        addButton.click();
    }

    public WebElement findAddButton(int index){
        By addButtonLocator = By.xpath("//div[@id='products_by_category']/child::div["+(index+1)+"]/descendant::a[@title='Agregar']");
        return webDriver.findElement(addButtonLocator);
    }

    public String getTotalProducts(){
        return shoppingCartTotal.getText();
    }

}
