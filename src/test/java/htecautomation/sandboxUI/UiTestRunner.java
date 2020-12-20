package htecautomation.sandboxUI;


import htecautomation.sandboxUI.steps.LoggingToSandbox;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class UiTestRunner {

    @Steps
    LoggingToSandbox user;

    @Managed
    WebDriver driver;
    @Test
    public void userShouldBeAbleToCreateAndUpdateNumberOfUseCases() {
        user.navigateToLoginPage();
        user.tryToLogin();
        user.navigateToCreateUseCase();
        user.createUseCase(4);
        user.verifyNewUseCases();
        user.updateUseCases();
    }
}
