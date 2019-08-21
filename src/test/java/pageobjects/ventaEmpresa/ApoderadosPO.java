package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;
import support.ui.elements.Select;

import java.util.List;

public class ApoderadosPO extends BasePage {

    Logger log = Logger.getLogger(ApoderadosPO.class);
    final String FORM_APODERADOS="//div[contains(@class,'modal-body')]//div[contains(@ng-if,'vm.modoEdicion')]/form";
    final String SELECTOR_REPRESENTES_LIST=FORM_APODERADOS+"/div[contains(@ng-repeat,'representante')]";
    final String SELECTOR_REPRESENTES_BY_INDEX=FORM_APODERADOS+"/div[contains(@ng-repeat,'representante')][%s]";

    final String SELECTOR_INPUT_FORMULARIO=".//div[label='%s']";

    public ApoderadosPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//div[contains(@class,'modal-header')]//h3[contains(@class,'modal-title')]")
    private WebElement titulo;

    @FindBy(xpath = "//div[contains(@class,'caja-resumen-inicio')]//div[p[contains(text(),'Tipo de producto')]]/p[2]")
    private WebElement tipoDeProducto;
    
    @FindBy(xpath = "//div[contains(@class,'modal-body')]//div[contains(@ng-if,'vm.modoEdicion')]/form") 
    private WebElement formularioApoderados;

    @FindBy(xpath = "//div[contains(@class,'modal-body')]//div[contains(@ng-if,'vm.modoEdicion')]/form/div[contains(@ng-repeat,'vm.representantes')]")
    private List<WebElement> listadoApoderados;

    @FindBy(xpath = "//div[contains(@class,'modal-footer')]//button['Guardar']")
    private WebElement btnGuardar;
    
    @FindBy(xpath = "//button[contains(@class,'close-modal')]") 
    private WebElement btnCerrarModal;

    public void cerrar(){
        btnCerrarModal.click();
    }

    public void ingresarApoderado(String rut){
        waitUntilEscritorioComercialIsLoaded();
        WebElement contenedorApoderado = formularioApoderados.findElement(By.xpath("./div[contains(@ng-repeat,'representante')]"));
        WebElement inputRut = contenedorApoderado.findElement(By.xpath(".//div[contains(@class,'form-group')]/input"));
        inputRut.sendKeys(rut);
        clickBotonBuscarDeContenedorApoderado(contenedorApoderado);
        new Select(contenedorApoderado.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,"Dirección")))).selectFirstValue();
        new Select(contenedorApoderado.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,"Teléfono")))).selectFirstValue();
        waitUntilEscritorioComercialIsLoaded();
        btnGuardar.click();
    }

    public void clickBotonBuscarDeContenedorApoderado(WebElement contenedor){
        waitUntilEscritorioComercialIsLoaded();
        contenedor.findElement(By.id("buscar_0")).click();
    }

}
