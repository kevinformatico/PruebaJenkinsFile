package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
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


    final String XPATH_PARA_FLECHA_DESPLEGAR_FAMILIA = "//div[contains(@class,'actions')]/a[contains(@ng-click,'productos')]";

    final String XPATH_PARA_CARRITO = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]//div[contains(@class,'body')]//div[contains(@ng-repeat,'compraActiva')]//div[contains(p,'Línea de Crédito Privada')]";


    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.showCarro')]")
    private WebElement contenedorFamilias;

    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//cdn-venta-taller-directive//div[contains(@ng-repeat,'listadoFamilias')]")
    private List<WebElement> familiasDisponibles;

    @FindBy(xpath = "//div[contains(@ng-if,'vm.showCarro')]//a[contains(text(),'Continuar')]")
    private WebElement botonContinuarAPresentacionDelProducto;

    private WebElement formularioFamilia;


    /*
    * Funcionalidades de la pagina
    * */

    public void expanderFamilia(String nombreFamilia){
        this.familia=nombreFamilia;
        waitUntilEscritorioComercialIsLoaded();
        getDriver().findElement(By.xpath(crearXpathFamilia(nombreFamilia)+XPATH_PARA_FLECHA_DESPLEGAR_FAMILIA)).click();
    }

    public void seleccionoElProducto(String nombreProducto){
        pickValorSelect(crearXpathFamilia(this.familia)+xpathToGetSelectProductoFromFamilia,nombreProducto);
        //WebElement formFamilia = getDriver().findElement(By.xpath(crearXpathFamilia(this.familia)));
        //ingresarValorEnInput(formFamilia, "Producto", nombreProducto);
        waitUntilEscritorioComercialIsLoaded();
    }

    public void ingresarValorAlProducto(String clave, String valor){
        WebElement formularioFamilia=getDriver().findElement(By.xpath(crearXpathFamilia(familia)));
        ingresarValorEnInput(formularioFamilia, clave, valor);
    }

    public void ingresarValoresAlProducto(DataTable datos){
        formularioFamilia= getDriver().findElement(By.xpath(crearXpathFamilia(familia)));
        List<Map<String,String>> tablaProducto = datos.asMaps();
        for (Map<String, String> fila :tablaProducto){
                ingresarValorEnInput(fila.get("clave"), fila.get("valor"));
        }
    }

    public void clickLinkFacilitadoresYPreferenciasDeAtencion(){
        waitForElementToAppear(linkFacilitadoresYPreferenciasDeAtencion);
        linkFacilitadoresYPreferenciasDeAtencion.click();
    }

    public void continuarAPresentacionDelProducto(){
        waitUntilEscritorioComercialIsLoaded();
        botonContinuarAPresentacionDelProducto.click();
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
        String objFiltro="//div[label='"+campo+"']";
        String desplegarLista="//i[contains(@ng-click,'event')]";
        String seleccion= "//li//a[contains(span,'"+valor+"')]";
        if(valor.equals("r")){
            seleccion = "//li//a[1]";
        }
        if(getDriver().findElement(By.xpath(seccionProducto+objFiltro+"//input")).getAttribute("class").contains("select")){
            getDriver().findElement(By.xpath(seccionProducto+objFiltro+desplegarLista)).click();
            getDriver().findElement(By.xpath(seleccion)).click();
        }else{
            getDriver().findElement(By.xpath(seccionProducto+objFiltro+"//input")).sendKeys(valor);
        }
    }

    public void ingresarValorEnInput(WebElement formulario, String campo, String valor){
        WebElement objFormulario= formulario.findElement(By.xpath("//div[contains(label, '"+campo+"')]"));
        System.out.println("xpath Objeto "+campo);
        System.out.println(objFormulario);
        if(objFormulario.findElement(By.xpath("/div")).getAttribute("class").contains("select")){
            objFormulario.findElement(By.xpath("//i[contains(@ng-click,'event')]")).click();
            objFormulario.findElement(By.xpath("//li//a[contains(span,'"+valor+"')]")).click();
        }else{
            objFormulario.findElement(By.xpath("//input")).sendKeys(valor);
        }

    }

    public boolean existeProductoEnElCarro(String nombreProducto){
        WebElement producto = getDriver().findElement(By.xpath("//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]//div[contains(@class,'body')]//div[contains(@ng-repeat,'compraActiva')]//div[contains(p,'"+nombreProducto+"')]"));
        return isVisible(producto);
    }

    public String addSpaceToFormText(String valor){
        return " "+valor.trim()+" ";
    }



}
