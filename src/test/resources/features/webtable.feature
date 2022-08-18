Feature: Web table
  Scenario Outline: Add multiple rows to the table
    Given user in on webtables page
    When he clicks on Add button
    And inserts data - <firstName> <lastName> <age> <email> <salary> <department> - into the fields
    And presses the Submit button from webtables page
    Then a new row should be added to the table

    Examples:
    | firstName | lastName | age | email | salary | department |
    | Razvan | Pustea | 21 | razvan.pustea@yahoo.com | 3500 | IT |
    | Sergiu | Woau | 32 | sergiu.woau@yahoo.com  | 7000 | Marketing |