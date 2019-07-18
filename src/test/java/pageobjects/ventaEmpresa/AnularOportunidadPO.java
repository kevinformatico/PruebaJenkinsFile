package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class AnularOportunidadPO extends BasePage {
    public AnularOportunidadPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//form[contains(@name,'vm.anularOportunidadForm')]//div[.//label[contains(text(),'Motivo')] and contains(@class,'form-group')]")
    private WebElement inputMotivo;

    @FindBy(xpath = "//form[contains(@name,'vm.anularOportunidadForm')]//div[.//label[contains(text(),'Comentario')] and contains(@class,'form-group')]")
    private WebElement inputComentario;
    
    @FindBy(xpath = "//form[contains(@name,'vm.anularOportunidadForm')]//button[contains(@ng-click,'vm.anular')]") 
    private WebElement botonAnular;
    
    @FindBy(xpath = "//form[contains(@name,'vm.anularOportunidadForm')]//div[contains(@class,'bch-mensaje-empresas') and contains(@ng-show,'vm.anulacion')]//p") 
    private WebElement mensajeRespuestaAnulacion;

    //TODO: Crear funciones para insertar valores, anular y validar mensaje


}
