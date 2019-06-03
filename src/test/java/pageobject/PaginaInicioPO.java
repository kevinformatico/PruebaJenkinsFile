package pageobject;

import driver.DriverFactory;
import driver.SharedDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.concurrent.TimeUnit;

public class PaginaInicioPO extends LoadableComponent<PaginaInicioPO> {

    private String URL_CDN = "http://200.14.169.120:8888/login/logout/platcom";


    public PaginaInicioPO() {
        DriverFactory.getDriver().get(URL_CDN);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    //Aqui puedes declarar los elementos y las funciones necesarias.


    @FindBy(id = "username")
    WebElement username;

    @FindBy(xpath = "//input[@name='password' and contains(@ng-model,'password')]")
    WebElement password;

    @FindBy(id="idIngresar")
    WebElement btnIngresar;

    public EscritorioComercialPO iniciarSesion(String user, String pass){
        username.sendKeys(user);
        password.sendKeys(pass);
        btnIngresar.click();
        return new EscritorioComercialPO();
    }





    //Metodos para validar la carga de la pagina 
    @Override
    protected void load() {
        System.out.println("Cargando la pagina de inicio");
    }

    @Override
    protected void isLoaded() throws Error {
        System.out.println("Validadndo si esta cargada la pagina de inicio");
    }
}

