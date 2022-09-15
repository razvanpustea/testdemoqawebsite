Feature: alert
  Scenario: click on a button to test the alert
    Given user is on alerts page
    When he clicks on the button next to 'Click Button to see alert'
    Then an alert should appear on the screen and user can exit it by clicking 'OK'

    Scenario: click on a button to test the delayed alert
      Given user is on alerts page
      When he clicks on a button next to 'On button click, alert will appear after 5 seconds'
      Then an alert should appear on the screen and user can exit it by clicking 'OK'