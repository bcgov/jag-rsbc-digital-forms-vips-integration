package api;

public class ErrorMessages
{
    public static String apiErrorMessage(int statusCode)
    {
        switch (statusCode)
        {
            case(200):
                return "Internal Server Error.";

            case(204):
                return "No Content.";

            case(401):
                return "Unauthorized Access.";

            case(403):
                return "Forbidden.";

            default:
                return "";
        }
    }
}
