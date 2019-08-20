package support.ui.throwable;

import org.openqa.selenium.WebDriverException;

public class UnexpectedClassNameException extends WebDriverException {
    public UnexpectedClassNameException(String expectedClassName, String actualClassName){
        super(String.format("Element should have been \"%s\" but was \"%s\"", expectedClassName, actualClassName));
    }
}
