Feature: Setup Test Data

  @setup
  Scenario Outline: Setup test data
    Given the database setup is required
    When the user setup the Application form data with attributes FormType "<formType>" NoticeNumber "<noticeNumber>" CorrelationId "<correlationId>" EMail "<email>" Fax "<fax>" FirstName "<fName>" FormData "<formData>" ManualEntry "<entry>" NoticeSubject "<noticeSubject>" Phone "<phone>" PresentationType "<presentationType>" ReviewRoleType "<rvwRoleType>" SecondName "<sName>" Surname "<surname>"
    And the user setup the Payment data with attributes PaymentAmount "<paymentAmount>" PaymentCardType "<paymentCardType>" PaymentDate "<paymentDate>" ReceiptNumberTxt "<receiptNumberTxt>"
    And the user setup the Review Timeslot data with attributes ReviewStartDateTime "<reviewStartDateTime>" ReviewEndDateTime "<reviewEndDateTime>"
    Examples:
      | formType | noticeNumber | correlationId | email          | fax        | fName           | formData     | entry | noticeSubject | phone      | presentationType | rvwRoleType | sName           | surname           | paymentAmount | paymentCardType | paymentDate | receiptNumberTxt | reviewStartDateTime | reviewEndDateTime |
      | IRP      | 21900102     | PATCH_IRP     | test4@test.com | 4444444444 | PATCH_IRP_fName | formData_IRP | Y     | PERS          | 4444444444 | WRIT             | LWYR        | PATCH_IRP_sName | PATCH_IRP_surname | 4444          | VISA            | 2020-08-17  | Receipt Text IRP | 2020-08-01          | 2020-08-02        |
      | ADP      | 00002000     | PATCH_ADP     | test5@test.com | 5555555555 | PATCH_ADP_fName | formData_ADP | Y     | PERS          | 5555555555 | WRIT             | LWYR        | PATCH_ADP_sName | PATCH_ADP_surname | 5555          | VISA            | 2020-08-18  | Receipt Text ADP | 2020-08-01          | 2020-08-02        |
      | UL       | 21900502     | PATCH_UL      | test6@test.com | 6666666666 | PATCH_UL_fName  | formData_UL  | Y     | PERS          | 6666666666 | WRIT             | LWYR        | PATCH_UL_sName  | PATCH_UL_surname  | 6666          | VISA            | 2020-08-19  | Receipt Text UL  | 2020-08-01          | 2020-08-02        |
      | IRP      | 21900101     | GET_IRP       | test7@test.com | 7777777777 | GET_IRP_fName   | formData_IRP | Y     | PERS          | 7777777777 | WRIT             | LWYR        | GET_IRP_sName   | GET_IRP_surname   | 7777          | VISA            | 2020-08-17  | Receipt Text IRP | 2020-08-01          | 2020-08-02        |
      | ADP      | 21900501     | GET_ADP       | test8@test.com | 8888888888 | GET_ADP_fName   | formData_ADP | Y     | PERS          | 8888888888 | WRIT             | LWYR        | GET_ADP_sName   | GET_ADP_surname   | 8888          | VISA            | 2020-08-18  | Receipt Text ADP | 2020-08-01          | 2020-08-02        |
      | UL       | 21900499     | GET_UL        | test9@test.com | 9999999999 | GET_UL_fName    | formData_UL  | Y     | PERS          | 9999999999 | WRIT             | LWYR        | GET_UL_sName    | GET_UL_surname    | 9999          | VISA            | 2020-08-19  | Receipt Text UL  | 2020-08-01          | 2020-08-02        |
