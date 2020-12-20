package htecautomation.sandboxUI.steps;

import htecautomation.sandboxUI.pageobjects.*;
import htecautomation.sandboxUI.utility.constants.TestDataConstants;
import net.thucydides.core.annotations.Step;
import htecautomation.sandboxUI.utility.helper.HtecUiUtils;

import java.util.List;


public class LoggingToSandbox {

    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    UseCasesPage useCasesPage;
    CreateUseCasePage createUseCasePage;
    HtecUiUtils htecUiUtils;

    @Step("User navigates to HtecSandbox login page")
    public void navigateToLoginPage() {
        homePage.open();
        homePage.navigateToLoginPage();
    }

    @Step("User enters his credentials and clicks submit")
    public void tryToLogin() {
        loginPage.userProvidesCredentials();
        dashboardPage.dashBoardConfirmation();
    }

    @Step("User navigates to Create use case page")
    public void navigateToCreateUseCase() {
        dashboardPage.navigateToUseCases();
    }

    @Step("User creates new use case")
    public void createUseCase(int numberOfUseCases) {
        TestDataConstants.setNoOfUseCases(numberOfUseCases);
        for (int i = 0; i < numberOfUseCases; i++) {
            createUseCasePage.createNewUseCase();
        }
    }

    @Step("User should see newly created use cases")
    public void verifyNewUseCases() {
        List<String> listResults = useCasesPage.getUseCases();

        for (int i = 0; i < TestDataConstants.getNoOfUseCases(); i++) {
            htecUiUtils.confirmThatIsVisible(listResults.get(i));
        }
    }

    @Step("User is able to update all created use cases")
    public void updateUseCases() {
        List<String> listResults = useCasesPage.getUseCases();

        for (int i = 0; i < TestDataConstants.getNoOfUseCases(); i++) {
            htecUiUtils.createLocator(listResults.get(i));
            createUseCasePage.updateExistingUseCases();
        }
    }
}
