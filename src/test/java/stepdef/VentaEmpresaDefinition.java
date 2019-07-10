package stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import pageobjects.*;
import pageobjects.ventaEmpresa.ConfiguracionDeProductosPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        configuracionDeProductosPO.seleccionoElProducto(producto);
        configuracionDeProductosPO.ingresarValoresAlProducto(tabla);
        configuracionDeProductosPO.clickAgregarAOportunidad();
    }
    
    @Given("continuo a presentación de productos")
    public void continuo_a_presentacion_de_productos (){
        configuracionDeProductosPO.continuarAPresentacionDelProducto();
    }

    @Given("agrego los siguientes datos adicionales")
    public void agrego_los_siguientes_datos_adicionales(DataTable datosAdicionales){

    }



}
