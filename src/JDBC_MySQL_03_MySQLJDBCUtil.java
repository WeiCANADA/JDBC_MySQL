import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Wei Liu
 * @version V1.0
 *For each interaction with MySQL database, you need to create a new connection.
 * You would have the same piece of code for doing this in all places.
 * Rather than doing this, you can create a new class for handing connection creation:
 */
public class JDBC_MySQL_03_MySQLJDBCUtil {
    /**
     * Get database connection
     *
     * @return a Connection object
     */
    public static Connection getConnection() {
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
            // ...
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
