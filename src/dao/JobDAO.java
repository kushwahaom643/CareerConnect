package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBConnection;

public class JobDAO {

    public boolean addJob(
            String title,
            String company,
            String location,
            String salary,
            String description) {

        String sql = "INSERT INTO jobs " +
                     "(title, company, location, salary, description) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, location);
            ps.setString(4, salary);
            ps.setString(5, description);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public java.util.List<model.Job> getAllJobs() {

    java.util.List<model.Job> jobs = new java.util.ArrayList<>();

    String sql = "SELECT * FROM jobs";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            model.Job job = new model.Job();

            job.setJobId(rs.getInt("job_id"));
            job.setTitle(rs.getString("title"));
            job.setCompany(rs.getString("company"));
            job.setLocation(rs.getString("location"));
            job.setSalary(rs.getString("salary"));
            job.setDescription(rs.getString("description"));

            jobs.add(job);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return jobs;
}
}