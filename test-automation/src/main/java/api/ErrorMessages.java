package api;

public class ErrorMessages
{
    public static String apiErrorMessage(int statusCode)
    {
        switch (statusCode)
        {
            case(500):
                return "Internal Server Error.";

            case(204):
                return "No Content.";

            case(401):
                return "Unauthorized Access.";

            case(403):
                return "Forbidden.";

            case(400):
                return "Bad Request.";

            default:
                return "";
        }
    }
}
