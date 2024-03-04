package api.definitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GameStepDefinitions {
	private RequestSpecification request;
	private Response response;
	private final String BASE_URI = "https://pokeapi.co/";
	
	@Given("I call the Generations via endpoint {string}")
	public void given_game_endpoint(String endpoint) {
		this.request = given().log().all().baseUri(BASE_URI).and().basePath(endpoint);
	}
	
	@Given("I call the Pokedex via endpoint {string}")
	public void given_pokedex_endpoint(String endpoint) {
		this.request = given().log().all().baseUri(BASE_URI).and().basePath(endpoint);
	}
	
	@When("REST GET the game by generation ID {string}")
	public void get_game_by_id(String id) {
		this.response = this.request
		.when()
		.pathParam("generation_id", id)
		.get();
	}
	
	@When("REST GET the game by pokedex ID {string}")
	public void get_game_by_pokedex(String id) {
		this.response = this.request
		.when()
		.pathParam("pokedex_id", id)
		.get();
	}
	
	@Then("Game Response should have status_code {string}")
	public void check_status_code(String status_code) {
		this.response.
		then()
		.statusCode(Integer.parseInt(status_code));
	}
	
	@Then("Response should contain the following species {string}")
	public void check_species(String species) {
		String[] expected_species = Arrays.asList(species.split(","))
				.stream().map(s -> s.trim())
				.collect(Collectors.toList())
				.toArray(new String[0]);
		this.response
		.then()
		.assertThat()
		.body("pokemon_species.name", hasItems(expected_species));
	}
	
	@Then("Descriptions should contain the following language {string}")
	public void descriptions_contain_language(String language) {
		this.response
		.then()
		.assertThat()
		.body(String.format("descriptions.find{ it.language.name == '%s'}.language.name", language), is(language));
	}
}
