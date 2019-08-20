package support.ui.interfaces;

import org.openqa.selenium.WebElement;

public interface IInput {
    void setValue(String value);
    String getValue();
    boolean isDisabled();
    WebElement getInputTag();
}
