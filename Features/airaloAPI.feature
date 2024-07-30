Feature: Airalo API Tests

  Scenario: Get Access Token
    Given I have the client credentials
    When I request an access token
    Then I should receive a valid access token


  Scenario: Submit Order
    Given I have a valid access token
    When I submit an order for 6 sim cards
    Then the order should be submitted successfully


  Scenario: Retrieve eSIMs List
    Given I have a valid access token
    And I have submitted an order
    When I retrieve the eSIMs list
    Then the order should be present in the list