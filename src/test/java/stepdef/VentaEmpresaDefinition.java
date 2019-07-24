package stepdef;

import Managers.driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.*;
import pageobjects.ventaEmpresa.*;

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
    ArrayList<InputFormulario> datosFormulario = new ArrayList<>();

    // Constantes

    String MONTO_A_SOLICITAR_SELECTOR="Monto a Solicitar ($)";
    String SPREAD_SELECTOR="Spread (%)";
    String AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR="Aumento programado de cupo";

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

    @Given("voy a contratar una LDC")
    public void voy_a_contratar_una_LDC(){
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
        configuracionDeProductosPO.expanderFamilia("Líneas");
    }

    @Given("despliego familia {string}")
    public void despliego_familia_string(String nombreFamilia){
        configuracionDeProductosPO.expanderFamilia(nombreFamilia);
    }

    @Given("contrato el producto {string}")
    public void voy_a_contratar_el_producto_string(String producto){
        this.producto=producto;
        configuracionDeProductosPO.seleccionoElProducto(producto);
    }

    @When("voy a contratar el producto {string} con los siguientes valores:")
    public void voy_a_contratar_el_producto_string(String producto, DataTable tabla){
        this.producto=producto;
        configuracionDeProductosPO.seleccionoElProducto(producto);
        configuracionDeProductosPO.ingresarValoresAlProducto(tabla);
        configuracionDeProductosPO.clickAgregarAOportunidad();
    }

    @When("contrato el producto {string} con los siguientes valores:")
    public void contrato_el_producto_string_con_los_siguientes_valores(String producto, DataTable tabla){
        this.producto=producto;
        configuracionDeProductosPO.seleccionoElProducto(producto);
        configuracionDeProductosPO.ingresarValoresAlProducto(tabla);
        configuracionDeProductosPO.clickAgregarAOportunidad();
    }
    
    @Given("continuo a presentación de productos")
    public void continuo_a_presentacion_de_productos(){
        configuracionDeProductosPO.continuarAPresentacionDelProducto();
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

    @Then("se muestra el mensaje de error {string}")
    public void se_muestra_el_mensaje_de_error_string (String mensaje){
        Assert.assertEquals(mensaje, configuracionDeProductosPO.obtenerMensajeDeErrorDelInput(mensaje));
    }

    @Then("permite seleccionar el valor {string}")
    public void permite_seleccionar_el_valor_string (String valor){
        // TODO: 2019-07-18 pgtoopx Realizar mapeo de valores seleccionados
        Assert.assertTrue(true);
    }

    @Then("no entrega mensaje de error")
    public void no_entrega_mensaje_de_error (){
        //hace algo
        // TODO: 2019-07-18 pgtoopx Deberia validar que no muestre el mensaje de error en el input asignado
        Assert.fail("No implementado");
    }

    @Then("se refleja frecuencia entrega cartola {string}")
    public void se_refleja_frecuencia_entrega_cartola_string (String valor){
        Assert.assertEquals(valor,configuracionDeProductosPO.obtenerEmisionCartolaText());
    }

    @When("ingreso el monto a solicitar {int}")
    public void ingreso_el_monto_a_solicitar_int (int monto){
        datosFormulario.add(new InputFormulario(MONTO_A_SOLICITAR_SELECTOR,monto+""));
        configuracionDeProductosPO.ingresarValorEnInput(MONTO_A_SOLICITAR_SELECTOR, monto+"");
    }

    @When("ingreso un spread de {}%")
    public void ingreso_un_spread_de_porcentaje (String porcentajeSpread){
        datosFormulario.add(new InputFormulario(SPREAD_SELECTOR,porcentajeSpread));
        configuracionDeProductosPO.ingresarValorEnInput(SPREAD_SELECTOR, porcentajeSpread);
    }

    @When("lo agrego a oportunidad")
    public void lo_agrego_a_oportunidad(){
        configuracionDeProductosPO.clickAgregarAOportunidad();
    }

    @Then("no entrega mensaje de error para los campos llenados")
    public void no_entrega_mensaje_de_error_para_los_campos_llenados (){
        for (InputFormulario i:datosFormulario) {
            Assert.assertFalse(configuracionDeProductosPO.contieneErrorElInput(i.getKey()));
        }
    }

    @When("ingreso {string} en aumento programado de cupo")
    public void ingreso_string_en_aumento_programado_de_cupo(String aumento){
        datosFormulario.add(new InputFormulario(AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR,aumento));
        configuracionDeProductosPO.ingresarValorEnInput(AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR, aumento);
    }



}
