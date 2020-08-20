Feature: PATCH Payment

  @database
  Scenario Outline: Test the PATCH endpoint of Payment
    Given the database has a record for FormType "<formType>" GUID "<guid>" CorrelationId "<correlationId>"
    When the user submits a PATCH Payment request with parameters NoticeNumber "<noticeNo>" Correlation ID "<correlationId>"
    And  PATCH form data having PaymentAmount "<paymentAmount>" PaymentCardType "<paymentCardType>" PaymentDate "<paymentDate>" ReceiptNumberTxt "<receiptNumberTxt>"
    Then the user should get a successful response from PATCH Payment request and the record should be updated in the database
    And the Payment db record should be reverted to the original state
    Examples:
      | formType | noticeNo | guid                                 | correlationId | paymentAmount | paymentCardType | paymentDate | receiptNumberTxt |
      | IRP      | 21900102 | ad3dce8b-edfa-653e-e054-00144ff95450 | PATCH_IRP     | 4444          | VISA            | 2020-08-17  | Receipt Text IRP |
      | ADP      | 00002000 | ad3dce8b-edfb-653e-e054-00144ff95450 | PATCH_ADP     | 5555          | VISA            | 2020-08-18  | Receipt Text ADP |
      | UL       | 21900502 | ad3dce8b-edfc-653e-e054-00144ff95450 | PATCH_UL      | 6666          | VISA            | 2020-08-19  | Receipt Text UL  |
