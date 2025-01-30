Feature: Bing Edge Search
  Scenario: Search for "Oracle" on Bing and validate the top result
    Given I open Edge
    When I navigate to "https://www.bing.com/"
    And I search for "Oracle"
    #Then the top result should be "https://www.oracle.com"