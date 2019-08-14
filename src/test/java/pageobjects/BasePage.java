package pageobjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private static final int WAIT_TIMEOUT = 30;
    private static final int DEFAULT_TIMEOUT = 1;
    private static final int POLLING = 100;
    private Logger log = LogManager.getLogger(BasePage.class);
    private

    final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "loading-bar")
    private WebElement barraCargando;

    @FindBy(id = "loading")
    private WebElement paginaCargando;

    protected BasePage(WebDriver driver){
        this.driver=driver;
        log.debug("Configuración WebDriverWait: TimeOut: "+WAIT_TIMEOUT+", Polling: "+POLLING);
        this.wait= new WebDriverWait(driver, WAIT_TIMEOUT, POLLING);
        log.debug("Pagina "+this.getClass()+" se inicializo con un TimeOut de " + DEFAULT_TIMEOUT);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,DEFAULT_TIMEOUT), this);
    }

    protected BasePage(WebDriver driver, int timeOutSec){
        this.driver=driver;
        log.debug("Configuración WebDriverWait: TimeOut: "+timeOutSec+", Polling: "+POLLING);
        this.wait= new WebDriverWait(driver, timeOutSec, POLLING);
        log.debug("Pagina "+this.getClass()+" se inicializo con un TimeOut de " + DEFAULT_TIMEOUT);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_TIMEOUT), this);
    }

    protected BasePage(WebDriver driver, int timeOutSec, int pollingSec){
        this.driver=driver;
        log.debug("Configuración WebDriverWait: TimeOut: "+timeOutSec+", Polling: "+pollingSec);
        this.wait= new WebDriverWait(driver, timeOutSec, pollingSec);
        log.debug("Pagina "+this.getClass()+" se inicializo con un TimeOut de " + DEFAULT_TIMEOUT);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_TIMEOUT), this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement locator) {
        wait.until(ExpectedConditions.invisibilityOf(locator));
    }

    protected void waitForTextToDisappear(WebElement locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, text)));
    }

    protected boolean isVisible(WebElement webElement){
        boolean isVisible;
        try {
            return webElement.isDisplayed();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }catch (Exception e){
            log.error(e);
            return false;
        }
    }

    protected boolean isInvisible(WebElement element){
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e){
            return true;
        }
    }

    public void waitUntilEscritorioComercialIsLoaded() {
        while (isVisible(barraCargando)){};
    }

    protected void waitFor(int segundos){
        try {
            log.debug("Esperando por "+segundos+" segundos");
            log.warn("Usar con precaución!!");
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e){
            log.error(e);
        }
    }

    protected void scrollEndPage() {
        try {
            log.debug("Realizando un scroll hasta el final de la pagina");
            log.warn("Usar con precaución!!");
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e){
            log.error(e);
        }
    }

    public byte[] takeScreenshot(){
         return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    protected WebElement getElementFrom(By locator){
        return getDriver().findElement(locator);
    }

}
