Feature: DuckDuckGo Firefox Search
  Scenario: Search for "Oracle" on DuckDuckGo in Firefox and validate the top result
    Given I open "firefox"
    When I navigate to "https://www.duckduckgo.com"
    And I search for "Oracle"
 #   Then the top result should be "https://www.oracle.com"