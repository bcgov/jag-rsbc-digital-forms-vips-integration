Feature: POST Review Timeslot

  @dev
  Scenario Outline: Test the POST endpoint of Review Timeslot
    Given the user has access to the Review Scheduling POST endpoint
    When the user submits a POST Review Timeslot Request for NoticeNumber "<noticeNumber>" CorrelationId "<correlationId>" ReviewStartDate "<reviewStartDate>" ReviewEndDate "<reviewEndDate>"
    Then the user should get a successful response from Review Scheduling POST endpoint
    Examples:
      | noticeNumber | correlationId | reviewStartDate            | reviewEndDate              |
      | 21900103     | test          | 2020-08-25 09:30:00 -07:00 | 2020-08-25 10:00:00 -07:00 |
      | 00312312     | test          | 2020-08-26 09:30:00 -07:00 | 2020-08-26 10:00:00 -07:00 |
      | 21900503     | test          | 2020-08-27 09:30:00 -07:00 | 2020-08-27 10:00:00 -07:00 |
