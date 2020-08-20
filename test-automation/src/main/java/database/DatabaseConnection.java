package database;

import java.sql.*;

public class DatabaseConnection
{

    protected Connection dbConnection;
    protected ResultSet resultSet;
    protected Statement statement;
    private String env;

    public DatabaseConnection(String environment)
    {
        this.env = environment;
    }

    public void openDBConnection(String connectionString, String username, String password) throws Exception
    {
        try
        {
            dbConnection = DriverManager.getConnection(connectionString,username,password);
        }
        catch (SQLException throwable)
        {
            throw new Exception("Failed to connect to Database");
        }
    }

    public void closeDBConnection() throws SQLException
    {
        dbConnection.close();
    }

    public int executeDeleteQuery(String sqlQuery) throws SQLException {
        statement = dbConnection.createStatement();
        int rowsDeleted = statement.executeUpdate(sqlQuery);
        return rowsDeleted;
    }

    public int executeUpdateQuery(String sqlQuery) throws SQLException {
        statement = dbConnection.createStatement();
        int rowsUpdated = statement.executeUpdate(sqlQuery);
        return rowsUpdated;
    }

    public ResultSet executeSelectQuery(String sqlQuery) throws Exception
    {
        statement = dbConnection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        if (!resultSet.isBeforeFirst()) throw new Exception("No results returned from Select Query");
        return resultSet;
    }


    public int getRowCount(String sqlQuery) throws Exception
    {
        int rowCount = 0;
        statement = dbConnection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        if (!resultSet.isBeforeFirst()) return 0;

        while (resultSet.next())
        {
            ++rowCount;
        }
        return rowCount;
    }

}
