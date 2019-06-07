package pageobject;

import driver.DriverFactory;
import Generics.ManejadorTablaFrontEnd;
import Generics.util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class DetalleGarantiasPO extends LoadableComponent<DetalleGarantiasPO> {

    @FindBy(xpath = "//table[@class='table tabla-interactiva']")
    WebElement tabla;

    public DetalleGarantiasPO() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    public void imprimirTablaCompleta(){
        util.waitUntilElementIsPresent(tabla, 10);
        System.out.println("Imprimiendo tabla");
        System.out.println("Tabla: "+ ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tabla));
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
    }
}

