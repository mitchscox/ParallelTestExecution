Feature: Bing Edge Search
  Scenario: Search for "Oracle" on Bing in Edge and validate the top result
    Given I open "edge"
    When I navigate to "https://www.bing.com/"
    And I search for "Oracle"
    #Then the top result should be "https://www.oracle.com"