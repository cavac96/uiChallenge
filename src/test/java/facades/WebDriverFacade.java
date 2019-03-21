package facades;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverFacade {
    private static WebDriver webDriver;
    private static int WAIT_SECONDS = 5;

    public WebDriverFacade(WebDriver webDriver){
        this.webDriver =  webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void start(){
        webDriver.get("http://ecofoodmarket.herokuapp.com/");
    }

    public void quit(){
        webDriver.quit();
    }


    public WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForVisibilityOfElementLocated(By location) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(location));
    }

    public List<WebElement> waitForVisibilityOfElements(List<WebElement> webElements) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public WebElement waitForElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitForElementLocatedToBeClickable(By location) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(location));
    }

    public void waitForInvisiblePageLoader() {
        //By loader = By.xpath(Paths.LOADER);
        By loader = By.xpath("//div[@class='loader']");
        new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void waitForAlert() {
        new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.alertIsPresent());
    }

    public Alert switchToAlert(){
        return webDriver.switchTo().alert();
    }

    public void acceptAlert(){
        waitForAlert();
        webDriver.switchTo().alert().accept();
    }

    public void clickAction(WebElement elementToClick){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(elementToClick).click().perform();
    }

    public void moveToElement(WebElement element){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).perform();
    }

    public WebElement findElementByLocator(By locator){
        return webDriver.findElement(locator);
    }

    public List<WebElement> findElementsByLocator(By locator){
        return webDriver.findElements(locator);
    }
}
