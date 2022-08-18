Feature: upload a file
  Scenario: upload a file
    Given user is on upload page
    When he selects a file
    Then the path of the file should appear on the page