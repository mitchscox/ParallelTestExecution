Feature: Google Search
  Scenario: Search for "Oracle" on Google and validate the top result
    Given I open Chrome
    When I navigate to "https://www.google.com"
    And I search for "Oracle"
 #   Then the top result should be "https://www.oracle.com"