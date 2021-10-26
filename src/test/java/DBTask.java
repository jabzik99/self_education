import org.testng.annotations.Test;
import utilities.PropertiesUtil;
import utilities.SQLUtil;

public class DBTask {

    private final static String USER = PropertiesUtil.getDbProperty("sql.user");
    private final static String PASSWORD = PropertiesUtil.getDbProperty("sql.password");
    private final static String URL = PropertiesUtil.getDbProperty("sql.url");

    @Test
    public void dbFirstStep() {
        String query = "SELECT DISTINCT project.name as PROJECT, test.name as TEST, TIMESTAMPDIFF(SECOND, test.start_time, test.end_time) as MIN_TIME\n" +
                "FROM project\n" +
                "JOIN test ON project.id = test.project_id\n" +
                "ORDER BY project.name, test.name";
        SQLUtil.outputSqlQuery(URL, USER, PASSWORD, query);
    }

    @Test
    public void dbSecondStep() {
        String query = "SELECT log.test_id, project.name\n" +
                "FROM log\n" +
                "JOIN project ON project.id=log.id";
        SQLUtil.outputSqlQuery(URL, USER, PASSWORD, query);
    }

    @Test
    public void dbThirdStep() {
        String query = "SELECT  test.name as TEST, test.start_time as DATE, project.name\n" +
                "FROM test\n" +
                "JOIN project ON test.project_id = project.id\n" +
                "WHERE test.start_time >= '2015-11-07'";
        SQLUtil.outputSqlQuery(URL, USER, PASSWORD, query);
    }

    @Test
    public void dbFourthStep() {
        String query = "SELECT   COUNT(log.test_id) AS TEST_COUNT, test.browser\n" +
                "FROM log\n" +
                "JOIN test ON test.id = log.test_id\n" +
                "WHERE test.browser = 'Firefox'\n" +
                "UNION SELECT COUNT(log.test_id) AS TEST_COUNT, test.browser\n" +
                "FROM log\n" +
                "JOIN test ON test.id = log.test_id\n" +
                "WHERE test.browser = 'Chrome'";
        SQLUtil.outputSqlQuery(URL, USER, PASSWORD, query);
    }
}
