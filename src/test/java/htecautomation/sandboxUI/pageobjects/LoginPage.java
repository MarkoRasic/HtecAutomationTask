package htecautomation.sandboxUI.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import htecautomation.sandboxUI.utility.constants.TestDataConstants;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//*[@name='email']")
    WebElementFacade usernameField;

    @FindBy(xpath = "//*[@name='password']")
    WebElementFacade passwordField;

    @FindBy(xpath = "//*[@type='submit']")
    WebElementFacade submitButton;

    /**
     * method for logging in to sandbox, different users can provide their own credentials here and test the code
     */
    public void userProvidesCredentials () {
        usernameField.waitUntilClickable().then().type(TestDataConstants.EMAIL);
        passwordField.waitUntilClickable().then().type(TestDataConstants.PASSWORD);
        submitButton.click();
    }

}
