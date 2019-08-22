package stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.componentHandler.InputFormulario;
import Managers.driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import pageobjects.*;
import pageobjects.ventaEmpresa.*;

import java.util.ArrayList;
import java.util.List;

public class VentaEmpresaDefinition {

    //Page Object Model
    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ConfiguracionDeProductosPO configuracionDeProductosPO;
    PresentacionDeProductosPO presentacionDeProductosPO;
    DatosAdicionalesPO datosAdicionalesPO;
    AsociarLimitesPo asociarLimitesPo;
    ApoderadosPO apoderadosPO;
    DocumentosAdjuntosPO documentosAdjuntosPO;

    //Data sharing state

    ArrayList<byte[]> screenshotList;
    String producto;
    ArrayList<InputFormulario> datosFormulario = new ArrayList<>();

    // Constantes

    String MONTO_A_SOLICITAR_SELECTOR="Monto a Solicitar ($)";
    String SPREAD_SELECTOR="Spread (%)";
    String AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR="Aumento programado de cupo";
    String DEGRAVAMEN_LINEA_DE_CREDITO = "Desgravamen Línea de Crédito";
    String TIPO_PLAZO = "Tipo Plazo";
    String PLAZO = "Plazo";
    String PRODUCTO = "Producto";

    public VentaEmpresaDefinition(SharedDriver driver,
                                  ArrayList<byte[]> screenshotList,
                                  PaginaInicioPO paginaInicioPO,
                                  EscritorioComercialPO escritorioComercialPO,
                                  Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                                  ConfiguracionDeProductosPO configuracionDeProductosPO,
                                  PresentacionDeProductosPO presentacionDeProductosPO,
                                  DatosAdicionalesPO datosAdicionalesPO,
                                  DocumentosAdjuntosPO documentosAdjuntosPO,
                                  AsociarLimitesPo asociarLimitesPo,
                                  ApoderadosPO apoderadosPO,
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
        this.asociarLimitesPo=asociarLimitesPo;
        this.apoderadosPO=apoderadosPO;
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

    @Given("voy a contratar una Banconexión")
    public void voy_a_contratar_una_banconexion(){
        vista360ResumenEmpresaPO.clickBotonContratarProductos();
        configuracionDeProductosPO.expanderFamilia("Canales remotos");
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
    }
    
    @Given("continuo a presentación de productos")
    public void continuo_a_presentacion_de_productos(){
        configuracionDeProductosPO.continuarAPresentacionDelProducto();
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
        configuracionDeProductosPO.clickAgregarAOportunidad();
        Assert.assertEquals(mensaje, configuracionDeProductosPO.obtenerMensajeDeErrorDelInput(mensaje));
    }

    @Then("permite seleccionar el valor {string}")
    public void permite_seleccionar_el_valor_string (String valor){
        // TODO: 2019-07-18 pgtoopx Realizar mapeo de valores seleccionados
        Assert.fail("No implementado");
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

    @When("ingreso el monto a solicitar {}")
    public void ingreso_el_monto_a_solicitar_int (String monto){
        datosFormulario.add(new InputFormulario(MONTO_A_SOLICITAR_SELECTOR,monto));
        //configuracionDeProductosPO.ingresarValorEnInput(MONTO_A_SOLICITAR_SELECTOR, monto);
        Assert.fail("No implementado");
    }

    @When("ingreso un spread de {}%")
    public void ingreso_un_spread_de_porcentaje (String porcentajeSpread){
        datosFormulario.add(new InputFormulario(SPREAD_SELECTOR,porcentajeSpread));
        configuracionDeProductosPO.waitUntilEscritorioComercialIsLoaded();
        configuracionDeProductosPO.limpiarValorEnInput(SPREAD_SELECTOR);
        Assert.fail("No implementado");
        //configuracionDeProductosPO.ingresarValorEnInput(SPREAD_SELECTOR, porcentajeSpread);
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
        //configuracionDeProductosPO.ingresarValorEnInput(AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR, aumento);
        Assert.fail("No implementado");
    }

    @When("ingreso tipo plazo {string}")
    public void ingreso_tipo_plazo_string(String plazo){
        datosFormulario.add(new InputFormulario(TIPO_PLAZO, plazo));
        //configuracionDeProductosPO.ingresarValorEnInput(TIPO_PLAZO, plazo);
        Assert.fail("No implementado");
    }

    @Then("me permite ingresar {string} meses en el campo plazo")
    public void me_permite_ingresar_string_meses_en_el_campo_plazo(String meses){
        datosFormulario.add(new InputFormulario(PLAZO, meses));
        //configuracionDeProductosPO.ingresarValorEnInput(PLAZO, meses);
        Assert.fail("No implementado");
    }

    @Then("ingreso {string} meses en el campo plazo")
    public void ingreso_string_meses_en_el_campo_plazo(String meses){
        datosFormulario.add(new InputFormulario(PLAZO, meses));
        //configuracionDeProductosPO.ingresarValorEnInput(PLAZO, meses);
        Assert.fail("No implementado");
    } 

    @When("aparece degravamen de linea de credito habilitado")
    public void aparece_degravamen(){
        Assert.assertEquals("Si", configuracionDeProductosPO.getSeleccionDegravamen(DEGRAVAMEN_LINEA_DE_CREDITO));
    }

    @Then("el spread debe debe ser {}%")
    public void el_spread_debe_ser_x(String valorSpread){
        Assert.assertEquals(valorSpread, configuracionDeProductosPO.getValorSpread());
    }

    @Then("el campo {string} contiene los siguientes valores")
    public void el_campo_string_contiene_los_siguientes_valores(String campo, DataTable valores){
        //Assert.assertEquals(valores.asList(), configuracionDeProductosPO.getValoresInput(campo));
        Assert.fail("No implementado");
    }

    @Then("el campo {string} contiene solo el valor {string}")
    public void el_campo_string_contiene_solo_el_valor_string(String campo, String valor){
        //List<String> valores = configuracionDeProductosPO.;
        //if(valores.size() != 1) Assert.fail("Tiene mas de un valor\n valores:"+valores.toString());
        //Assert.assertEquals(valor, valores.get(0));
        Assert.fail("No implementado");
    }

    @And("asocio los limites")
    public void asocio_los_limites(){
        configuracionDeProductosPO.clickAsociarLimites();
        asociarLimitesPo.insertarMontoReservaALimites("1",asociarLimitesPo.getTotalMontoReserva());
        asociarLimitesPo.asociarLimites();
        screenshotList.add(asociarLimitesPo.takeScreenshot());
        asociarLimitesPo.cerrar();
    }

    @And("agrego el {string} como representante legal")
    public void agrego_el_string_como_representante_legal(String rutRepresentante){
        configuracionDeProductosPO.clickApoderados();
        apoderadosPO.ingresarApoderado(rutRepresentante);
        screenshotList.add(apoderadosPO.takeScreenshot());
        apoderadosPO.cerrar();
    }

    @And("despliego el aside Rep. Legal/Apoderados")
    public void despliego_el_aside_replegal_apoderados(){
        configuracionDeProductosPO.clickApoderados();
    }

    @Then("se validan los datos de configuracion en presentacion")
    public void se_validan_los_datos_de_configuracion_en_presentacion(){
        presentacionDeProductosPO.extraerDatosPresentacionDelProducto();
    }

    @Then("no se visualiza el producto {string}")
    public void no_se_visualiza_el_producto_string (String producto){
        Assert.fail("No implementado");
    }

    @Then("no se visualiza la familia {string}")
    public void no_se_visualiza_la_familia_string (String familia){
        Assert.assertFalse("La familia \""+familia+"\" se visualiza",
                configuracionDeProductosPO.isFamiliaVisible(familia));
    }

    @Then("se muestra el mensaje {string}")
    public void se_muestra_el_mensaje_string(String mensaje){
        //TODO: validar mensaje de error
    }

    @Then("probemos esto")
    public void probemos_esto (DataTable dataTable){
        configuracionDeProductosPO.pruebas(dataTable);
    }

}
