package stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.DriverFactory;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pageobjects.*;
import pageobjects.ventaEmpresa.ConfiguracionDeProductosPO;
import pageobjects.ventaEmpresa.DatosAdicionalesPO;
import pageobjects.ventaEmpresa.DocumentosAdjuntosPO;
import pageobjects.ventaEmpresa.PresentacionDeProductosPO;

import java.util.ArrayList;

public class VentaEmpresaDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ConfiguracionDeProductosPO configuracionDeProductosPO;
    PresentacionDeProductosPO presentacionDeProductosPO;
    DatosAdicionalesPO datosAdicionalesPO;
    DocumentosAdjuntosPO documentosAdjuntosPO;
    ArrayList<byte[]> screenshotList;
    String producto;

    public VentaEmpresaDefinition(SharedDriver driver,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              ConfiguracionDeProductosPO configuracionDeProductosPO,
                              PresentacionDeProductosPO presentacionDeProductosPO,
                              DatosAdicionalesPO datosAdicionalesPO,
                              DocumentosAdjuntosPO documentosAdjuntosPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
        this.screenshotList=screenshotList;
        this.paginaInicioPO= paginaInicioPO;
        this.escritorioComercialPO= escritorioComercialPO;
        this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
        this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
        this.configuracionDeProductosPO=configuracionDeProductosPO;
        this.presentacionDeProductosPO=presentacionDeProductosPO;
        this.datosAdicionalesPO=datosAdicionalesPO;
        this.documentosAdjuntosPO=documentosAdjuntosPO;
    }

    @Given("voy a contratar un producto")
    public void voy_a_contratar_un_producto(){
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
    }

    @Given("despliego familia {string}")
    public void despliego_familia_string(String nombreFamilia){
        configuracionDeProductosPO.expanderFamilia(nombreFamilia);
    }



    @Given("voy a contratar el producto {string} con los siguientes valores:")
    public void voy_a_contratar_el_producto_string (String producto, DataTable tabla){
        this.producto=producto;
        configuracionDeProductosPO.seleccionoElProducto(producto);
        configuracionDeProductosPO.ingresarValoresAlProducto(tabla);
        configuracionDeProductosPO.clickAgregarAOportunidad();
    }
    
    @Given("continuo a presentación de productos")
    public void continuo_a_presentacion_de_productos (){
        configuracionDeProductosPO.continuarAPresentacionDelProducto();
        //Solo imprime por consola los valores de PresentacionDelProducto
        presentacionDeProductosPO.extraerDatosPresentacionDelProducto();
    }

    @Given("agrego los siguientes datos adicionales")
    public void agrego_los_siguientes_datos_adicionales(DataTable datosAdicionales){
        datosAdicionalesPO = presentacionDeProductosPO.clickDatosAdicionales();
        datosAdicionalesPO.probarDatosAdicionales();
    }

    @When("se agrega al carro correctamente")
    public void se_agrega_al_carro_correctamente (){
        Assert.assertTrue(configuracionDeProductosPO.existeProductoEnElCarro(this.producto));
    }



}
