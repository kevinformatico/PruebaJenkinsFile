package support.ui.interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IInput {
    void setValue(String value);
    String getValue();
    boolean isDisabled();
    WebElement getInputTag();
    void clear();
    boolean isError();
    List<WebElement> getVisiblesError();
}
