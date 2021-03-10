Feature: SET Disclosure Document

  @dev
  Scenario Outline: Test the PATCH endpoint of Disclosure Document
    Given the user has access to the Disclosure Document PATCH endpoint
    When the user submits a PATCH Disclosure Document Request having DocumentId "<documentId>" Disclosed DateTime "<disclosedDtm>" CorrelationId "<correlationId>"
    Then the user should get a successful response for PATCH Disclosure Document
    Examples:
      | documentId | disclosedDtm               | correlationId |
      | 14         | 2018-06-27 00:00:00 -07:00 | test          |
      | 15         | 2018-06-28 00:00:00 -07:00 | test          |
      | 16         | 2018-06-29 00:00:00 -07:00 | test          |
