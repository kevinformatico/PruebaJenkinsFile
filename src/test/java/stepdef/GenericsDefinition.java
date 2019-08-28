package stepdef;

import Managers.context.Context;
import Managers.context.ScenarioContext;
import Managers.context.TestContext;
import Managers.driver.SharedDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pageobjects.EscritorioComercialPO;
import pageobjects.PaginaInicioPO;
import pageobjects.Vista360ResumenEmpresaPO;
import pageobjects.Vista360ResumenPersonaPO;
import support.database.SqlClient;
import support.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericsDefinition {

    final String PERFIL_EMPRESA="empresa";
    final String PERFIL_PERSONA="persona";
    final String DEFAULT_USERNAME="mnilos";
    final String DEFAULT_PASSWORD="Venta75";
    final String DEFAULT_USERNAME_DOCKER="KTELLEZ";
    final String DEFAULT_PASSWORD_DOCKER="Venta75";
    String isOnDocker= System.getProperty("docker", "false");

    TestContext testContext;
    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ArrayList<byte[]> screenshotList;
    ScenarioContext scenarioContext;

    public GenericsDefinition(SharedDriver driver,
                              ScenarioContext scenarioContext,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
        this.scenarioContext = scenarioContext;
        this.screenshotList=screenshotList;
        this.paginaInicioPO= paginaInicioPO;
        this.escritorioComercialPO= escritorioComercialPO;
        this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
        this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
    }


    @Given("el usuario {string} ingreso a V360")
    public void el_usuario_string_ingresa_a_v360(String usuario){
        scenarioContext.setScenarioContext(Context.LOG_USER,usuario);
        scenarioContext.setScenarioContext(Context.LOG_PASSWORD,DEFAULT_PASSWORD);
        paginaInicioPO.iniciarSesion(usuario, DEFAULT_PASSWORD);
    }

    @Given("busco el rut {string}")
    public void busco_el_rut_string(String rut){
        scenarioContext.setScenarioContext(Context.RUT, rut);
        escritorioComercialPO.buscarPorRut(rut);
    }

    @Given("busco el rut {string} con perfil empresa")
    public void  busco_el_rut_string_con_perfil_empresa(String rut){
        scenarioContext.setScenarioContext(Context.RUT, rut);
        escritorioComercialPO.buscarPorRut(rut);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_EMPRESA);
        configurarPerfil(PERFIL_EMPRESA);
    }

    @Given("busco el rut {string} con perfil persona")
    public void  busco_el_rut_string_con_perfil_persona(String rut){
        scenarioContext.setScenarioContext(Context.RUT, rut);
        escritorioComercialPO.buscarPorRut(rut);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_PERSONA);
        configurarPerfil(PERFIL_PERSONA);
    }

    @Given("el usuario busca el rut {string} con perfil empresa")
    public void  el_usuario_busca_el_rut_string_con_perfil_empresa(String rut){
        if(isOnDocker.equalsIgnoreCase("true")){
            scenarioContext.setScenarioContext(Context.LOG_USER,DEFAULT_USERNAME_DOCKER);
            scenarioContext.setScenarioContext(Context.LOG_PASSWORD,DEFAULT_PASSWORD_DOCKER);
            paginaInicioPO.iniciarSesion( DEFAULT_USERNAME_DOCKER, DEFAULT_PASSWORD_DOCKER);
        }else{
            scenarioContext.setScenarioContext(Context.LOG_USER,DEFAULT_USERNAME);
            scenarioContext.setScenarioContext(Context.LOG_PASSWORD,DEFAULT_PASSWORD);
            paginaInicioPO.iniciarSesion( DEFAULT_USERNAME, DEFAULT_PASSWORD);
        }
        scenarioContext.setScenarioContext(Context.RUT, rut);
        escritorioComercialPO.buscarPorRut(rut);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_EMPRESA);
        configurarPerfil(PERFIL_EMPRESA);
    }

    @Given("el usuario busca el rut {string} con perfil persona")
    public void  el_usuario_busca_el_rut_string_con_perfil_persona(String rut){
        if(isOnDocker.equalsIgnoreCase("true")){
            scenarioContext.setScenarioContext(Context.LOG_USER,DEFAULT_USERNAME_DOCKER);
            scenarioContext.setScenarioContext(Context.LOG_PASSWORD,DEFAULT_PASSWORD_DOCKER);
            paginaInicioPO.iniciarSesion( DEFAULT_USERNAME_DOCKER, DEFAULT_PASSWORD_DOCKER);
        }else{
            scenarioContext.setScenarioContext(Context.LOG_USER,DEFAULT_USERNAME);
            scenarioContext.setScenarioContext(Context.LOG_PASSWORD,DEFAULT_PASSWORD);
            paginaInicioPO.iniciarSesion( DEFAULT_USERNAME, DEFAULT_PASSWORD);
        }
        scenarioContext.setScenarioContext(Context.RUT, rut);
        escritorioComercialPO.buscarPorRut(rut);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_PERSONA);
        configurarPerfil(PERFIL_PERSONA);
    }

    @Given("ingreso a Vista 360 persona")
    public void ingreso_a_vista_360_persona(){
        configurarPerfil(PERFIL_PERSONA);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_PERSONA);
    }

    @Given("ingreso a Vista 360 empresa")
    public void ingreso_a_vista_360_empresa(){
        configurarPerfil(PERFIL_EMPRESA);
        scenarioContext.setScenarioContext(Context.PERFIL, PERFIL_EMPRESA);
    }

    @When("espero por {int} segundos")
    public void se_pierde_la_conexion_de_internet(int segundos) {
        util.waitFor(segundos);
    }

    private void configurarPerfil(String tipo){
        switch (tipo){
            case "empresa":
                if (!vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("empresa")&&
                        vista360ResumenPersonaPO.isVisibleBtnCambiarEmpresaPersona()){
                    vista360ResumenPersonaPO.clickCambiarEmpresaPersona();
                }else if(vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("empresa")) {
                    //hace nada xd
                }else{
                    Assert.fail("El rut ingresado no tiene perfil empresa");
                }
                break;
            case "persona":
                if(!vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("persona")&&
                        vista360ResumenPersonaPO.isVisibleBtnCambiarEmpresaPersona()){
                    vista360ResumenPersonaPO.clickCambiarEmpresaPersona();
                }else if(vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("persona")) {
                    //hace nada xd
                }else{
                    Assert.fail("El rut ingresado no tiene perfil persona");
                }
        }
    }


    private String getRutFromDB() throws SQLException{
        SqlClient v360qa = new SqlClient("jdbc:oracle:thin:@200.14.169.238:1521/ORION","VISTA_360_CN","VISTA_360_CN_ORION2K16", "oracle.jdbc.OracleDriver");
        SqlClient coreqa = new SqlClient("jdbc:oracle:thin:@10.30.100.231:1521/ATEQA","flexcube","flexcube", "oracle.jdbc.OracleDriver");

        String QUERY_GET_RUT="SELECT T1.NAME RUT FROM S_ORG_EXT T1, S_INDUST T2, S_CON_ADDR T3, S_ADDR_PER T4 WHERE CUST_STAT_CD='Cliente' AND OU_TYPE_CD='Activo' AND ROWNUM = 1" ;
        String QUERY_CORE="SELECT b.cust_no as RUT, a.CUST_AC_NO as 'CTA CTE', a.BRANCH_CODE as Oficina, a.LOC_ACC_NO as 'LDC', b.acy_avl_bal as Saldo_Cta_Cte, a.*" +
                "FROM STTMS_CUSAC_LOC_DETAIL a, sttm_cust_account b" +
                "Where a.cust_ac_no = b.cust_ac_no";
        //consulta rut
        v360qa.connect();
        v360qa.prepareStatement(QUERY_GET_RUT);
        ResultSet rs = v360qa.executeQuery();
        rs.next();
        String resutaldo = rs.getString("RUT");
        v360qa.close();

        //Consulta Core

        coreqa.connect();
        coreqa.prepareStatement(QUERY_CORE);
        System.out.println(coreqa.executeQueryAndGetRsAsList());
        coreqa.close();

        return resutaldo;
    }

}