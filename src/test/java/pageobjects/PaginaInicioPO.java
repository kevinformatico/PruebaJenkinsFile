package pageobjects;

import Managers.driver.DriverFactory;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaginaInicioPO extends BasePage {

    @FindBy(id = "username")
    private
    WebElement username;

    @FindBy(xpath = "//input[@name='password' and contains(@ng-model,'password')]")
    private
    WebElement password;

    @FindBy(id="idIngresar")
    private
    WebElement btnIngresar;

    public PaginaInicioPO() {
        super(DriverFactory.getDriver());
        DriverFactory.getDriver().get("http://200.14.169.120:8888/login/logout/platcom");
    }

    public void iniciarSesion(String user, String pass){
        System.out.println("URL ACTUAL");
        System.out.println(DriverFactory.getDriver().getCurrentUrl());
        waitForElementToAppear(username);
        username.sendKeys(user);
        password.sendKeys(pass);
        btnIngresar.click();
    }
}

