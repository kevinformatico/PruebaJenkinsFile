package pageobjects.ventaEmpresa;

import driver.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.List;

public class ConfiguracionDeProductosPO extends BasePage {

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    String familiasDisponiblesXpath = "//cdn-venta-taller-directive//div[contains(@ng-repeat,'listadoFamilias')]";

    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.showCarro')]")
    private WebElement contenedorFamilias;

    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-if,'vm.showCarro')]")
    private WebElement contenedorProductosDeLaOportunidad; //TODO: fix

    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//cdn-venta-taller-directive//div[contains(@ng-repeat,'listadoFamilias')]")
    private List<WebElement> familiasDisponibles;






    public void clickLinkFacilitadoresYPreferenciasDeAtencion(){
        waitForElementToAppear(linkFacilitadoresYPreferenciasDeAtencion);
        linkFacilitadoresYPreferenciasDeAtencion.click();
    }

    public int pickerIndexFamilia(String nombreFamilia){
        waitUntilEscritorioComercialIsLoaded();
        int i;
        for (i=0;i<familiasDisponibles.size();i++){
            if(familiasDisponibles.get(i).getText().contains(nombreFamilia)) break;
        }
        return i+1;
    }

    public void expanderFamilia(String nombreFamilia){
        pickerIndexFamilia(nombreFamilia);
        getDriver().findElement(By.xpath("("+familiasDisponiblesXpath+")["+pickerIndexFamilia(nombreFamilia)+"]"
                +"//div[contains(@class,'actions')]/a")).click();
    }
}
