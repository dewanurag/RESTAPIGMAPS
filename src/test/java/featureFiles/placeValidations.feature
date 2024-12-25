#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@GMaps
Feature: Google Maps API Mockup
  I want to register, deregister, fetch a location detail

  @AddPlace
  Scenario: Adding a location on the map
    Given I have add location payload
    When I call "addPlaceAPI" with POST HTTP request
    Then I get success message with status code 200
    And value of "status" in response body is "OK"
    And value of "scope" in response body is "APP"

  @AddPlaceDyn
  Scenario Outline: Adding a location on the map
    Given I set payload with <location lat>, <location lng>, <location accuracy>, "<address>", "<language>", "<name>"
    When I call "addPlaceAPI" with "POST" HTTP request
    Then I get success message with status code 200
    And value of "status" in response body is "OK"
    And value of "scope" in response body is "APP"
    And I verify placeId created with "<name>" using "getPlaceAPI"

    Examples: 
      | location lat | location lng | location accuracy | address         | language | name        |
      |      37.7749 |    -122.4194 |                50 | 123 Main St, SF | English  | Golden Gate |

  @AddPlaceDyn
  Scenario Outline: Adding a location on the map dynamically
    Given I set payload with <location lat>, <location lng>, <location accuracy>, "<address>", "<language>", "<name>"
    When I call "addPlaceAPI" with "POST" HTTP request
    Then I get success message with status code 200
    And value of "status" in response body is "OK"
    And value of "scope" in response body is "APP"

    Examples: 
      | location lat | location lng | location accuracy | address           | language | name         |
      |      37.7749 |    -122.4194 |                50 | 123 Main St, SF   | English  | Golden Gate  |
      |      40.7128 |     -74.0060 |                20 | 456 Wall St, NY   | English  | Wall Street  |
      |      48.8566 |       2.3522 |                10 | 789 Champs, Paris | French   | Eiffel Tower |
      |      35.6895 |     139.6917 |                30 | 101 Tokyo Tower   | Japanese | Tokyo Tower  |

  @DeletePlace
  Scenario: Deleting a location on a map
    Given I have delete location payload
    When I call "deletePlaceAPI" with "POST" HTTP request
    Then I get success message with status code 200
    And value of "status" in response body is "OK"
 
