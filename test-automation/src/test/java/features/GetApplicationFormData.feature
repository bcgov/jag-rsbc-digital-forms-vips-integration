Feature: GET Application Form Data

  @dev
  Scenario Outline: Test the GET endpoint of Application Form Data
    Given the user has access to GET Application Form Data
    When the user submits a GET Application Form Data request with parameters GUID "<GUID>" Correlation ID "<correlationId>"
    Then the user should get a response having attributes ProhibitionNoticeNumber "<noticeNo>" NoticeType "<noticeType>" ReviewApplicationType "<applicationType>" NoticeSubject "<noticeSubject>" PresentationType "<presentationType>" ReviewRoleType "<roleType>"
    And the response body should have attributes FirstName "<firstName>" Surname "<surname>" Phone "<phone>" Fax "<fax>" Email "<email>" ManualEntry "<manualEntry>"
    Examples:
      | GUID                                 | correlationId | noticeNo | noticeType | applicationType | noticeSubject | presentationType | roleType | firstName     | surname         | phone      | fax        | email          | manualEntry |
      | ad3dce8b-edfd-653e-e054-00144ff95450 | GET_IRP       | 21900101 | IRP        | IRP             | PERS          | WRIT             | LWYR     | GET_IRP_fName | GET_IRP_surname | 7777777777 | 7777777777 | test7@test.com | Y           |
      | ad3dce8b-edfe-653e-e054-00144ff95450 | GET_ADP       | 21900501 | ADP        | ADP             | PERS          | WRIT             | LWYR     | GET_ADP_fName | GET_ADP_surname | 8888888888 | 8888888888 | test8@test.com | Y           |
      | ad3dce8b-edff-653e-e054-00144ff95450 | GET_UL        | 21900499 | UL         | UL              | PERS          | WRIT             | LWYR     | GET_UL_fName  | GET_UL_surname  | 9999999999 | 9999999999 | test9@test.com | Y           |
