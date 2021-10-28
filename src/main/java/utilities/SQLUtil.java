package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class SQLUtil {
    public static void outputSqlQuery(String url, String user, String password, String query) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= numberOfColumns; i++) {
                sb.append(String.format("| %-150s", rsMetaData.getColumnLabel(i)));
            }
            System.out.println(sb);
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("| %-150s", rs.getString(i));
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
