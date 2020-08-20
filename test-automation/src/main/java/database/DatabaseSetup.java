package database;

public class DatabaseSetup
{
    public static String createConnectionString(String environment) throws Exception
    {
        switch (environment.toUpperCase())
        {
            case "QA":
                return "";

            case "DEV":
                return "jdbc:oracle:thin:@devdb.bcgov:1521/deva";

            default:
                throw new Exception("Invalid environment type");
        }
    }
}
