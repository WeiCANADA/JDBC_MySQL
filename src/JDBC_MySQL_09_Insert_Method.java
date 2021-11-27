import java.sql.*;

/**
 * @author Wei Liu
 * @version V1.0
 */
public class JDBC_MySQL_09_Insert_Method {
    public static void main(String[] args) {
// insert a new candidate
        int id = insert("Bush", "Lily", Date.valueOf("1980-01-04"),
                "bush.l@yahoo.com", "(408) 898-6666");

        System.out.printf("A new candidate with id %d has been inserted.%n", id);
    }

    /**
     * Insert a new candidate
     *
     * @param firstName String
     * @param lastName String
     * @param dob Date
     * @param email String
     * @param phone String
     * @return the id
     */
    public static int insert(String firstName, String lastName, Date dob,
                             String email, String phone) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO candidates(first_name,last_name,dob,phone,email) "
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = JDBC_MySQL_03_MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, dob);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }
}
