package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;

public class BraveNewCoinAPISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I have a valid API key for the (.+) URI$")
    public void iSetTheRequestParams(String URI) {  //relaxedHTTPSValidation()... para llamar al server confiando plenamente en él
        request = given().relaxedHTTPSValidation().header("x-rapidapi-key", "7eabf4d9a7msh784ed699e843c64p19098fjsn15f22d9f086e")
                .header("x-rapidapi-host", "bravenewcoin.p.rapidapi.com").contentType(ContentType.JSON).baseUri(URI)
                .log().all();
    }

    @When("^I send a POST request with a valid (.+) payload to the (.+) endpoint$")
    public void sendPOSTRequest(String endpoint, String payload) {
        /*
         * String requestBody = Manera arcaica de hacer lo mismo que hace File requestBody
         * requestBody "{\n" + " \"audience\": \"https://api.bravenewcoin.com\",\n" +
         * " \"client_id\": \"oCdQoZoI96ERE9HY3sQ7JmbACfBf55RY\", \n" +
         * " \"grant_type\": \"client_credentials\"\n" + "}";
         */

        File requestBody = new File("src/test/resources/Payloads/" + payload + ".json");

        response = request.when().body(requestBody).post(endpoint).prettyPeek();
    }

    @Then("^I can validate I receive a valid token in the response$")
    public void validateTheToken() {

    }
}
