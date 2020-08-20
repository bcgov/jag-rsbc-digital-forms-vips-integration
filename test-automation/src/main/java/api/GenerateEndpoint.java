package api;

import org.jetbrains.annotations.Nullable;

public class GenerateEndpoint
{
    private String digitalFormsBaseUrl;

    public GenerateEndpoint(String digitalFormsBaseUrl) {
        this.digitalFormsBaseUrl = digitalFormsBaseUrl;
    }

    public String ApplicationForm(Enum VerbTypes, @Nullable String formType, @Nullable String guid, @Nullable String noticeNumber, String correlationId) throws Exception
    {
        switch (VerbTypes.toString())
        {
            case "GET":
                if(guid == null) throw new Exception("GUID value not passed for GET Application Form Data");
                return digitalFormsBaseUrl + guid + "/application/" + correlationId;

            case "PATCH":
                if(formType == null) throw new Exception("Form Type value not passed for PATCH Application Form Data");
                if(guid == null) throw new Exception("GUID value not passed for PATCH Application Form Data");
                return digitalFormsBaseUrl + formType + "/" + guid + "/application/" + correlationId;

            case "POST":
                if(formType == null) throw new Exception("Form Type value not passed for POST Application Form Data");
                if(noticeNumber == null) throw new Exception("Notice Number not passed for POST Application Form Data");
                return digitalFormsBaseUrl + formType + "/" + noticeNumber + "/application/" + correlationId;

            default:
                throw new IllegalStateException("Unexpected value: " + VerbTypes);
        }

    }


    public String Payment(Enum VerbTypes, String noticeNumber, String correlationId)
    {
        switch (VerbTypes.toString())
        {
            case "GET":
                return digitalFormsBaseUrl + noticeNumber + "/payment/status/" + correlationId;

            case "PATCH":
                return digitalFormsBaseUrl + noticeNumber + "/payment/" + correlationId;

            default:
                throw new IllegalStateException("Unexpected value: " + VerbTypes);
        }

    }


    public String Query(Enum VerbTypes, String noticeNumber, String correlationId)
    {
        switch (VerbTypes.toString())
        {
            case "GET":
                return digitalFormsBaseUrl + noticeNumber + "/status/" + correlationId;

            default:
                throw new IllegalStateException("Unexpected value: " + VerbTypes);
        }

    }


    public String ReviewScheduling(Enum VerbTypes, @Nullable String noticeNumber, @Nullable String noticeType, @Nullable String reviewType, @Nullable String reviewDate, String correlationId) throws Exception
    {
        switch (VerbTypes.toString())
        {
            case "GET":
                if(noticeType == null) throw new Exception("Notice Type value not passed for GET Application Form Data");
                if(reviewType == null) throw new Exception("Review Type value not passed for GET Application Form Data");
                if(reviewDate == null) throw new Exception("Review Date value not passed for GET Application Form Data");
                return digitalFormsBaseUrl + noticeType + "/" + reviewType + "/" + reviewDate + "/review/availableTimeSlot/" + correlationId;

            case "POST":
                if(noticeNumber == null) throw new Exception("Notice Number not passed for POST Application Form Data");
                return digitalFormsBaseUrl + noticeNumber + "/review/schedule/" + correlationId;

            default:
                throw new IllegalStateException("Unexpected value: " + VerbTypes);
        }

    }

    public String Utilities(Enum VerbTypes)
    {
        switch (VerbTypes.toString())
        {
            case "GET":
                return digitalFormsBaseUrl + "api/utility/ping";

            default:
                throw new IllegalStateException("Unexpected value: " + VerbTypes);
        }

    }


}
