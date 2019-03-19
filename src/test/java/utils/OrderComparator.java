package utils;

import org.openqa.selenium.WebElement;
import pages.OrdersPage;

import java.util.Comparator;

public class OrderComparator implements Comparator<WebElement> {
    public int compare(WebElement w1, WebElement w2) {
        if(OrdersPage.getOrderNumber(w1) > OrdersPage.getOrderNumber(w2))
            return 1;
        else if(OrdersPage.getOrderNumber(w1) < OrdersPage.getOrderNumber(w2))
            return -1;
        return 0;
    }
}
