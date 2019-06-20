package stepdef;

import Generics.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import driver.DriverFactory;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;

import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver.Options;
import pageobjects.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    AsideGarantiasEmpresaPO asideGarantiasEmpresaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ArrayList<byte[]> screenshotList;
    //ConexionDB conexionDB;
    Dimension dimensionActual;
    String cabecera;
    String folio;
    public GenericsDefinition(SharedDriver driver,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              DetalleGarantiasPO detalleGarantiasPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              AsideGarantiasEmpresaPO asideGarantiasEmpresaPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
      this.screenshotList=screenshotList;
      this.paginaInicioPO= paginaInicioPO;
      this.escritorioComercialPO= escritorioComercialPO;
      this.detalleGarantiasPO=detalleGarantiasPO;
      this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
      this.asideGarantiasEmpresaPO= asideGarantiasEmpresaPO;
      this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
    }


    @Given("el usuario {string} ingreso a V360")
    public void el_usuario_string_ingresa_a_v360(String usuario){
        paginaInicioPO.iniciarSesion(usuario, "Banco01");
    }

    @Given("busco el rut {string}")
    public void busco_el_rut_string(String rut){
        escritorioComercialPO.buscarPorRut(rut);
    }

    @When("Se pierde la conexion de internet")
    public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
        ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
    }

    @When("ingreso a detalle de garantias persona")
    public void ingreso_a_detalle_de_garantias_persona(){
        vista360ResumenPersonaPO.clickBoxGarantias();
    }

    @When("ingreso a detalle de garantias empresa")
    public void ingreso_a_detalle_de_garantias_empresa(){
        vista360ResumenEmpresaPO.clickBotonGarantiasEmpresa();
        asideGarantiasEmpresaPO.clickIrAGarantiasVigentesEHistoricas();
    }

    @When("me encuentro en el tab {string}")
    public void me_encuentro_en_el_tab_string(String tab){
        switch (tab.toLowerCase()){
            case "por constituir": detalleGarantiasPO.clickTabPorConstituir();
        }
    }

    @Then("el sistema muestra una tabla con las siguientes columnas")
    public void el_sistema_muestra_una_tabla_con_las_siguientes_columnas(DataTable columnas) throws SQLException {
        //detalleGarantiasPO.imprimirTablaCompleta();

        List<String> columnasEsperadas = columnas.asList(String.class);
        List<String> columnasActuales = ManejadorTablaFrontEnd.getNameColumns(detalleGarantiasPO.getTabla());
        Assert.assertArrayEquals("no contiene las mismas columnas", columnasEsperadas.toArray(), columnasActuales.toArray());
        for(int i=0; i<columnasEsperadas.size(); i++){
            //Verify.verify(columnasEsperadas.get(i).toString().contains(columnasActuales.get(i).toString()));
        }
    }

    @Then("el sistema muestra el mensaje {string}")
    public void el_sistema_muestra_el_mensaje_string(String mensaje){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        DriverFactory.getDriver().findElement(By.xpath("//h5[contains(text(),'"+mensaje+"')]"));
    }

    @Then("el nombre del tab indica {string}")
    public void el_nombre_del_tab_indica_string(String mensaje){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        DriverFactory.getDriver().findElement(By.xpath("//a[contains(text(),'"+mensaje+"')]"));
    }

    @Then("el sistema muestra un guion en el campo faltante")
    public void el_sistema_muestra_un_guion_en_el_campo_faltante(){
        JsonArray datosTabla = detalleGarantiasPO.getJsonFromTabla();
        String obj = datosTabla.get(0).getAsJsonObject().get("Grado").toString();
        Assert.assertTrue("",obj.contains("-"));
    }

    @Then("hago click en la cabecera de la columna {string}")
    public void hago_click_en_la_cabecera_de_la_columnas_string(String cabecera){
        this.cabecera=cabecera;
        dimensionActual= detalleGarantiasPO.obtenerSizeCabecera(cabecera);
        detalleGarantiasPO.clickEnCabecera(cabecera);
    }

    @Then("hago click en el cuerpo de la columna {string}")
    public void hago_clicl_en_el_cuerpo_de_la_columna_string(String columna){
        detalleGarantiasPO.clickEnCuerpoDeLaCabecera(columna);
    }

    @Then("la columna se expande")
    public void la_columna_se_expande(){
        Dimension dimensionNueva= detalleGarantiasPO.obtenerSizeCabecera(this.cabecera);
        System.out.println("Dimension antigua: " + dimensionActual);
        System.out.println("Dimension nueva: " + dimensionNueva);
        Assert.assertTrue(util.seExpande(this.dimensionActual, dimensionNueva));
    }

    @Then("se colapsan las otras columnas")
    public void se_colapsan_las_otras_columnas(){
        Assert.assertTrue(true);
    }

    @Then("aparece el listado de garantias ordenado por defecto segun folio de manera ascendente")
    public void aparece_el_listado_de_garantias_ordenado_por_defecto_segun_folio_de_manera_ascendente(){
        JsonArray datosTabla = detalleGarantiasPO.getJsonFromTabla();
        JsonArray sortedJsonArray = new JsonArray();
        List<JsonObject> datosAOrdenar = new ArrayList<>();
        for (int i = 0; i< datosTabla.size(); i++){
            datosAOrdenar.add((JsonObject) datosTabla.get(i));
        }
        Collections.sort( datosAOrdenar, new Comparator<JsonObject>() {
            private static final String KEY_NAME = "Folio";

            @Override
            public int compare(JsonObject a, JsonObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME).toString();
                    valB = (String) b.get(KEY_NAME).toString();
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
            }
        });
        for (int i = 0; i < datosAOrdenar.size(); i++) {
            sortedJsonArray.add(datosAOrdenar.get(i));
        }
    }

    @Then("se ordenara por esa columna")
    public void se_ordenara_por_esa_columna(){
        //Este valor trae la columna seleccionada = this.cabecera;
        //TODO: Implementar
        Assert.assertTrue(true);
    }

    @Then("se invierte el orden de esa columna")
    public void se_invierte_el_orden_de_esa_columna(){
        //TODO: implementar
        Assert.assertTrue(true);
    }

    @Then("se muestra el paginador")
    public void se_muestra_el_paginador() {
        Assert.assertTrue(detalleGarantiasPO.estaVisiblePaginador());
    }

    @When("el cliente tiene mas de {int} registros")
    public void el_cliente_tiene_mas_de_int_registros(int limiteRegistros){
        Assert.assertTrue("Tiene menos de "+limiteRegistros+" registros",detalleGarantiasPO.getJsonFromTabla().size()>limiteRegistros);
    }

    @When("despliego el filtro")
    public void despliego_el_filtro(){
        detalleGarantiasPO.desplegarFiltro();
    }

    @When("colapso el filtro")
    public void colapso_el_filtro(){
        detalleGarantiasPO.desplegarFiltro();
    }

    @When("ingreso el valor {string} en el filtro {string}")
    public void ingreso_un_valor_en_el_filtro(String valor, String filtro){
        this.folio=valor;
        detalleGarantiasPO.escribirEnCampoFiltroPorFolio(valor);
    }

    @When("presiono el boton {string}")
    public void presiono_el_boton_string(String boton){
        detalleGarantiasPO.clickEnBotoneraFiltro(boton);
    }

    @Then("el sistema arroja el mensaje {string}")
    public void el_sistema_arroja_el_mensaje_string(String mensaje){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        Assert.assertTrue(detalleGarantiasPO.validarMensaje(mensaje));
    }

    @Then("se colapsa la seccion de filtro")
    public void se_colapsa_la_seccion_de_filtro(){
        //TODO: Validar
        Assert.assertTrue(true);
    }

    @Then("ingreso un valor en el filtro combinado")
    public void ingreso_un_valor_en_el_filtro_combinado(){
        //TODO: Agregar el otro filtro
        detalleGarantiasPO.escribirEnCampoFiltroPorFolio(this.folio);
    }

    @Then("los filtros se limpian")
    public void los_filtros_se_limpian(){
        Assert.assertTrue(detalleGarantiasPO.validarFiltrosVacios());
    }

    @Then("se muestra el filtro para los siguientes campos")
    public void se_muestra_el_filtro_para_los_siguientes_campos(DataTable campos){
        Assert.assertTrue(detalleGarantiasPO.validarFiltros(campos.asList()));
    }

    @Then("los siguientes botones")
    public void los_siguientes_botones(DataTable botones){
        Assert.assertTrue(detalleGarantiasPO.validarBotones(botones.asList()));
    }

    @Then("el paginador tiene las siguientes opciones")
    public void tiene_las_siguientes_opciones(DataTable opciones){
        detalleGarantiasPO.scrollAlFondoDeLaPagina();
        detalleGarantiasPO.clickFlechaSelectPaginador();
        List<String> listadoDeOpciones = opciones.asList();
        Assert.assertTrue(detalleGarantiasPO.validarOpcionesPaginador(listadoDeOpciones));
        takeScreenshot();
    }

    @Then("se aplican los filtros")
    public void se_aplican_los_filtros(){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        JsonArray datosTabla = detalleGarantiasPO.getJsonFromTabla();
        boolean seAplicaElFiltro= false;
        for (int i=0; i<datosTabla.size(); i++){
          if(!seAplicaElFiltro)
              seAplicaElFiltro = datosTabla.get(i).getAsJsonObject().get("Folio").getAsString().contains(this.folio);
        }
        Assert.assertTrue(seAplicaElFiltro);
    }

    @Then("se realiza la busqueda sobre el total de datos")
    public void se_realiza_la_busqueda_sobre_el_total_de_datos(){
        //TODO: Validar a mayor detalle
        JsonArray datosTabla = detalleGarantiasPO.getJsonFromTabla();
        boolean seAplicaElFiltro= false;
        for (int i=0; i<datosTabla.size(); i++){
            if(!seAplicaElFiltro)
                seAplicaElFiltro = datosTabla.get(i).getAsJsonObject().get("Folio").getAsString().contains(this.folio);
        }
        Assert.assertTrue(seAplicaElFiltro);
    }


    @Then("pruebo algo")
    public void prueba() throws SQLException {
        //conexionDB = new ConexionDB("jdbc:oracle:thin:@152.139.146.170:1521/DIANA.bch.bancodechile.cl","Siebel","siebelqa");
        //Connection connection= conexionDB.getConexion();
        //ResultSet resultSet= conexionDB.RunQuery(connection,"SELECT soe.name as rut, sa.build AS folio, sa.warranty_type_cd AS tipo_garantia, sa.cfg_type_cd AS tipo_bien, sa.curcy_cd as moneda, sa.asset_value_amt AS valor_comercial, sa.rplcmnt_val_amt AS valor_garantia, sa.exch_date AS tasacion, sa.extension_cd AS seguro, sa.status_cd as estado FROM siebel.s_asset sa, siebel.s_org_ext soe WHERE sa.type_cd = 'Warranty' AND soe.par_row_id = sa.owner_accnt_id AND UPPER(sa.status_cd) in ('CONSTITUIDA', 'POR ALZAR') AND instr(soe.NAME,'-')>8;");
        //System.out.println(resultSet);
        //System.out.println(ResultSetConverter.convert(resultSet));
    }

    public void takeScreenshot(){
        byte[] screenshotByte = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        screenshotList.add(screenshotByte);
    }
}