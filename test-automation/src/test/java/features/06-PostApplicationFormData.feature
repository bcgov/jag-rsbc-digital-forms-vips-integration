Feature: POST Application Form Data

  @dev
  Scenario Outline: Test the POST endpoint of Application Form Data
    Given the database doesn't have any record for ProhibitionId "<prohibitionId>"
    When the user submits a POST Application Form Data request with parameters FormType "<formType>" NoticeNumber "<noticeNo>" Correlation ID "<correlationId>"
    And form data having EMail "<email>" Fax "<fax>" FirstName "<fName>" FormData "<formData>" ManualEntry "<entry>" NoticeSubject "<noticeSubject>" Phone "<phone>" PresentationType "<presentationType>" ReviewRoleType "<rvwRoleType>" SecondName "<sName>" Surname "<surname>"
    Then the user should get a successful response
    Examples:
      | prohibitionId | formType | noticeNo | correlationId | email          | fax        | fName          | formData     | entry | noticeSubject | phone      | presentationType | rvwRoleType | sName          | surname          |
      | 181           | IRP      | 21900103 | POST_IRP      | test1@test.com | 1111111111 | POST_IRP_fName | formData_IRP | Y     | PERS          | 1111111111 | WRIT             | LWYR        | POST_IRP_sName | POST_IRP_surname |
      | 6339          | ADP      | 00312312 | POST_ADP      | test2@test.com | 2222222222 | POST_ADP_fName | formData_ADP | Y     | PERS          | 2222222222 | WRIT             | LWYR        | POST_ADP_sName | POST_ADP_surname |
      | 5044          | UL       | 21900503 | POST_UL       | test3@test.com | 3333333333 | POST_UL_fName  | formData_UL  | Y     | PERS          | 3333333333 | WRIT             | LWYR        | POST_UL_sName  | POST_UL_surname  |
