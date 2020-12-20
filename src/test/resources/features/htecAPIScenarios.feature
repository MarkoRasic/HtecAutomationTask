Feature: HTEC use cases automation

  Background: User is logged and token is acquired
    Given User is logged in

  Scenario: Create and update use cases
    Given User creates 4 new use cases
    And Assert that new use cases are created
    When User updates newly created use cases
    Then changes made by user can be seen on updated use cases