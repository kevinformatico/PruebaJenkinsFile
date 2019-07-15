package pageobjects;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Vista360ResumenEmpresaPO extends BasePage{

    public Vista360ResumenEmpresaPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(id = "v360_cabecera-empresa_inteligencia-empresa_garantias-empresa_enlace")
    private WebElement botonGarantiasEmpresa;

    @FindBy(xpath = "//ng-include[not(contains(@class,'hide'))]//button[contains(@ng-click,'linkContrar')]")
    private WebElement botonContratarProductos;


    public void clickBotonGarantiasEmpresa(){
        waitUntilEscritorioComercialIsLoaded();
        botonGarantiasEmpresa.click();
    }

    public void clickBotonContratarProductos(){
        waitUntilEscritorioComercialIsLoaded();
        botonContratarProductos.click();
    }
}
