package pageobjects;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Vista360ResumenEmpresaPO extends BasePage{

    public Vista360ResumenEmpresaPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(id = "v360_cabecera-empresa_inteligencia-empresa_garantias-empresa_enlace")
    private
    WebElement botonGarantiasEmpresa;



    public void clickBotonGarantiasEmpresa(){
        waitUntilEscritorioComercialIsLoaded();
        botonGarantiasEmpresa.click();
    }
}
