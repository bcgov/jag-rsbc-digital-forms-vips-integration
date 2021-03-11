Feature: GET Payment Status

  @dev
  Scenario Outline: Test the GET endpoint of Payment
    Given the user has access to the Payment Status GET endpoint
    When the user submits a GET Payment Request for guid "<guid>" CorrelationId "<correlationId>"
    Then the user should get a response having attributes PaymentCardType "<paymentCardType>" PaymentAmount "<paymentAmount>" ReceiptNumber "<receiptNumberTxt>" PaymentDate "<paymentDate>"
    Examples:
      | guid                                 | correlationId | paymentCardType | paymentAmount | receiptNumberTxt | paymentDate                |
      | bd3590ec-5a1f-3859-e054-00144ffbc109 | GET_IRP       | VISA            | 7777.00       | Receipt Text IRP | 2020-08-17 00:00:00 -07:00 |
      | adf714c8-8b03-65e3-e054-00144ff95450 | GET_ADP       | VISA            | 8888.00       | Receipt Text ADP | 2020-08-18 00:00:00 -07:00 |
      | adf714c8-8b04-65e3-e054-00144ff95450 | GET_UL        | VISA            | 9999.00       | Receipt Text UL  | 2020-08-19 00:00:00 -07:00 |
