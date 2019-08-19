package Generics.support.ui;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface ISelect {

    boolean isMultiple();

    List<WebElement> getOptions();

    WebElement expand();

    boolean isExpanded();

    WebElement getSelectedOption();

    void selectByVisibleText(String s);

    void selectByIndex(int i);

    void selectByValue(String s);
}
