package pageobject;

import driver.DriverFactory;
import driver.util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EscritorioComercialPO extends LoadableComponent<EscritorioComercialPO> {

    WebDriverWait wait;

    @FindBy(id="rut")
    WebElement inputRut;
    @FindBy(name="searchButton")
    WebElement searchButton;

    public EscritorioComercialPO() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        this.wait = new WebDriverWait(DriverFactory.getDriver(), 20);
    }

    public void buscarPorRut(String rut){
        util.waitUntilElementIsPresent(inputRut);
        inputRut.sendKeys(rut);
        searchButton.click();
    }



    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        util.waitUntilEscritorioComercialIsLoaded();
        //TODO: Validar el titulo de la pagina y la url
    }





}

