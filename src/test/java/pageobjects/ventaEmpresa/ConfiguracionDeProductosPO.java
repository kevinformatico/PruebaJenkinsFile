package pageobjects.ventaEmpresa;

import driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.List;
import java.util.Map;

public class ConfiguracionDeProductosPO extends BasePage {

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    String xpathToGetSelectProductoFromFamilia="//cdn-detalle-familia-directive//div[label[contains(text(),'Producto')]]";
    String botonAgregarAOportunidad = "//button[contains(@class,'agregar-producto') and contains(text(),'Agregar a oportunidad')]";
    String familia;


    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.showCarro')]")
    private WebElement contenedorFamilias;

    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-if,'vm.showCarro')]")
    private WebElement contenedorProductosDeLaOportunidad; //TODO: fix

    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//cdn-venta-taller-directive//div[contains(@ng-repeat,'listadoFamilias')]")
    private List<WebElement> familiasDisponibles;



    /*
    * Funcionalidades de la pagina
    * */

    public void expanderFamilia(String nombreFamilia){
        this.familia=nombreFamilia;
        waitUntilEscritorioComercialIsLoaded();
        getDriver().findElement(By.xpath(crearXpathFamilia(nombreFamilia)+"//div[contains(@class,'actions')]/a")).click();

    }

    public void seleccionoElProducto(String nombreProducto){
        pickValorSelect(crearXpathFamilia(this.familia)+xpathToGetSelectProductoFromFamilia,nombreProducto);
        waitUntilEscritorioComercialIsLoaded();
    }

    public void ingresarValorAlProducto(String clave, String valor){
        ingresarValorEnInput(clave, valor);
    }

    public void ingresarValoresAlProducto(DataTable datos){
        List<Map<String,String>> tablaProducto = datos.asMaps();
        for (Map<String, String> fila :tablaProducto){
            ingresarValorEnInput(fila.get("clave"),fila.get("valor"));
        }
    }

    public void clickLinkFacilitadoresYPreferenciasDeAtencion(){
        waitForElementToAppear(linkFacilitadoresYPreferenciasDeAtencion);
        linkFacilitadoresYPreferenciasDeAtencion.click();
    }

    public void continuarAPresentacionDelProducto(){
        waitUntilEscritorioComercialIsLoaded();
        getDriver().findElement(By.xpath("//div[contains(@ng-if,'vm.showCarro')]//a[contains(text(),'Continuar')]")).click();
    }


    /*
    * Utilidades
    * */
    public String crearXpathFamilia(String nombreFamilia){
        return "//cdn-venta-taller-directive[contains(@on-agregar,'agregarProductoCarro')]//div[contains(@ng-repeat,'listadoFamilias') and .//div[contains(h5,'"+nombreFamilia+"')]]";
    }

    public void pickValorSelect(String select, String valor){
        String desplegarLista="//i[contains(@ng-click,'event')]";
        String seleccion= "//li//a[contains(span,'"+valor+"')]";
        getDriver().findElement(By.xpath(select+desplegarLista)).click();
        getDriver().findElement(By.xpath(select+seleccion)).click();
    }

    public void clickAgregarAOportunidad(){
        getDriver().findElement(By.xpath(crearXpathFamilia(familia)+botonAgregarAOportunidad)).click();
    }

    public void ingresarValorEnInput(String campo, String valor){
        String seccionProducto=crearXpathFamilia(familia);
        String objFiltro="//div[contains(label, '"+campo+"')]";
        String desplegarLista="//i[contains(@ng-click,'event')]";
        String seleccion= "//li//a[contains(span,'"+valor+"')]";
        if(getDriver().findElement(By.xpath(seccionProducto+objFiltro+"//input")).getAttribute("class").contains("select")){
            getDriver().findElement(By.xpath(seccionProducto+objFiltro+desplegarLista)).click();
            getDriver().findElement(By.xpath(seleccion)).click();
        }else{
            getDriver().findElement(By.xpath(seccionProducto+objFiltro+"//input")).sendKeys(valor);
        }
    }

}
