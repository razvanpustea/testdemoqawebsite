Feature: prompt box
  Scenario Outline: click on a button to test the prompt box
    Given user is on prompt box page
    When he clicks on button next to 'prompt box'
    And enters a <name> inside the alert popup
    Then the <name> should be displayed on the page

    Examples:
    | name |
    | Sergiu |