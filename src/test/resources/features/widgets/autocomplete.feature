Feature: autocomplete
  Scenario Outline: test autocomplete for multiple color names field
    Given user is on autocomplete page
    When he types <letters> in multiple color names field
    Then colors containing those letters should appear
    And <colorsToBeSelected> from the list can be selected

    Examples:
    | letters | colorsToBeSelected |
    | r       | red,     yeey, zz |
    | i       | white, abcd, indigo |

    Scenario: test autocomplete for single color name field
      Given user is on autocomplete page
      When he types letters, for example 'g', in single color name field
      Then colors containing those letters should appear
      And user can select a color, for example 'magenta', from the list
