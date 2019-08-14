package pageobjects;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaginaInicioPO extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password' and contains(@ng-model,'password')]")
    private WebElement password;

    @FindBy(id="idIngresar")
    private WebElement btnIngresar;

    public PaginaInicioPO() {
        super(DriverFactory.getDriver());
        //abrirPagina("http://200.14.169.120:8888/login/logout/platcom");
        abrirPagina("portalcomercial.qa.labchile.cl:8888/login/logout/platcom");
    }

    public void abrirPagina(String url){
        getDriver().get(url);
    }

    public void iniciarSesion(String user, String pass){
        waitForElementToAppear(username);
        username.sendKeys(user);
        password.sendKeys(pass);
        btnIngresar.click();
    }
}

