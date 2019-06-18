package pageobjects;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AsideGarantiasEmpresaPO extends BasePage{

    public AsideGarantiasEmpresaPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//a[@class='link-icon ml-0 mr-0 pull-right mt-5']")
    WebElement linkIrAGarantiasVigentesEHistoricas;

    public void clickIrAGarantiasVigentesEHistoricas(){
        linkIrAGarantiasVigentesEHistoricas.click();
    }


}
