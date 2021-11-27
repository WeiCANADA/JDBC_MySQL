import java.sql.*;

/**
 * @author Wei Liu
 * @version V1.0
 * When connecting to MySQL, anything could happens e.g., database server is not
 * available, wrong user name or password, etc. in such cases, JDBC throws a SQLException
 * . Therefore, when you create a Connection object, you should always put it inside
 * a try catch block. Also you should always close the database connection
 * once you complete interacting with database by calling close() method of
 * the Connection object.
 */
public class JDBC_MySQL_01_Connection {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/mysqljdbc";
        String user = "root";
        String password = "lw123321";

        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...
            Statement stmt = conn.createStatement();
            String sql = "SELECT first_name, last_name, email " + "FROM candidates";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}

