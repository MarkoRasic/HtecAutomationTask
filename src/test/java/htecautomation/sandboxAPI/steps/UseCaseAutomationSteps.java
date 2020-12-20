package htecautomation.sandboxAPI.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import htecautomation.sandboxAPI.apicalls.LoginApi;
import htecautomation.sandboxAPI.apicalls.UseCaseApi;
import net.thucydides.core.annotations.Steps;

public class UseCaseAutomationSteps {

    @Steps
    LoginApi loginApi;
    @Steps
    UseCaseApi useCaseApi;

    @Given("User is logged in")
    public void getAuthToken() {
            loginApi.loginAndSetToken();
    }

    @Given("User creates {int} new use cases")
    public void createUseCase(int numberOfUseCases) {
        useCaseApi.createNumberOfUseCases(numberOfUseCases);
    }

    @And("Assert that new use cases are created")
    public void assertThatNewUseCasesAreCreated() {
        useCaseApi.getUseCaseByID();
    }

    @When("User updates newly created use cases")
    public void userUpdatesNewlyCreatedUseCases() {
        useCaseApi.updateUseCases();
    }

    @Then("changes made by user can be seen on updated use cases")
    public void changesMadeByUserCanBeSeenOnUpdatedUseCases() throws Exception {
        useCaseApi.confirmThatUseCasesAreUpdated();
    }
}
