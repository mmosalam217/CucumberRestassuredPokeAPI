@Pokemon
Feature: Fetch Pokemon
  I want to get pokemon details by different search criteria.

  @PokemonById
  Scenario Outline: Fetch Pokemon by ID
    Given I call the Pokemon api via endpoint "/api/v2/pokemon/{pokemon_id}"
    When GET the Pokemon with ID "<pokemon_id>"
    Then Response should have status_code "200"
    And The Pokemon name should be "<name>"
    Examples:
	    | pokemon_id | name       |
	    | 1          | bulbasaur  |
	    | 35         | clefairy   |
	    | 5          | charmeleon |
	        
  @PokemonByName
  Scenario Outline: Fetch Pokemon by Name
    Given I call the Pokemon api via endpoint "/api/v2/pokemon/{pokemon_name}"
    When GET the Pokemon with name "<name>"
    Then Response should have status_code "200"
    And The Pokemon ID should be "<pokemon_id>"
   
    Examples:
	    | pokemon_id | name       |
	    | 1          | bulbasaur  |
	    | 35         | clefairy   |
	    | 5          | charmeleon |