Feature: Textbox
  Scenario: Insert data into text boxes
    Given user is on the textbox webpage
    When he inserts data into text boxes
    And presses the Submit button from textbox page
    Then he should see what he had entered previously