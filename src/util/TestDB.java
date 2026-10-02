import java.sql.Connection;
import util.DBConnection;

public class TestDB {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("SUCCESS: CareerConnect database connected!");
        } else {
            System.out.println("FAILED: Could not connect to database.");
        }
    }
}