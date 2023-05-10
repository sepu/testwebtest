@Test
Feature: Basic test TechDay

Scenario: As a user I test the basic options of menu
    Given I am on webtest page
    And click in Charts
    And click in Tables
    And click in Forms
    Then type text in first field "TechDay"

