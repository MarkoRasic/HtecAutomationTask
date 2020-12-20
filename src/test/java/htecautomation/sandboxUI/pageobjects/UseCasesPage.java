package htecautomation.sandboxUI.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case page, all relevant elements and methods regarding all created use cases can be found here
 */
public class UseCasesPage extends PageObject {

    @FindBy(xpath = "//*[@data-testid='create_use_case_btn']")
    WebElementFacade createUseCaseButton;

    public static final String USE_CASES_LIST = "//*[@class = 'list-group-item list-group-item-action']";

    public List<String> getUseCases() {

        return findAll(USE_CASES_LIST).stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }
}
