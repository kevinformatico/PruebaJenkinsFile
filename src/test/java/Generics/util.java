package Generics;

import Managers.driver.DriverFactory;
import io.cucumber.core.api.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.BasePage;

import javax.rmi.CORBA.Util;

public class util {
    public static void printCurrentThread(){
        System.out.println("Current Thread : " + Thread.currentThread().getId());
    }

    public static void takeAndEmbedScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public static void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean isVisible(By by){
        Boolean isVisible =  true; //asume que la pagina esta cargando
        try {
            isVisible = DriverFactory.getDriver().findElement(by).isDisplayed(); // si es true es decir que esta cargando
        }catch (NoSuchElementException e){
            isVisible=false; // si entra al catch quiere decir que no encontro el elemento, es decir ya cargo
        }
        return isVisible;
    }



    public static boolean isVisible(WebElement webElement){
        Boolean isVisible =  true; //asume que la pagina esta cargando
        try {
            isVisible = webElement.isDisplayed(); // si es true es decir que esta cargando
        }catch (NoSuchElementException e){
            isVisible=false; // si entra al catch quiere decir que no encontro el elemento, es decir ya cargo
        }
        return isVisible;
    }

    public static void waitUntilEscritorioComercialIsLoaded(){
        while (util.isVisible(By.id("loading-bar"))) {
            util.waitFor(1);
        }
    }

    public static void waitUntilElementIsPresent(WebDriverWait wait, WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilElementIsPresent(WebElement webElement, int timeOutSec){
        new WebDriverWait(DriverFactory.getDriver(), timeOutSec).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilElementIsPresent(WebElement webElement){
        new WebDriverWait(DriverFactory.getDriver(), 5).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void sleep(int timeOutSec){
        try {
            Thread.sleep(timeOutSec*1000);
        }catch (InterruptedException e){
            e.getMessage();
        }
    }

    public static String elimininarTildes(String cadena){
        return StringUtils.stripAccents(cadena);
    }

    public static boolean seExpande(Dimension dimAnterior, Dimension dimNueva){
        boolean seExpande = false;
        int widthDimAnterior = dimAnterior.getWidth();
        int heightDimAnterior = dimAnterior.getWidth();
        int widthDimNueva = dimNueva.getWidth();
        int heightDimNueva = dimNueva.getHeight();
        if(widthDimAnterior<widthDimNueva) seExpande = true;
        if(heightDimAnterior<heightDimNueva) seExpande =true;
        return seExpande;
    }

    public static void log (String mensaje){
        Logger log = LogManager.getLogger(util.class);
        log.debug(mensaje);
    }
}
