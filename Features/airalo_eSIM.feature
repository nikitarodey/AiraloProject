Feature: Purchase eSIM package from Airalo website

  Scenario Outline: Search and purchase a Japan eSIM package
  
    Given User open the browser and navigate to Airalo website
    When User search for "<location>" in the search field on the home page
    And User select the "<location>" destination from the Local section in the autocomplete options
    Then User should see the list of eSIM packages available for "<location>"
    And User choose the first eSIM package and click on "Buy Now" button
    Then a popup should appear with the package details
    And User should be able to validate below package details:
       |  Title      | Coverage | Data   | Validity | Price |
       | Moshi Moshi | Japan    | 1 GB   | 7 Days   | $4.50 USD |

      
      
      Examples:
    | location | title      | coverage | data | validity | price |
    | Japan    | Moshi Moshi| Japan    | 1 GB | 7 days   | $4.50 |