Feature: PATCH Application Form Data

  @dev
  Scenario Outline: Test the PATCH endpoint of Application Form Data
    Given the database has a record for GUID "<guid>" and CorrelationId "<correlationId>"
    When the user submits a PATCH Application Form Data request with parameters FormType "<formType>" GUID "<guid>" Correlation ID "<correlationId>"
    And  PATCH form data having EMail "<email>" Fax "<fax>" FirstName "<fName>" FormData "<formData>" ManualEntry "<entry>" NoticeSubject "<noticeSubject>" Phone "<phone>" PresentationType "<presentationType>" ReviewRoleType "<rvwRoleType>" SecondName "<sName>" Surname "<surname>"
    Then the user should get a successful response from PATCH request and the record should be updated in the database
    And the db record should be reverted to the original state
    Examples:
      | formType | guid                                 | correlationId | email          | fax        | fName           | formData     | entry | noticeSubject | phone      | presentationType | rvwRoleType | sName           | surname           |
      | IRP      | ad3dce8b-edfa-653e-e054-00144ff95450 | PATCH_IRP     | test4@test.com | 4444444444 | PATCH_IRP_fName | formData_IRP | Y     | PERS          | 4444444444 | WRIT             | LWYR        | PATCH_IRP_sName | PATCH_IRP_surname |
      | ADP      | ad3dce8b-edfb-653e-e054-00144ff95450 | PATCH_ADP     | test5@test.com | 5555555555 | PATCH_ADP_fName | formData_ADP | Y     | PERS          | 5555555555 | WRIT             | LWYR        | PATCH_ADP_sName | PATCH_ADP_surname |
      | UL       | ad3dce8b-edfc-653e-e054-00144ff95450 | PATCH_UL      | test6@test.com | 6666666666 | PATCH_UL_fName  | formData_UL  | Y     | PERS          | 6666666666 | WRIT             | LWYR        | PATCH_UL_sName  | PATCH_UL_surname  |
