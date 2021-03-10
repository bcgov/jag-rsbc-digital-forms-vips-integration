Feature: GET Review Scheduling

  @dev
  Scenario Outline: Test the GET endpoint of Review Scheduling for WRIT Review Type
    Given the user has access to the Review Scheduling GET endpoint
    When the user submits a GET Review Scheduling Request for noticeType "<noticeType>" ReviewType "<reviewType>" ReviewDate "<reviewDate>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes ReviewStartDate "<reviewStartDate>" ReviewEndDate "<reviewEndDate>"
    Examples:
      | noticeType | reviewType | reviewDate                 | correlationId | reviewStartDate            | reviewEndDate              |
      | IRP        | WRIT       | 2020-08-25 00:00:00 -07:00 | test          | 2020-08-25 09:30:00 -07:00 | 2020-08-25 10:00:00 -07:00 |
      | ADP        | WRIT       | 2020-08-26 00:00:00 -07:00 | test          | 2020-08-26 09:30:00 -07:00 | 2020-08-26 10:00:00 -07:00 |
      | UL         | WRIT       | 2020-08-27 00:00:00 -07:00 | test          | 2020-08-27 09:30:00 -07:00 | 2020-08-27 10:00:00 -07:00 |


  @dev
  Scenario Outline: Test the GET endpoint of Review Scheduling for WRIT Review Type and Notice type IRP and ADP
    Given the user has access to the Review Scheduling GET endpoint
    When the user submits a GET Review Scheduling Request for noticeType "<noticeType>" ReviewType "<reviewType>" ReviewDate "<reviewDate>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes Review StartDate "<sDate1>" EndDate "<eDate1>" StartDate "<sDate2>" EndDate "<eDate2>" StartDate "<sDate3>" EndDate "<eDate3>" StartDate "<sDate4>" EndDate "<eDate4>" StartDate "<sDate5>" EndDate "<eDate5>"
    Examples:
      | noticeType | reviewType | reviewDate                 | correlationId | sDate1                     | eDate1                     | sDate2                     | eDate2                     | sDate3                     | eDate3                     | sDate4                     | eDate4                     | sDate5                     | eDate5                     |
      | IRP        | ORAL       | 2020-08-25 00:00:00 -07:00 | test          | 2020-08-25 09:00:00 -07:00 | 2020-08-25 09:30:00 -07:00 | 2020-08-25 10:00:00 -07:00 | 2020-08-25 10:30:00 -07:00 | 2020-08-25 11:00:00 -07:00 | 2020-08-25 11:30:00 -07:00 | 2020-08-25 12:00:00 -07:00 | 2020-08-25 12:30:00 -07:00 | 2020-08-25 13:00:00 -07:00 | 2020-08-25 13:30:00 -07:00 |
      | ADP        | ORAL       | 2020-08-26 00:00:00 -07:00 | test          | 2020-08-26 09:00:00 -07:00 | 2020-08-26 09:30:00 -07:00 | 2020-08-26 10:00:00 -07:00 | 2020-08-26 10:30:00 -07:00 | 2020-08-26 11:00:00 -07:00 | 2020-08-26 11:30:00 -07:00 | 2020-08-26 12:00:00 -07:00 | 2020-08-26 12:30:00 -07:00 | 2020-08-26 13:00:00 -07:00 | 2020-08-26 13:30:00 -07:00 |


  @dev
  Scenario Outline: Test the GET endpoint of Review Scheduling for WRIT Review Type and Notice type UL
    Given the user has access to the Review Scheduling GET endpoint
    When the user submits a GET Review Scheduling Request for noticeType "<noticeType>" ReviewType "<reviewType>" ReviewDate "<reviewDate>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes Review StartDate "<sDate1>" EndDate "<eDate1>" StartDate "<sDate2>" EndDate "<eDate2>" StartDate "<sDate3>" EndDate "<eDate3>"
    Examples:
      | noticeType | reviewType | reviewDate                 | correlationId | sDate1                     | eDate1                     | sDate2                     | eDate2                     | sDate3                     | eDate3                     |
      | UL         | ORAL       | 2020-08-27 00:00:00 -07:00 | test          | 2020-08-27 09:00:00 -07:00 | 2020-08-27 09:30:00 -07:00 | 2020-08-27 10:00:00 -07:00 | 2020-08-27 10:30:00 -07:00 | 2020-08-27 11:00:00 -07:00 | 2020-08-27 11:30:00 -07:00 |




