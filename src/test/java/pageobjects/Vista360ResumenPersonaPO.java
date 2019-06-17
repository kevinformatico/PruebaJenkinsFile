package pageobjects;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Vista360ResumenPersonaPO extends BasePage {

    public Vista360ResumenPersonaPO() {
        super(DriverFactory.getDriver());
    }

    @FindBy(id = "v360_titulo")
    WebElement titulo;

    @FindBy(xpath = "/html/body/div[1]/div[2]/section/section/ng-include/div[2]/label")
    private
    WebElement cambiarEmpresaPersona;

    @FindBy(id = "v360_cabecera-persona_inteligencia-persona_garantias-persona_enlace")
    private
    WebElement botonGarantiasPersona;

    public void clickCambiarEmpresaPersona() {
        waitForElementToAppear(cambiarEmpresaPersona);
        cambiarEmpresaPersona.click();
    }
    public void clickBoxGarantias() {
        waitUntilEscritorioComercialIsLoaded();
        waitForElementToAppear(botonGarantiasPersona);
        botonGarantiasPersona.click();
    }
}
