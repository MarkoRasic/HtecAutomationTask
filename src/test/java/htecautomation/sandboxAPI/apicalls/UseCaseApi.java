package htecautomation.sandboxAPI.apicalls;

import htecautomation.sandboxAPI.constants.BaseData;
import htecautomation.sandboxAPI.pojo.UseCaseObjects;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import htecautomation.sandboxUI.utility.helper.HtecUiUtils;

import static org.assertj.core.api.Assertions.assertThat;


import static htecautomation.sandboxAPI.constants.ApiTestSharedConstants.*;


import java.util.Collections;

/**
 * class containing all API calls to the use cases endpoint
 */

public class UseCaseApi extends BaseData {


    int useCaseNumber;

    /**
     * This method will create and populate payload for new use case body
     * Post method will create new use case
     * After, method getAllUseCases is called which is used to extract first use case id and store it to list
     * @param numberOfUseCases number of use cases to be created
     */

    public void createNumberOfUseCases(int numberOfUseCases) {


        useCaseNumber = numberOfUseCases;

        for (int i = 0; i < numberOfUseCases; i++) {

            UseCaseObjects createNewUseCasePayload = new UseCaseObjects();
            createNewUseCasePayload.setAutomated(true);
            createNewUseCasePayload.setTitle(HtecUiUtils.randomString());
            createNewUseCasePayload.setDescription(HtecUiUtils.randomString());
            createNewUseCasePayload.setExpected_result(HtecUiUtils.randomString());
            createNewUseCasePayload.setTeststeps(Collections.singletonList(HtecUiUtils.randomString()));

            Response response = SerenityRest
                    .with().log().all()
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .contentType(ContentType.JSON)
                    .body(createNewUseCasePayload)
                    .baseUri(BASE_URL)
                    .post("/api/usecases/usecase")
                    .andReturn().prettyPeek();

            response.then().statusCode(200);

            useCasesTitles.add(response.getBody().path("title"));

            Response getAllUseCases = SerenityRest
                    .with().log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .baseUri(BASE_URL)
                    .get("/api/usecases/all")
                    .andReturn().prettyPeek();

            getAllUseCases.then().statusCode(200);

            useCaseIDs.add(getAllUseCases.getBody().path("[0].usecase_id"));
        }
    }

    /**
     * method used for assertion that we have created use cases with params we provided
     */
    public void getUseCaseByID() {

        for (int i = 0; i < useCaseNumber; i++) {

            Response response = SerenityRest
                    .with().log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .baseUri(BASE_URL)
                    .get("/api/usecases/" + useCaseIDs.get(i))
                    .andReturn().prettyPeek();

            response.then().statusCode(200);

            UseCaseObjects newUseCase = response.getBody().as(UseCaseObjects.class);

            assertThat(newUseCase.getUsecase_id()).isEqualTo(useCaseIDs.get(i));
            assertThat(newUseCase.getTitle()).isEqualTo(useCasesTitles.get(i));

        }

    }

    /**
     * method which will get use case by its ID, take use case payload values and store it inside pojo class
     * extracting length of newly added objects and passing value if length to the put method
     */
    public void updateUseCases() {

        for (int i = 0; i < useCaseNumber; i++) {

            Response response = SerenityRest
                    .with().log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .baseUri(BASE_URL)
                    .get("/api/usecases/" + useCaseIDs.get(i))
                    .andReturn().prettyPeek();

            response.then().statusCode(200);

            UseCaseObjects updateResponseValues = response.getBody().as(UseCaseObjects.class);

            int titleLength = updateResponseValues.getTitle().length();
            int descriptionLength = updateResponseValues.getTitle().length();
            int expectedLength = updateResponseValues.getDescription().length();
            int stepLength = updateResponseValues.getTeststeps().get(0).length();


            updateResponseValues.setTitle("This field previously had " + titleLength + " characters");
            updateResponseValues.setDescription("This field previously had " + descriptionLength + " characters");
            updateResponseValues.setExpected_result("This field previously had " + expectedLength + " characters");
            updateResponseValues.setTeststeps(Collections.singletonList("This field previously had " + stepLength + " characters"));


            Response updateUseCases = SerenityRest
                    .with().log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .baseUri(BASE_URL)
                    .body(updateResponseValues)
                    .put("/api/usecases/usecase/" + useCaseIDs.get(i)).prettyPeek();

            updateUseCases.then().statusCode(200);
            updatedUseCasesTitles.add(updateUseCases.getBody().path("title"));
        }
    }

    /**
     * method used for assertion that what we did in update use case is applied to use cases
     */
    public void confirmThatUseCasesAreUpdated() throws Exception{

        for (int i = 0; i < useCaseNumber; i++) {

            Response response = SerenityRest
                    .with().log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Bearer " + Serenity.sessionVariableCalled(BEARER_TOKEN))
                    .baseUri(BASE_URL)
                    .get("/api/usecases/" + useCaseIDs.get(i))
                    .andReturn().prettyPeek();

            if (response.statusCode() == 200) {
                UseCaseObjects updatedUseCaseBody = response.getBody().as(UseCaseObjects.class);
                assertThat(updatedUseCaseBody.getTitle()).isEqualTo(updatedUseCasesTitles.get(i));

            } else if (response.statusCode() == 403) {
                throw new Exception("There are no use cases for this user");
            }
            else if (response.statusCode() == 401) {
                throw new Exception("Your token is expired. Unauthorised");
            }
            else { throw new Exception("DevOps did it again");
            }
        }
    }
}

