package api.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PokemonStepDefinition {
	
	private RequestSpecification request;
	private Response response;
	private final String BASE_URI = "https://pokeapi.co/";
	
	@Given("I call the Pokemon api via endpoint {string}")
	public void given_pokemon_endpoint(String endpoint) {
		this.request = given().log().all().baseUri(BASE_URI).and().basePath(endpoint);
		
	}
	
	@When("GET the Pokemon with ID {string}")
	public void get_pokemon_by_id(String id) {
		this.response = this.request
		.when()
		.pathParam("pokemon_id", id)
		.get();
	}
	
	@When("GET the Pokemon with name {string}")
	public void get_pokemon_by_name(String pokemon_name) {
		this.response = this.request
		.when()
		.pathParam("pokemon_name", pokemon_name)
		.get();
	}
	
	@Then("Response should have status_code {string}")
	public void check_status_code(String status_code) {
		this.response.
		then()
		.statusCode(Integer.parseInt(status_code));
	}
	
	@Then("The Pokemon name should be {string}")
	public void check_pokemon_name(String name) {
		this.response
		.then()
		.assertThat()
		.body("forms.name", hasItems(name));
	}
	
	@Then("The Pokemon ID should be {string}")
	public void check_pokemon_id(String pokemon_id) {
		this.response
		.then()
		.assertThat()
		.body("id", is(Integer.parseInt(pokemon_id)));
	}
}
