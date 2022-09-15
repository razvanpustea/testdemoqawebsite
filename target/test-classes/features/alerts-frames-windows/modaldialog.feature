Feature: modal dialog
  Scenario: test small modal button
    Given user is on modal-dialogs page
    When he clicks the 'Small modal' button
    Then he should see a modal with some text in it

    Scenario: test the large modal button
      Given user is on modal-dialogs page
      When he clicks the 'Large modal' button
      Then he should see a modal with some text in it