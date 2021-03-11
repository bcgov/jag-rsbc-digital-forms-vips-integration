Feature: PATCH Payment

  @dev
  Scenario Outline: Test the PATCH endpoint of Payment
    Given the database has a record for guid "<guid>" and CorrelationId "<correlationId>"
    When the user submits a PATCH Payment request with parameters guid "<guid>" Correlation ID "<correlationId>"
    And  PATCH form data having PaymentAmount "<paymentAmount>" PaymentCardType "<paymentCardType>" PaymentDate "<paymentDate>" ReceiptNumberTxt "<receiptNumberTxt>"
    Then the user should get a successful response from PATCH Payment request and the record should be updated in the database
    And the Payment db record should be reverted to the original state
    Examples:
      | guid                             | correlationId | paymentAmount | paymentCardType | paymentDate | receiptNumberTxt |
      | adf714c8-8aff-65e3-e054-00144ff95450 | PATCH_IRP     | 4444          | VISA            | 2020-08-17  | Receipt Text IRP |
      | adf714c8-8b00-65e3-e054-00144ff95450 | PATCH_ADP     | 5555          | VISA            | 2020-08-18  | Receipt Text ADP |
      | adf714c8-8b01-65e3-e054-00144ff95450 | PATCH_UL      | 6666          | VISA            | 2020-08-19  | Receipt Text UL  |
