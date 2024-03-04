@Game
Feature: Game feature
  I want to get different games information

  @GameByGeneration
  Scenario: Fetch Pokemon games based on the Pokemons generations
    Given I call the Generations via endpoint "/api/v2/generation/{generation_id}"
    When REST GET the game by generation ID "1"
    Then Game Response should have status_code "200"
    And Response should contain the following species "bulbasaur, charmander, squirtle"
    
  @GamePokedex
  Scenario: Check games Pokedex
    Given I call the Pokedex via endpoint "/api/v2/pokedex/{pokedex_id}"
    When REST GET the game by pokedex ID "2"
    Then Game Response should have status_code "200"
    And Descriptions should contain the following language "de"
    