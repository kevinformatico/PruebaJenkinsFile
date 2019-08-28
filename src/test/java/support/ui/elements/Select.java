package support.ui.elements;

import support.ui.interfaces.ISelect;
import support.ui.throwable.UnexpectedClassNameException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Select extends Input implements ISelect {

    private final WebElement element;
    private final boolean isMulti;

    /**
     * Constructor. Verifica que el WebElement sea de la plataforma comercial, (div --> input.select). De lo contrario,
     * se lanzara el error UnexpectedTagNameException.
     *
     * @param element SELECT element to wrap
     * @throws UnexpectedTagNameException when element is not a SELECT
     */
    public Select(WebElement element) {
        super(element);
        String className =  element.findElement(By.tagName("input")).getAttribute("class");
        if(null==className|| !className.toLowerCase().contains("select")){
            throw new UnexpectedClassNameException("select", className);
        }

        this.element = element;
        String value = element.getAttribute("multiple");

        isMulti = (value != null && !"false".equals(value));
    }

    public Select(Input element){
        super(element.getWrappedElement());
        String className =  element.getWrappedElement().findElement(By.tagName("input")).getAttribute("class");
        if(null==className|| !className.toLowerCase().contains("select")){
            throw new UnexpectedClassNameException("select", className);
        }

        this.element = element.getWrappedElement();
        String value = element.getWrappedElement().getAttribute("multiple");

        isMulti = (value != null && !"false".equals(value));
    }


    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    public boolean isMultiple() {
        return isMulti;
    }

    public List<WebElement> getOptions() {
        if(!isExpanded()) expand();
        return element.findElements(
                By.xpath(".//ul[contains(@class,'ui-select-choices')]//li[contains(@class,'ui-select-choices-group')]//div[contains(@class,'ui-select-choices-row')]"));
    }

    public List<String> getTextOptions(){
        return getOptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public WebElement expand(){
        element.findElement(By.xpath(".//i[contains(@class,'caret pull-right') and contains(@ng-click,'')]")).click();
        return element;
    }

    public boolean isExpanded(){
        return element.findElement(By.xpath("./div[contains(@class,'ui-select-container')]")).getAttribute("class").contains("open");
    }

    public WebElement getSelectedOption(){
        return element.findElement(By.xpath(".//span[contains(@class,'ui-select-match-text')]/span"));
    }

    public void selectByVisibleText(String s) {
        try {
            getOptions().stream().filter((e)-> e.getText().contains(s))
                    .collect(Collectors.toList()).get(0).click();
        }catch (IndexOutOfBoundsException e){
            throw new NoSuchElementException(String.format("No se encuentra el producto %s \n Productos: %s",s,
                    getOptions().stream().map(WebElement::getText).collect(Collectors.toList())));
        }
    }

    public void selectByValue(String s) {
        getOptions().stream().limit(1).filter((e)-> e.getText().equals(s))
                .collect(Collectors.toList()).get(0).click();
    }

    public void selectByIndex(int i) {
        getOptions().get(i).click();
    }

    public void selectByRandom(){
        getOptions().get((int)(Math.random()*(getOptions().size()+1))).click();
    }

    public void selectFirstValue(){
        getOptions().get(0).click();
    }
}
