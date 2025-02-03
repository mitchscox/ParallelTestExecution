Feature: Google Search
  Scenario: Search for "Oracle" on Google in Chrome and validate the top result
    Given I open "chrome"
    When I navigate to "https://www.google.com"
    And I search for "Oracle"
 #   Then the top result should be "https://www.oracle.com"
  Scenario: Search for "Microsoft" on Google in Chrome and validate the top result
    Given I open "chrome"
    When I navigate to "https://www.google.com"
    And I search for "Microsoft"
 #   Then the top result should be "https://www.microsoft.com"
  Scenario: Search for "Amazon" on Google in Chrome validate the top result
    Given I open "chrome"
    When I navigate to "https://www.google.com"
    And I search for "Amazon"
 #   Then the top result should be "https://www.amazon.com"
