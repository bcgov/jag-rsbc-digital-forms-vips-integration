Feature: GET Prohibition Status

  @dev
  Scenario Outline: Test the GET endpoint of Query Service Controller
    Given the user has access to GET Prohibition Status endpoint
    When the user submits a Get Prohibition Status request with parameters Notice Number "<noticeNo>" Correlation ID "<correlationId>"
    Then the user should get a response having attributes NoticeType "<noticeType>" EffectiveDate "<effectiveDate>" ReviewFormSubmitted "<reviewFormSubmitted>" ReviewCreated "<reviewCreated>" OriginalCause "<originalCause>" Surname "<surname>" DriverLicenceSeized "<driverLicenceSeized>" ReceiptNumberText "<receiptNumberTxt>" ReviewStartDateTime "<reviewStartDtm>" ReviewEndDateTime "<reviewEndDtm>"
    Examples:
      | noticeNo | correlationId | noticeType | effectiveDate              | reviewFormSubmitted | reviewCreated | originalCause | surname         | driverLicenceSeized | receiptNumberTxt | reviewStartDtm             | reviewEndDtm               |
      | 21900504 | test          | UL         | 2018-10-11 00:00:00 -07:00 | Y                   | Y             | IRPINDEF      | Fox             | N                   | null             | 2020-05-01 09:30:00 -07:00 | 2020-05-01 10:00:00 -07:00 |
      | 21900101 | GET_IRP       | IRP        | 2018-10-11 00:00:00 -07:00 | Y                   | N             | IRP90FAIL     | GET_IRP_surname | N                   | null             | null                       | null                       |
      | 21900501 | GET_ADP       | ADP        | 2018-04-12 00:00:00 -07:00 | Y                   | N             | BREATHSAMP    | GET_ADP_surname | Y                   | null             | null                       | null                       |
      | 21900499 | GET_UL        | UL         | 2018-10-11 00:00:00 -07:00 | Y                   | N             | IRPINDEF      | GET_UL_surname  | N                   | null             | null                       | null                       |

  #add disclosure
  #remove effectiveDt?
  #remove reviewFormSubmitted?
  #remove reviewCreatedYn?
  #remove originalCause?
  #remove driverLicenceSeized?
