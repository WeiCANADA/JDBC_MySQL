
import java.sql.*;
/**
 * @author Wei Liu
 * @version V1.0
we will test this MySQLJDBCUtil class for creating a new connection to MySQL as follows:
 */
public class JDBC_MySQL_04_MySQLJDBCUtil_Test {
    public static void main(String[] args) {
        // create a new connection from MySQLJDBCUtil
        Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
           // print out a message
        try {
            System.out.println("Connected to database\t" + conn.getCatalog() + "\tsuccessfully!");
       }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
