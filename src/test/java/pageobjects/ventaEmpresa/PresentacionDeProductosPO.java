package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class PresentacionDeProductosPO extends BasePage {

    @FindBy(xpath = "//section[contains(@class,'view-ventas')]//div[contains(@class,'work-area__header')]//h3")
    private WebElement tituloPresentacionDeProductos;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]")
    private WebElement ResumenOportunidad; //Cuadro superior que incluye toda la informacion de la oportunidad

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Oportunidad en proceso')]]")
    private WebElement resumenOportunidadEnProceso; //Cuadro gris, lado izquierdo Oportunidad en proceso

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Solicitado')]]")
    private WebElement resumenCreadorYSolicitado;//Cuadro gris, lado derecho del resumen

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//a[contains(@ng-if,'vm.anular')]")
    private WebElement botonAnular;

    @FindBy(xpath = "//section[contains(@class,'view-ventas')]//div[contains(@class,'card-aside') and .//span[contains(text(),'Datos adicionales')]]//article[contains(@ng-click,'asideDatosAdicionales')]")
    private WebElement botonDesplegarDatosAdicionales;

    @FindBy(xpath = "//section[contains(@class,'view-ventas')]//div[contains(@class,'card-aside') and .//span[contains(text(),'Documentos adjuntos')]]//article[contains(@ng-click,'')]")
    private WebElement botonDesplegarDocumentosAdjuntos;

    @FindBy(xpath = "//section[contains(@class,'view-ventas')]//div[contains(@ng-if,'vm.solicitudProductoCargada')]")
    private WebElement componenteProductosAGestionar; // TODO: 2019-07-11 pgtoopx desglosar mas

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//p[contains(span,'ID oportunidad:')]")
    private WebElement idOportunidad;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Oportunidad en proceso')]]//li[.//span[contains(text(),'Creación:')]]//span[2]")
    private WebElement fechaYhoraDeCreacion;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Oportunidad en proceso')]]//li[.//span[contains(text(),'Productos')]]//span[2]")
    private WebElement productos;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Solicitado')]]//li[.//span[contains(text(),'Creador')]]//span[2]")
    private WebElement creador;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'resumen-producto') and not(contains(@class,'bloque col-2'))]//div[.//span[contains(text(),'Solicitado')]]//li[.//span[contains(text(),'Solicitado')]]//span[2]")
    private WebElement solicitado;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]/div[1]/span")
    private WebElement nombreProductoEnDesgloseSingleProduct;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]//li[contains(@class,'width') and contains(p,'Usuario')]/p[2]")
    private WebElement usuarioFromDesglose;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]//li[contains(@class,'width') and contains(p,'Área de usuario')]/p[2]")
    private WebElement areaDeUsuario;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]//li[contains(@class,'width') and contains(p,'de producto')]/p[2]")
    private WebElement noDeProducto;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]//li[contains(@class,'width') and contains(p,'Monto solicitado')]/p[2]")
    private WebElement montoSolicitado;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]/div[1]//li[contains(@class,'width') and contains(p,'Nombre tarea')]/p[2]")
    private WebElement nombreTarea;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]//li[contains(@ng-if,'requiereAdep')]//a[contains(span,'Copia banco')]")
    private WebElement contratoCopiaBanco;

    @FindBy(xpath = "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]//div[contains(@class,'desglose expandido')]//div[contains(@ng-repeat,'oportunidad.productos')]//li[contains(@ng-if,'requiereAdep')]//a[contains(span,'Copia cliente')]")
    private WebElement contratoCopiaCliente;



    public PresentacionDeProductosPO() {
        super(DriverFactory.getDriver());
    }


    /*
    * Getters
    * */
    public String getTituloPresentacionDeProductos(){
        return tituloPresentacionDeProductos.getText();
    }

    public String getIdOportunidad(){
        return idOportunidad.getText();
    }

    public String getDateCreacion(){
        return fechaYhoraDeCreacion.getText();
    }

    public String getProductos(){
        return productos.getText();
    }

    public String getCreador(){
        return creador.getText();
    }

    public String getSolicitado(){
        return solicitado.getText();
    }

    public String getNombreProductoDesgloseSingleProduct(){
        return nombreProductoEnDesgloseSingleProduct.getText();
    }

    public String getUsuarioFromDesglose(){
        return usuarioFromDesglose.getText();
    }

    public String getAreaDeUsurio(){
        return areaDeUsuario.getText();
    }

    public String getNoDeProducto(){
        return noDeProducto.getText();
    }

    public String getMontoSolicitado(){
        return montoSolicitado.getText();
    }

    public String getNombreTarea(){
        return nombreTarea.getText();
    }

    /*
    * Clicks
    * */

    public void clickContratoBanco(){
        contratoCopiaBanco.click();
    }

    public void clickContratoCliente(){
        contratoCopiaCliente.click();
    }

    public AnularOportunidadPO clickAnularOportunidad(){
        botonAnular.click();
        return new AnularOportunidadPO();
    }

    public DatosAdicionalesPO clickDatosAdicionales(){
        botonDesplegarDatosAdicionales.click();
        return new DatosAdicionalesPO();
    }

    public DocumentosAdjuntosPO clickDocumentosAdjuntos(){
        botonDesplegarDocumentosAdjuntos.click();
        return new DocumentosAdjuntosPO();
    }

    /*
    * Funcionalidades exclusivas
    * */

    public void extraerDatosPresentacionDelProducto(){
        waitUntilEscritorioComercialIsLoaded();
        System.out.println("Titulo: "+ getTituloPresentacionDeProductos());
        System.out.println("Id oportunidad: "+getIdOportunidad());
        System.out.println("Creación: "+getDateCreacion());
        System.out.println("Productos: "+ getProductos());
        System.out.println("Creador: "+ getCreador());
        System.out.println("Solicitado: " +getSolicitado());
        System.out.println("Desglose Productos ---------");
        System.out.println("Nombre Producto: "+getNombreProductoDesgloseSingleProduct());
        System.out.println("Usuario: "+getUsuarioFromDesglose());
        System.out.println("Area de usuario: "+getAreaDeUsurio());
        System.out.println("No de producto: "+getNoDeProducto());
        System.out.println("Monto solicitado: "+getMontoSolicitado());
        System.out.println("Nombre Tarea: "+ getNombreTarea());

        //clickAnularOportunidad();
        //clickDocumentosAdjuntos();
    }



}
