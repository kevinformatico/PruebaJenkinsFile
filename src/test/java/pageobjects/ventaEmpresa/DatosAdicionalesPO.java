package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class DatosAdicionalesPO extends BasePage {
    public DatosAdicionalesPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//form[contains(@name,'productoForm')]//div[contains(@class,'modal-header')]//h3[contains(@class,'modal-title')]")
    private WebElement tituloDatosAdicionales;

    @FindBy(xpath = "//form[contains(@name,'productoForm')]//div[contains(@class,'modal-header')]//button[contains(@ng-click,'vm.done')]")
    private WebElement botonCerrar;

    @FindBy(xpath = "//form[contains(@name,'productoForm')]//div[contains(@class,'modal-body aside-venta')]//div[contains(@class,'caja-resumen-inicio')]//div[contains(p,'ID oportunidad')]//p[2]")
    private WebElement idOportunidad;

    @FindBy(xpath = "//form[contains(@name,'productoForm')]//div[contains(@class,'modal-body aside-venta')]//div[contains(label,'Sucursal') and contains(@class,'form-group')]")
    private WebElement inputSucursal;

    /*
    * Getters
    * */

   public String getTituloDatosAdicionales(){
       return tituloDatosAdicionales.getText();
   }

   public String getIdOportunidad(){
       return idOportunidad.getText();
   }

   /*
   * Clicks
   * */

   public void clickBotonCerrar(){
       botonCerrar.click();
   }

    public void probarDatosAdicionales(){
        waitUntilEscritorioComercialIsLoaded();
    }
}
