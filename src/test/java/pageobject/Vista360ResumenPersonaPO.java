package pageobject;

import driver.DriverFactory;
import driver.util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class Vista360ResumenPersonaPO extends LoadableComponent<Vista360ResumenPersonaPO> {

    public Vista360ResumenPersonaPO() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(id = "v360_titulo")
    WebElement titulo;

    @FindBy(xpath = "/html/body/div[1]/div[2]/section/section/ng-include/div[2]/label")
    WebElement cambiarEmpresaPersona;

    @FindBy(id = "v360_cabecera-persona_inteligencia-persona_garantias-persona_enlace")
    WebElement botonGarantiasPersona;

    public void clickCambiarEmpresaPersona() {
        this.get();
        util.waitUntilElementIsPresent(cambiarEmpresaPersona);
        cambiarEmpresaPersona.click();
    }
    public void clickBoxGarantias() {
        this.get();
        util.waitUntilElementIsPresent(botonGarantiasPersona);
        botonGarantiasPersona.click();
    }

    //Metodos para validar la carga de la pagina 
    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        util.waitUntilEscritorioComercialIsLoaded();
    }
}
