package stepdef;

import Generics.ManejadorTablaFrontEnd;
import Generics.util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.DriverFactory;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import pageobjects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GarantiasDefinition {

    //Paginas
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    AsideGarantiasEmpresaPO asideGarantiasEmpresaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;

    //Screenshot list
    ArrayList<byte[]> screenshotList;

    //Variables
    Dimension dimensionActual;
    String cabecera;
    String folio;
    JsonArray datosTabla;
    JsonObject store = new JsonObject();


    public GarantiasDefinition(SharedDriver driver,
                               ArrayList<byte[]> screenshotList,
                               DetalleGarantiasPO detalleGarantiasPO,
                               Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                               AsideGarantiasEmpresaPO asideGarantiasEmpresaPO,
                               Vista360ResumenEmpresaPO vista360ResumenEmpresaPO){
        this.detalleGarantiasPO=detalleGarantiasPO;
        this.vista360ResumenPersonaPO=vista360ResumenPersonaPO;
        this.asideGarantiasEmpresaPO=asideGarantiasEmpresaPO;
        this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
        this.screenshotList=screenshotList;
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
        detalleGarantiasPO.clickTab(tab);
    }

    @Then("el sistema muestra una tabla con las siguientes columnas")
    public void el_sistema_muestra_una_tabla_con_las_siguientes_columnas(DataTable columnas) {
        List<String> columnasEsperadas = columnas.asList(String.class);
        List<String> columnasActuales = ManejadorTablaFrontEnd.getNameColumns(detalleGarantiasPO.getTabla());
        Assert.assertArrayEquals("No contiene las mismas columnas",
                columnasEsperadas.toArray(),
                columnasActuales.toArray());
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
        this.datosTabla=detalleGarantiasPO.getJsonFromTabla();
        detalleGarantiasPO.clickEnCabecera(cabecera);
        this.cabecera=cabecera;
    }

    @Then("hago click en el cuerpo de la columna {string}")
    public void hago_clicl_en_el_cuerpo_de_la_columna_string(String columna){
        this.dimensionActual=detalleGarantiasPO.obtenerSizeCabecera(columna);
        detalleGarantiasPO.clickEnCuerpoDeLaCabecera(columna);
        this.cabecera=columna;
    }

    @Then("la columna se expande")
    public void la_columna_se_expande(){
        util.waitFor(2);
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
        //Datos de la tabla se almacenan en datosTabla
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
        detalleGarantiasPO.colapsarFiltro();
    }

    @When("ingreso el valor {string} en el filtro {string}")
    public void ingreso_un_valor_en_el_filtro(String valor, String filtro){
        this.folio=valor;
        if(valor.isEmpty()|filtro.isEmpty()) Assert.assertTrue("El campo viene vacio, revisar BDD",false);
        detalleGarantiasPO.seleccionarValorEnFiltro(filtro, valor);
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
        Assert.assertFalse("La sección filtro no se colapso correctamente",
                detalleGarantiasPO.getClassDesplegarFiltro().contains("active"));
    }

    @Then("ingreso un valor en el filtro combinado")
    public void ingreso_un_valor_en_el_filtro_combinado(){
        //TODO: Agregar el otro filtro
        detalleGarantiasPO.escribirEnCampoFiltroPorFolio(this.folio);
    }

    @Then("los filtros se limpian")
    public void los_filtros_se_limpian(){
        for (WebElement element: detalleGarantiasPO.getSeccionFiltros()){
            if(element.getText().contains("Folio")){
                Assert.assertNull(element.getAttribute("value"));
            }else{
                Assert.assertTrue(element.getText().contains("Seleccione"));
            }
        }
    }

    @Then("se muestra el filtro para los siguientes campos")
    public void se_muestra_el_filtro_para_los_siguientes_campos(DataTable campos){
        Assert.assertArrayEquals("Los filtros no son los esperados",
                campos.asList().toArray(),
                detalleGarantiasPO.getTextListadoFiltros().toArray());
    }

    @Then("los siguientes botones")
    public void los_siguientes_botones(DataTable botones){
        Assert.assertArrayEquals("Los botones del filtro no son los esperados",
                botones.asList().toArray(),
                detalleGarantiasPO.getTextListadoBotones().toArray());
    }

    @Then("el paginador tiene las siguientes opciones")
    public void tiene_las_siguientes_opciones(DataTable opciones){
        detalleGarantiasPO.scrollAlFondoDeLaPagina();
        detalleGarantiasPO.clickFlechaSelectPaginador();
        List<String> listadoDeOpciones = opciones.asList();
        Assert.assertTrue(detalleGarantiasPO.validarOpcionesPaginador(listadoDeOpciones));
        detalleGarantiasPO.takeScreenshot();
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
}
