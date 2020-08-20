Feature: Test ORDS

  Scenario: Testing the ORDS Health check
    Given the user can connect to ORDS web health
    When the user hits the api
    Then the user gets success