Feature: none
  Scenario: Insert data into text boxes
    Given user is on the textbox webpage
    When he inserts data into text boxes
    And presses the Submit button
    Then he should see what he had entered previously