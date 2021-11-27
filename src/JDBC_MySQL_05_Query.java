import java.sql.*;

/**
 * @author Wei Liu
 * @version V1.0
 * we will use this MySQLJDBCUtil class for creating a new connection to MySQL as follows:
 */
public class JDBC_MySQL_05_Query {

    public static void main(String[] args) {
        // Step 1 :construct a SQL UPDATE statement
        String sqlSelect;
        //sql= "SELECT first_name, last_name, email " + "FROM candidates";
        //sqlSelect = "SELECT * FROM candidates";
       // sqlSelect = "SELECT * FROM candidates\n" + "WHERE id = 100 OR id = 101 OR id = 134;";
        sqlSelect = "select prod_name from products";
        ResultSet rs = null;
        try (Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            // query method
            // rs = query(sqlSelect);
            rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                System.out.println(/*rs.getString("id") + "\t" +
                                rs.getString("first_name") + "\t" +
                                rs.getString("last_name") + "\t" +
                                rs.getString("email"));*/
                       rs.getString(1));
            }
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static ResultSet query(String sqlSelect) {

        // Step 1 : create a new connection from MySQLJDBCUtil

        // Connection conn = DriverManager.getConnection(url, username, password);

        /*We developed a utility class named MySQLJDBCUtil that open a new connection with
         database parameters stored in a properties file.*/
       /* final Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
        ResultSet rs = null;
        
        try {
            //Step 2: create a Statement object such as Statement, PreparedStatement and CallableStatement
            final Statement stmt = conn.createStatement();
            //Step 3: Execute any valid MySQL query like sqlSelect
            rs= stmt.executeQuery(sqlSelect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //use the try-with-resources statement
        */
        ResultSet rs = null;
        try (Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sqlSelect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


}
