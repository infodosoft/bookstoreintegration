package book.store.api.v1.steps;

import book.store.api.v1.store.BooksService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static book.store.api.v1.BaseApi.getSharedApiTestData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;

public class CommonSteps {
    public static Response latestResponse;
    protected BooksService booksService = new BooksService();
    public static List<Map<String, Object>> responseBodyList ;
    public static Map<String, Object> responseBody ;

    @Given("the bookstore API is running")
    public void the_api_is_running() {
      assertEquals(
              booksService.the_api_is_running().getStatusCode(),
              200,
              "the bookstore API is running"
      );
    }

    @Then("^the response status code should be (.*) from the service$")
    public void the_response_status_code_should_be(int statusCode) {
       latestResponse.then().statusCode(statusCode);
    }

    @When("^User retrieves a list of (.*) in store$")
    public void userRetrievesAListOfInStore(String type) {
        latestResponse = booksService.userRetrievesAListOfInStore(type);
    }

    @And("^The list of (.*) is not empty$")
    public void theListOfIsNotEmpty(String type) {
     responseBodyList = booksService.jsonToObject.listObject( latestResponse.body().asString() );
     assertFalse(responseBodyList.isEmpty());
    }

    @When("^User retrieves a (.*) details in store$")
    public void userRetrievesADetailsInStore(String type) {
        latestResponse = booksService.userRetrievesADetailsInStore(type,responseBodyList);
    }

    @And("^The details of (.*) is not empty$")
    public void theDetailsOfIsNotEmpty(String type) {
        responseBody = booksService.jsonToObject.toObject( latestResponse.body().asString() );
        assertFalse(responseBody.isEmpty());
    }

    @When("^User adds a new (.*) in the store$")
    public void userAddsANewInTheStore(String type) {
        latestResponse = booksService.userAddsANewInTheStore(type);
    }

    @And("^the response should contain the updated (.*) in store$")
    public void theResponseShouldContainTheUpdatedInStore(String type) {
        latestResponse.then().body(type.contains("books") ? "title" : "firstName", equalTo(getSharedApiTestData().get(type).toString()));
    }

    @When("^User update an existing (.*) in the store$")
    public void userUpdateAnExistingInTheStore(String type) {
        latestResponse = booksService.userUpdateAnExistingInTheStore(type, responseBodyList);
    }

    @When("^User deletes an existing (.*) in the store$")
    public void userDeletesAnExistingInTheStore(String type) {
        latestResponse = booksService.userDeletesAnExistingInTheStore(type, responseBodyList);
    }

    @And("^the response should not contain the updated (.*) in store$")
    public void theResponseShouldNotContainTheUpdatedInStore(String type) {
        assertTrue(latestResponse.getBody().asString().isEmpty());
    }

    @When("^User deletes an existing (.*) in the store using invalid (.*) id$")
    public void userDeletesAnExistingInTheStoreUsingInvalidId(String type, String id) {
      latestResponse = booksService.userDeletesAnExistingInTheStoreUsingInvalidId(type, id);
    }

    @When("^User retrieves a (.*) details in store using invalidId (.*) id$")
    public void userRetrievesADetailsInStoreUsingInvalidId(String type, String id) {
        latestResponse = booksService.userRetrievesADetailsInStoreUsingInvalidId(type,id);
    }

    @When("^User adds a new (.*) in the store using invalid (.*) payload$")
    public void userAddsANewInTheStoreUsingInvalidPayload(String type, String payload) {
        latestResponse = booksService.userAddsANewInTheStoreUsingInvalidPayload(type,payload);
    }

    @And("^the response should contain the (.*) created$")
    public void theResponseShouldContainTheCreated(String type) {
        String savedTitle = getSharedApiTestData().get(type).toString();
        latestResponse.then()
                .body("id", notNullValue())
                .body(
                        type.contains("books") ? "title" : "firstName", equalTo(savedTitle.contains("null") ? null : savedTitle));
    }
}
