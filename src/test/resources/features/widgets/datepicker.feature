Feature: date picker

  Scenario: Select date from given input
    Given user is on date picker page
    When he selects a date, for example '07.12.1992'
    Then it should be selected

  Scenario: Select date and time from given input
    Given user is on date picker page
    When he selects a date and a time, for example '25/12/2125' and '11:15'
    Then these should be selected