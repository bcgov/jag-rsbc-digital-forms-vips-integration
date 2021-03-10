Feature: PATCH Payment

  @dev
  Scenario Outline: Test the PATCH endpoint of Payment
    Given the database has a record for NoticeNumber "<noticeNo>" and CorrelationId "<correlationId>"
    When the user submits a PATCH Payment request with parameters NoticeNumber "<noticeNo>" Correlation ID "<correlationId>"
    And  PATCH form data having PaymentAmount "<paymentAmount>" PaymentCardType "<paymentCardType>" PaymentDate "<paymentDate>" ReceiptNumberTxt "<receiptNumberTxt>"
    Then the user should get a successful response from PATCH Payment request and the record should be updated in the database
    And the Payment db record should be reverted to the original state
    Examples:
      | noticeNo | correlationId | paymentAmount | paymentCardType | paymentDate | receiptNumberTxt |
      | 21900102 | PATCH_IRP     | 4444          | VISA            | 2020-08-17  | Receipt Text IRP |
      | 00002000 | PATCH_ADP     | 5555          | VISA            | 2020-08-18  | Receipt Text ADP |
      | 21900502 | PATCH_UL      | 6666          | VISA            | 2020-08-19  | Receipt Text UL  |
