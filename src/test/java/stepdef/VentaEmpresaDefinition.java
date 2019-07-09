package stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.SharedDriver;
import pageobjects.*;
import pageobjects.ventaEmpresa.ConfiguracionDeProductosPO;

import java.util.ArrayList;

public class VentaEmpresaDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ConfiguracionDeProductosPO configuracionDeProductosPO;
    ArrayList<byte[]> screenshotList;

    public VentaEmpresaDefinition(SharedDriver driver,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              ConfiguracionDeProductosPO configuracionDeProductosPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
        this.screenshotList=screenshotList;
        this.paginaInicioPO= paginaInicioPO;
        this.escritorioComercialPO= escritorioComercialPO;
        this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
        this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
        this.configuracionDeProductosPO=configuracionDeProductosPO;
    }



    @Given("voy a contratar el producto {string}")
    public void voy_a_contratar_el_producto_string (String nombreProducto){
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
        configuracionDeProductosPO.expanderFamilia("Líneas");

        // TODO: 2019-07-08 realizar flujo para contratar producto
    }



}
