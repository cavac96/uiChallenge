package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.OrderComparator;
import utils.Paths;

import java.util.*;

public class OrdersPage extends NavegationBar{
    @FindBy(xpath = "//tbody[@id='orders']/tr")
    static private List<WebElement> orderList;

    WebElement order;
    WebElement orderModal;
    List<WebElement> productList;

    public OrdersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyOrderDetails(){
        waitForInvisiblePageLoader();
        waitForVisibilityOfElements(orderList);
        getOrder();
        WebElement detailsButton = getDetailsButton();
        waitForElementToBeClickable(detailsButton);
        detailsButton.click();
        getOrderModal();
        getOrderProducts();
    }

    public void getOrder(){
        //int index = getLatsOrderIndex();
        //sortList();
        int index = orderList.size()-1;
        order = orderList.get(index);
    }

    public void sortList(){
        Collections.sort(orderList, new OrderComparator());
    }

    public WebElement getDetailsButton(){
        By detailsButtonLocator = By.xpath(Paths.ORDER_DETAILS_BUTTON);
        return order.findElement(detailsButtonLocator);
    }

    public static int getOrderNumber(WebElement orderToSort){
        int index = orderList.indexOf(orderToSort);
        By numberLocator = By.xpath("//tbody[@id='orders']/tr["+(index+1)+"]/descendant::td[1]");
        //By numberLocator = By.xpath(Paths.ORDER_NUMBER);
        WebElement orderNumber = orderToSort.findElement(numberLocator);
        return Integer.parseInt(orderNumber.getText());
    }

    public void getOrderModal(){
        By modalLocator = By.xpath(Paths.ORDER_MODAL);
        waitForVisibilityOfElementLocated(modalLocator);
        orderModal = webDriver.findElement(modalLocator);
    }

    public void getOrderProducts(){
        By productsLocator = By.xpath(Paths.ORDER_PRODUCTS_MODAL);
        productList = orderModal.findElements(productsLocator);
    }

    public List<String> getOrderProductsName(){
        waitForInvisiblePageLoader();
        getOrderModal();
        getOrderProducts();
        List<String> orderProductsList = new ArrayList<String>();
        for(WebElement product: productList){
            waitForVisibilityOfElement(product);
            String name = getProductName(product);
            orderProductsList.add(name);
        }
        return orderProductsList;
    }

    public String getProductName(WebElement product){
        By productNameLocator = By.xpath(Paths.ORDER_PRODUCT_NAME_MODAL);
        return product.findElement(productNameLocator).getText();
    }

    public String getProductAmount(WebElement product){
        By productAmountLocator = By.xpath(Paths.ORDER_PRODUCT_AMOUNT_MODAL);
        return product.findElement(productAmountLocator).getText();
    }

    public void acceptModal(){
        getOrderModal();
        WebElement button = getModalButton();
        button.click();
    }

    public WebElement getModalButton(){
        By button = By.xpath(Paths.ORDER_BUTTON_MODAL);
        return orderModal.findElement(button);
    }

    public int getLatsOrderIndex(){
        SortedSet<Integer>  sortedOrderIndexes = new TreeSet<Integer>();
        for(WebElement order: orderList){
            System.out.println(getOrderNumber(order));
            sortedOrderIndexes.add(getOrderNumberNew(order));
        }
        return sortedOrderIndexes.last();
    }

    public static int getOrderNumberNew(WebElement order){
        By numberLocator = By.xpath("/descendant::td[1]");
        //By numberLocator = By.xpath(Paths.ORDER_NUMBER);
        WebElement orderNumber = order.findElement(numberLocator);
        return Integer.parseInt(orderNumber.getText());
    }

    public int getOrderNew(int index){
        By numberLocator = By.xpath("/descendant::td[1]");
        //By numberLocator = By.xpath(Paths.ORDER_NUMBER);
        WebElement orderNumber = order.findElement(numberLocator);
        return Integer.parseInt(orderNumber.getText());
    }
}
