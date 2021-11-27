import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Wei Liu
 * @version V1.0
 * To avoid hard coding all the database parameters in the code,
 * you can use a Java properties file to store them. In case of changes,
 * you just need to change them in the properties file and you don’t have
 * to recompile the code.
 */
public class JDBC_MySQL_02_Properties {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            FileInputStream fis = new FileInputStream("src/db.properties");
            // load the properties file
            Properties properties = new Properties();
            properties.load(fis);
            // assign db parameters
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // more processing here
            //...
            System.out.println("Connected to database <" + conn.getCatalog() + "> successfully!");
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println( e.getMessage());
                }
            }
        }
    }
}
