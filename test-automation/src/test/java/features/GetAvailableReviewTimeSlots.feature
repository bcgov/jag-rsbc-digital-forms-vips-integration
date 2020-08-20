Feature: GET Review Scheduling

  @regression
  Scenario Outline: Test the GET endpoint of Review Scheduling
    Given the user has access to the Review Scheduling GET endpoint
    When the user submits a GET Review Scheduling Request for noticeType "<noticeType>" ReviewType "<reviewType>" ReviewDate "<reviewDate>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes ReviewStartDate "<reviewStartDate>" ReviewEndDate "<reviewEndDate>"
    Examples:
      | noticeType | reviewType | reviewDate                 | correlationId | reviewStartDate            | reviewEndDate              |
      | ADP        | ORAL       | 2018-05-01 00:00:00 -07:00 | test1         | 2020-08-03 00:00:00 -07:00 | 2020-08-03 00:00:00 -07:00 |