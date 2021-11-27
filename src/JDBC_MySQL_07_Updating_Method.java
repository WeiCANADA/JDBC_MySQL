import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Wei Liu
 * @version V1.0
 */
public class JDBC_MySQL_07_Updating_Method {
    public static void main(String[] args) {
         int rowAffected = update();
        System.out.println(rowAffected);

    }
    /**
     * update a new candidate
     *
     * @return the number of Affected row
     */
    public static int update(){
        //Step1: construct a SQL UPDATE statement
        String sqlUpdate = "UPDATE candidates "
                + "SET last_name = ?, first_name = ?, email = ?"
                + "WHERE id = ?";
        int rowAffected = 0;
        //Step2: open a connection to MySQL database
        //Step3: create PreparedStatement object by calling the prepareStatement() method of the Connection object
        try (Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)){

            //Step4,supply values for the placeholders one-by-one by using setYYY() method of the PreparedStatement
            // interface where YYY is the data type of the placeholder

            pstmt.setString(1, "Liu");
            pstmt.setString(2, "Wei");
            pstmt.setString(3, "wei.liu@unb.ca");
            pstmt.setInt(4, 100);
        /*  Fourth, you send the UPDATE statement with the values for the placeholders to
           MySQL by calling executeUpdate() method of the PreparedStatement interface.
           This method takes no arguments and returns the number of row affected.*/
            rowAffected =  pstmt.executeUpdate();
            //System.out.println(rowAffected);

            // reuse the prepared statement
            String lastName = "Grace";
            int id = 101;
            pstmt.setString(1, lastName);
            pstmt.setInt(2, id);

            //conn.createStatement().executeQuery("SELECT ")
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowAffected;
    }
}
