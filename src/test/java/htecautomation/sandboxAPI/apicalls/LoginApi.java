package htecautomation.sandboxAPI.apicalls;

import htecautomation.sandboxAPI.pojo.LoginObjects;
import htecautomation.sandboxAPI.constants.BaseData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.Serenity;

import static htecautomation.sandboxAPI.constants.ApiTestSharedConstants.BEARER_TOKEN;

public class LoginApi extends BaseData {
    /**
     *  method which aims for login endpoint
     *  requires users username and password to be provided in payload
     *  extracts bearer token from response and set it as enum variable
     */
    public void loginAndSetToken() {

        LoginObjects populateLoginForm = new LoginObjects();
        populateLoginForm.setEmail("markorasic62@gmail.com");
        populateLoginForm.setPassword("Sifra123!");

        Response response = SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(populateLoginForm)
                .post("/api/users/login");

        response.then().statusCode(200);

        Serenity.setSessionVariable(BEARER_TOKEN).to(response.getBody().path("token"));

    }
}
