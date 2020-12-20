package htecautomation.sandboxUI.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://qa-sandbox.apps.htec.rs")
public class HomePage extends PageObject {

    @FindBy(xpath = "//*[contains(text(), 'Login')]")
    WebElementFacade loginButton;

    /**
     * navigates user to login page
     */
    public void navigateToLoginPage() {
        loginButton.click();
    }
}


