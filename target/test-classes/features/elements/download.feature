Feature: download
  Scenario: Download a file
    Given user is on download page
    When he clicks on 'Download' button
    Then the file should be saved on his computer