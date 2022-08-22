Feature: confirm box
  Scenario: click on a button to test the confirm box
    Given user is on confirm box page
    When he clicks on the button next to to 'confirm box will appear'
    Then a confirm box should appear and user can select 'Cancel' or 'OK'
    And when he selects 1 value, it should be displayed