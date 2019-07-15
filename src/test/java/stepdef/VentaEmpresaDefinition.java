package stepdef;

import Managers.driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.*;
import pageobjects.ventaEmpresa.ConfiguracionDeProductosPO;
import pageobjects.ventaEmpresa.DatosAdicionalesPO;
import pageobjects.ventaEmpresa.DocumentosAdjuntosPO;
import pageobjects.ventaEmpresa.PresentacionDeProductosPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @When("voy a contratar un producto")
    public void voy_a_contratar_un_producto(){
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
    }

    @When("voy a contratar el siguiente producto")
    public void voy_a_contratar_el_siguiente_producto(DataTable valores){
        List<Map<String,String>> tablaProducto = valores.asMaps();
        this.producto=tablaProducto.get(1).get("Producto");
        String  familia = tablaProducto.get(1).get("Familia");
        System.out.println(tablaProducto);
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
        configuracionDeProductosPO.expanderFamilia(familia);
        configuracionDeProductosPO.seleccionoElProducto(producto);
        //configuracionDeProductosPO.ingresarValoresAlProducto(valores);
        //configuracionDeProductosPO.clickAgregarAOportunidad();
    }

    @Given("despliego familia {string}")
    public void despliego_familia_string(String nombreFamilia){
        configuracionDeProductosPO.expanderFamilia(nombreFamilia);
    }

    @When("voy a contratar el producto {string} con los siguientes valores:")
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
        Assert.assertTrue("No se ha agregado el producto al carro correctamente",configuracionDeProductosPO.existeProductoEnElCarro(this.producto));
    }

    @Then("se agrega el producto correctamente")
    public void se_agrega_el_producto_correctamente(){
        Assert.assertTrue(configuracionDeProductosPO.existeProductoEnElCarro(this.producto));
    }





}
