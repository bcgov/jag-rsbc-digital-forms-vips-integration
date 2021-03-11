Feature: GET Disclosure Document

  @dev
  Scenario Outline: Test the GET endpoint of Disclosure Document
    Given the user has access to the Disclosure Document GET endpoint
    When the user submits a GET Disclosure Document Request for DocumentId "<documentId>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes MimeType "<mimeType>" and Document "<document>"
    Examples:
      | documentId | correlationId | mimeType        | document                                                                                |
      | 14         | test          | application/pdf | AzjAeJoIH7BQCxgDDfHFIuoYwAA1MsEKQplbmRzdHJlYW0KZW5kb2JqCnN0YXJ0eHJlZgoyMjY1NAolJUVPRg== |
      | 15         | test          | application/pdf | GAExI0MjI7cDMQCRscoTDGnpQwAoFIDdQplbmRzdHJlYW0KZW5kb2JqCnN0YXJ0eHJlZgoxNzA2MQolJUVPRg== |
#      | 16         | test          | application/pdf | DADY4UQEM9hIBYwVrzGFKsyYwAAEJYFXwplbmRzdHJlYW0KZW5kb2JqCnN0YXJ0eHJlZgozMTI4NgolJUVPRg== |
