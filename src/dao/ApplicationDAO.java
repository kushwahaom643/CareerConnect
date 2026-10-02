package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBConnection;

public class ApplicationDAO {

    public boolean applyForJob(int userId, int jobId) {

        String sql =
                "INSERT INTO applications (user_id, job_id) VALUES (?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, jobId);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}