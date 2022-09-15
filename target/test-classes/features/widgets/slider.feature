Feature: slider
  Scenario: test slider
    Given user is on slider page
    When he enters a value, for example 100
    Then the slider should move left or right until that value is reached