Feature: Health checkup

  @dev
  Scenario: Health checkup for VIPS and Digital Forms services
    Given the user has access to the health check api
    When the user connects to Digital Forms ping service
    Then the user should get a successful health status response
