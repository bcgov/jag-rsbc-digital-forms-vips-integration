package stepdefs;

import base.BaseUtil;
import config.GetConfiguration;
import config.GlobalVariables;
import database.DatabaseConnection;
import database.DatabaseSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import java.io.IOException;
import java.sql.SQLException;

public class Hooks extends BaseUtil
{
    GetConfiguration configuration;
    DatabaseConnection databaseConnection;
    private BaseUtil baseUtil;

    public Hooks(BaseUtil baseUtil) throws IOException {
        configuration = new GetConfiguration();
        this.baseUtil = baseUtil;
    }

    @Before
    public void beforeScenario(){
        GlobalVariables.apiUsername = configuration.getApiUsername();
        GlobalVariables.apiPassword = configuration.getApiPassword();
        GlobalVariables.apiBaseUrl = configuration.getDigitalFormsUrl();
    }

    @Before(value = "@database")
    public void beforeAllDatabaseScenario() throws Exception {
        GlobalVariables.dbUsername = configuration.getDbUsername();
        GlobalVariables.dbPassword = configuration.getDbPassword();
        GlobalVariables.testEnvironment = configuration.getTestEnvironment();
        databaseConnection = new DatabaseConnection(GlobalVariables.testEnvironment);
        baseUtil.databaseConnection = databaseConnection;
        String connectionString = DatabaseSetup.createConnectionString(GlobalVariables.testEnvironment);
        databaseConnection.openDBConnection(connectionString,GlobalVariables.dbUsername,GlobalVariables.dbPassword);
    }

    @After
    public void afterScenario(){}

    @After(value = "@database")
    public void afterAllDatabaseScenario() throws SQLException {
        databaseConnection.closeDBConnection();
    }

    @BeforeStep
    public void beforeEachScenarioSteps(){}

    @AfterStep
    public void afterEachScenarioSteps(){}

}
