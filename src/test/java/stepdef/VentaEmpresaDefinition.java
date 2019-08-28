package stepdef;

import Managers.context.ScenarioContext;
import Managers.driver.SharedDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pageobjects.EscritorioComercialPO;
import pageobjects.PaginaInicioPO;
import pageobjects.Vista360ResumenEmpresaPO;
import pageobjects.Vista360ResumenPersonaPO;
import pageobjects.ventaEmpresa.*;
import support.componentHandler.InputFormulario;
import support.ui.elements.BchMensajeEmpresa;

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
    ScenarioContext scenarioContext;

    // Constantes

    String MONTO_A_SOLICITAR_SELECTOR="Monto a Solicitar ($)";
    String SPREAD_SELECTOR="Spread (%)";
    String AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR="Aumento programado de cupo";
    String DEGRAVAMEN_LINEA_DE_CREDITO = "Desgravamen Línea de Crédito";
    String TIPO_PLAZO = "Tipo Plazo";
    String PLAZO = "Plazo";
    String PRODUCTO = "Producto";

    public VentaEmpresaDefinition(SharedDriver driver,
                                  ScenarioContext scenarioContext,
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
        this.scenarioContext=scenarioContext;
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
        List<String> actual = configuracionDeProductosPO.getMensajesDeErrorFromInput(datosFormulario.get(datosFormulario.size()-1).getKey());
        if (actual.size()!=1)Assert.fail(String.format("Error se encontraron %s errores en el input %s \n Los errores son: %s",
                actual.size(),datosFormulario.get(datosFormulario.size()-1).getKey(), actual));
        Assert.assertEquals(mensaje, actual.get(0));
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
        Assert.assertEquals(valor,configuracionDeProductosPO.getEmisionCartolaText());
    }

    @When("ingreso el monto a solicitar {}")
    public void ingreso_el_monto_a_solicitar_int (String monto){
        datosFormulario.add(new InputFormulario(MONTO_A_SOLICITAR_SELECTOR,monto));
        configuracionDeProductosPO.ingresarMontoASolicitar(monto);
    }

    @When("ingreso un spread de {}%")
    public void ingreso_un_spread_de_porcentaje (String porcentajeSpread){
        datosFormulario.add(new InputFormulario(SPREAD_SELECTOR,porcentajeSpread));
        configuracionDeProductosPO.limpiarValorEnInput(SPREAD_SELECTOR);
        configuracionDeProductosPO.waitUntilEscritorioComercialIsLoaded();
        configuracionDeProductosPO.ingresarSpread(porcentajeSpread);
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
        configuracionDeProductosPO.ingresarValorAlProducto(AUMENTO_PROGRAMADO_DE_CUPO_SELECTOR, aumento);
    }

    @When("ingreso tipo plazo {string}")
    public void ingreso_tipo_plazo_string(String plazo){
        datosFormulario.add(new InputFormulario(TIPO_PLAZO, plazo));
        configuracionDeProductosPO.ingresarValorAlProducto(TIPO_PLAZO, plazo);
    }

    @Then("me permite ingresar {string} meses en el campo plazo")
    public void me_permite_ingresar_string_meses_en_el_campo_plazo(String meses){
        datosFormulario.add(new InputFormulario(PLAZO, meses));
        configuracionDeProductosPO.ingresarValorAlProducto(PLAZO, meses);
    }

    @Then("ingreso {string} meses en el campo plazo")
    public void ingreso_string_meses_en_el_campo_plazo(String meses){
        datosFormulario.add(new InputFormulario(PLAZO, meses));
        configuracionDeProductosPO.ingresarValorAlProducto(PLAZO, meses);
    }

    @When("aparece desgravamen de linea de credito habilitado")
    public void aparece_degravamen(){
        Assert.assertEquals("Si", configuracionDeProductosPO.getValorSeguroDegravamen());
    }

    @Then("el spread debe debe ser {}%")
    public void el_spread_debe_ser_x(String valorSpread){
        Assert.assertEquals(valorSpread, configuracionDeProductosPO.getValorSpread());
    }

    @Then("el campo {string} contiene los siguientes valores")
    public void el_campo_string_contiene_los_siguientes_valores(String campo, DataTable valores){
        List<String> valoresSelect = configuracionDeProductosPO.getValoresDelSelect(campo);
        Assert.assertEquals(valores.asList(), valoresSelect);
    }

    @Then("el select {string} contiene solo el valor {string}")
    public void el_campo_string_contiene_solo_el_valor_string(String campo, String valor){
        List<String> valoresSelect = configuracionDeProductosPO.getValoresDelSelect(campo);
        if(valoresSelect.size()!=1) Assert
                .fail(String.format("El select contiene %s valores\n Valores: %s",
                        valoresSelect.size(),
                        valoresSelect));
        Assert.assertEquals(valor, valoresSelect.get(0));
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
        Assert.fail("No Implementado");
    }

    @Then("no se visualiza el producto {string}")
    public void no_se_visualiza_el_producto_string (String producto){
        Assert.assertFalse(String.format("El producto %s es visible", producto),
                configuracionDeProductosPO.isProductoVisible(producto));
    }

    @Then("no se visualiza la familia {string}")
    public void no_se_visualiza_la_familia_string (String familia){
        Assert.assertFalse("La familia \""+familia+"\" se visualiza",
                configuracionDeProductosPO.isFamiliaVisible(familia));
    }

    @Then("se muestra el mensaje {string}")
    public void se_muestra_el_mensaje_string(String mensaje){
        Assert.assertEquals(mensaje,configuracionDeProductosPO.getMensajesBchEmpresasSinSubtitulos().get(0));
    }

    @Then("aparece el mensaje {string} en los siguientes campos:")
    public void aparece_el_mensaje_string_en_los_siguientes_campos(String mensaje, DataTable campos){
        campos.asList().forEach((c)->{
            if (configuracionDeProductosPO.contieneErrorElInput(c)){
                Assert.assertEquals(mensaje, configuracionDeProductosPO.getMensajesDeErrorFromInput(c).get(0));
            }else{
                Assert.fail(String.format("El campo \"%s\" no contiene error", c));
            }
        });
    }

    @Then("aparece el mensaje de advertencia {string} y el subtitulo {string}")
    public void aparece_el_mensaje_de_error_string_y_el_sub_string (String mensaje, String subtitulo){
        BchMensajeEmpresa m = configuracionDeProductosPO.getBchMensajesEmpresasWithTitulo(mensaje);
        Assert.assertEquals(mensaje, m.getTitulo());
        Assert.assertEquals(subtitulo, m.getSubtitulo());
        Assert.assertTrue("El mensaje no es un warning", m.isWarning());
    }

    @Then("pruebo esto")
    public void prueba (){
        configuracionDeProductosPO.pruebas();
    }




}
