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
#      | IRP      | 21900102 | PATCH_IRP     | test4@test.com | 4444444444 | PATCH_IRP_fName | formData_IRP | Y     | PERS          | 4444444444 | WRIT             | LWYR        | PATCH_IRP_sName | PATCH_IRP_surname |
#      | ADP      | 00002000 | PATCH_ADP     | test5@test.com | 5555555555 | PATCH_ADP_fName | formData_ADP | Y     | PERS          | 5555555555 | WRIT             | LWYR        | PATCH_ADP_sName | PATCH_ADP_surname |
#      | UL       | 21900502 | PATCH_UL      | test6@test.com | 6666666666 | PATCH_UL_fName  | formData_UL  | Y     | PERS          | 6666666666 | WRIT             | LWYR        | PATCH_UL_sName  | PATCH_UL_surname  |
#      | IRP      | 21900101 | GET_IRP       | test7@test.com | 7777777777 | GET_IRP_fName   | formData_IRP | Y     | PERS          | 7777777777 | WRIT             | LWYR        | GET_IRP_sName   | GET_IRP_surname   |
#      | ADP      | 21900501 | GET_ADP       | test8@test.com | 8888888888 | GET_ADP_fName   | formData_ADP | Y     | PERS          | 8888888888 | WRIT             | LWYR        | GET_ADP_sName   | GET_ADP_surname   |
#      | UL       | 21900499 | GET_UL        | test9@test.com | 9999999999 | GET_UL_fName    | formData_UL  | Y     | PERS          | 9999999999 | WRIT             | LWYR        | GET_UL_sName    | GET_UL_surname    |
