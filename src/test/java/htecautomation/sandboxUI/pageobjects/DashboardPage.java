package htecautomation.sandboxUI.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * dashboard page or page that is visible after user is successfully logged
 */
public class DashboardPage extends PageObject {

    @FindBy(xpath = "//*[contains(text(), 'Logout')]")
    WebElementFacade logOutButton;

    @FindBy(xpath = "//*[@data-testid='use_cases_card_id']")
    WebElementFacade useCasesCard;

    public void dashBoardConfirmation () {
        logOutButton.waitUntilClickable();
    }

    public void navigateToUseCases () {
        useCasesCard.click();
    }
}
