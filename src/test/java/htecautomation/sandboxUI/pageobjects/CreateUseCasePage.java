package htecautomation.sandboxUI.pageobjects;

import htecautomation.sandboxUI.utility.helper.HtecUiUtils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


import java.util.ArrayList;
import java.util.List;

import static htecautomation.sandboxUI.utility.constants.TestDataConstants.setTitleFieldsToList;

/**
 * Create use case page, all relevant elements and methods regarding manipulating with use cases can be found here
 */
public class CreateUseCasePage extends PageObject {

    UseCasesPage useCasesPage;

    @FindBy(xpath = "//*[@name='title']")
    WebElementFacade inputTitleField;

    @FindBy(xpath = "//*[@name='description']")
    WebElementFacade inputDescriptionField;

    @FindBy(xpath = "//*[@name='expected_result']")
    WebElementFacade inputExpectedResultField;

    @FindBy(xpath = "//*[@class='pt-1 ml-2']")
    WebElementFacade isAutomatedSwitchToggle;

    @FindBy(xpath = "//*[@id='stepId']")
    WebElementFacade useCaseStepInputField;

    @FindBy(xpath = "//*[@type='submit']")
    WebElementFacade buttonSubmit;


    /**
     * Will create new use case with random values as inputs
     * And also will store title field value to list
     */
    public void createNewUseCase () {

        useCasesPage.createUseCaseButton.waitUntilClickable();
        useCasesPage.createUseCaseButton.click();
        inputTitleField.waitUntilVisible();
        inputTitleField.sendKeys(HtecUiUtils.randomString());
        inputDescriptionField.sendKeys(HtecUiUtils.randomString());
        inputExpectedResultField.sendKeys(HtecUiUtils.randomString());
        isAutomatedSwitchToggle.click();
        useCaseStepInputField.sendKeys(HtecUiUtils.randomString());
        buttonSubmit.click();

        setTitleFieldsToList.add(inputTitleField.getAttribute("value"));
    }

    /**
     * method that will take previous values of inputs and update it with new description
     */
    public void updateExistingUseCases () {

        List<WebElementFacade> getFieldLengthAndUpdateIt = new ArrayList<>();
        getFieldLengthAndUpdateIt.add(inputTitleField);
        getFieldLengthAndUpdateIt.add(inputDescriptionField);
        getFieldLengthAndUpdateIt.add(inputExpectedResultField);
        getFieldLengthAndUpdateIt.add(useCaseStepInputField);

        for (WebElementFacade element : getFieldLengthAndUpdateIt) {
            String locatorField = element.getAttribute("value");
            int fieldLength = locatorField.length();
            element.clear();
            element.sendKeys("This field previously had " + fieldLength + " characters");
        }

        buttonSubmit.waitUntilClickable();
        buttonSubmit.click();

    }
}
