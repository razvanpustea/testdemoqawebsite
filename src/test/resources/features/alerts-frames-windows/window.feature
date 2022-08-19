Feature: open link in new window
  Scenario: open link in new window
    Given user is on window page
    When he clicks on 'New Window' button
    Then a new window with a page should open