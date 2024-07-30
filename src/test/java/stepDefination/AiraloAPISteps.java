package stepDefination;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class AiraloAPISteps {

    private static String accessToken;
    private static String orderId;
    private Response response;

    @Given("I have the client credentials")
    public void i_have_the_client_credentials() {
        // Do nothing, credentials are hardcoded for now
    }

    @When("I request an access token")
    public void i_request_an_access_token() {
        final String TOKEN_ENDPOINT = "https://sandbox-partners-api.airalo.com/v2/token";
        final String CLIENT_ID = "974d515d41f86868eccd2d22aae8d10e";
        final String CLIENT_SECRET = "tILYEqQRq5PnZ5nccQZ1IiVugUWhZN2UveJZ9rVa";
        RestAssured.baseURI = TOKEN_ENDPOINT;

        response = RestAssured.given()
                .header("Accept", "application/json")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", CLIENT_ID)
                .formParam("client_secret", CLIENT_SECRET)
                .when()
                .post();

        Assert.assertEquals(response.getStatusCode(), 200);
        accessToken = response.jsonPath().getString("data.access_token");
        Assert.assertNotNull(accessToken);
    }

    @Then("I should receive a valid access token")
    public void i_should_receive_a_valid_access_token() {
        System.out.println("Access Token: " + accessToken);
    }

    @Given("I have a valid access token")
    public void i_have_a_valid_access_token() {
        Assert.assertNotNull(accessToken);
    }

    
    @When("I submit an order for 6 sim cards")
    public void i_submit_an_order_for_6_sim_cards() {
        final String ORDER_ENDPOINT = "https://sandbox-partners-api.airalo.com/v2/orders";
        RestAssured.baseURI = ORDER_ENDPOINT;

        response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/x-www-form-urlencoded")
                .formParam("quantity", 6)
                .formParam("package_id", "merhaba-7days-1gb")
                .formParam("type", "sim")
                .formParam("description", "6 sim merhaba-7days-1gb")
                .when()
                .post();

        Assert.assertEquals(response.getStatusCode(), 200);
        orderId = response.jsonPath().getString("data.id");
        Assert.assertNotNull(orderId);
    }

    @Then("the order should be submitted successfully")
    public void the_order_should_be_submitted_successfully() {
        System.out.println("Order submitted successfully: " + response.asPrettyString());
    }

    @And("I have submitted an order")
    public void i_have_submitted_an_order() {
        Assert.assertNotNull(orderId);
    }

    @When("I retrieve the eSIMs list")
    public void i_retrieve_the_eSIMs_list() {
        RestAssured.baseURI = "https://sandbox-partners-api.airalo.com/v2/sims";

        response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .queryParam("include", "order,order.status,order.user")
                .queryParam("limit", 100)
                .queryParam("page", 1)
                .when()
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("the order should be present in the list")
    public void the_order_should_be_present_in_the_list() {
    	if (response.getStatusCode() == 200) {  
            System.out.println("eSIMs: " + response.asPrettyString());
            if(response.jsonPath().getString("data.id").equals(orderId)) {
            	Assert.assertEquals(response.jsonPath().getString("data.id.simable.package_id"), "merhaba-7days-1gb");
            	Assert.assertEquals(response.jsonPath().getString("data.id.simable.quantity"), "6");           	
            	System.out.println("merhaba-7days-1gb order is present in list ");
            }
        } else {
            System.out.println("Error: " + response.getStatusCode() + " - " + response.getStatusLine());
            System.out.println("Response: " + response.asPrettyString());
            Assert.fail();
        }
    }
}
