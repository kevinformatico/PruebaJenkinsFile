package pageobjects;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EscritorioComercialPO extends BasePage {


    @FindBy(id="rut")
    private WebElement inputRut;
    @FindBy(name="searchButton")
    private WebElement searchButton;

    public EscritorioComercialPO() {
        super(DriverFactory.getDriver());
    }

    public void buscarPorRut(String rut){
        waitForElementToAppear(inputRut);
        inputRut.sendKeys(rut);
        searchButton.click();
    }

}

