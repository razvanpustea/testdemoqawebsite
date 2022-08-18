Feature: buttons
  Scenario: double click a button
    Given user is on buttons page
    When he double clicks the button 'Double Click Me'
    Then he should see a text saying he had done a double click

    Scenario: right click a button
      Given user is on buttons page
      When he right clicks on the button 'Right Click Me'
      Then he should see a text saying he had done a right click

      Scenario: click a button
        Given user is on buttons page
        When he clicks on the button 'Click Me'
        Then he should see a text saying he had done a dynamic click