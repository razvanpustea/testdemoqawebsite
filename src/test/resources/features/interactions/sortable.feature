Feature: sort blocks
  Scenario: sort blocks from list
    Given user is on sortable page
    When he drags a block over another one, for example 'one' over 'five' - list
    Then they should be swapped

    Scenario: sort blocks from grid
      Given user is on sortable page
      And clicks on 'Grid' tab
      When he drags a block over another one, for example 'three' over 'seven' - grid
      Then they should be swapped