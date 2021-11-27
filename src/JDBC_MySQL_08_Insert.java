import java.sql.*;

/**
 * @author Wei Liu
 * @version V1.0
 */
public class JDBC_MySQL_08_Insert {
    public static void main(String[] args)  {
        //Step1: construct a SQL UPDATE statement
        String sql = "INSERT INTO candidates(first_name,last_name,dob,phone,email) "
                + "VALUES(?,?,?,?,?)";


        //Step2: open a new connection to MySQL. You can utilized the utility class MySQLJDBCUtil
        /* create a new PreparedStatement object by calling the prepareStatement() method of the Connection object.
        You pass the INSERT statement as the first argument and an integer with value  Statement.RETURN_GENERATED_KEYS
         as the the second argument to the method. The second argument instructs JDBC to give the inserted ID back.*/
        try ( Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            String firstName = "Sitan";
            String lastName = "Liu";
            Date dob = Date.valueOf("1980-01-04");
            String phone = "5062306777";
            String email = "Sitan@ gmail.com";
        // set parameters for statement
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setDate(3, dob);
        pstmt.setString(4, phone);
        pstmt.setString(5, email);

        //call the executeUpdate() method to execute the INSERT statement.
            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // process further here
            }

            /*Finally, to get the inserted id, you call the getGeneratedKeys() method of the PreparedStatement object.
             The method returns a ResultSet . You just need to get data out of this ResultSet as follows:*/
            // get candidate id
            int candidateId = 0;
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
                candidateId = rs.getInt(1);
            System.out.println(candidateId);
        }catch (SQLException e){
                  e.printStackTrace();
        }
    }
}

