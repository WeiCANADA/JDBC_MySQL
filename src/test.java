import java.sql.*;

/**
 * @author Wei Liu
 * @version V1.0
 */
public class test {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/mysqljdbc";
            String user      = "root";
            String password  = "lw123321";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
          // System.out.println("Connected to database\t" + conn.getCatalog() + "\tsuccessfully!");
            Statement stmt  = conn.createStatement();
           String sql = "SELECT first_name, last_name, email " +"FROM candidates";

            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name")  + "\t" +
                        rs.getString("email"));

            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null)
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
