package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

@WebServlet("/applicants")
public class ViewApplicantsServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String sql =
                "SELECT applications.application_id, " +
                "users.name, users.email, jobs.title, " +
                "applications.status " +
                "FROM applications " +
                "JOIN users ON applications.user_id = users.user_id " +
                "JOIN jobs ON applications.job_id = jobs.job_id";

        response.setContentType("text/html");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            response.getWriter().println(

                "<!DOCTYPE html>" +
                "<html lang='en'>" +

                "<head>" +

                "<meta charset='UTF-8'>" +

                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>" +

                "<title>Applicants | CareerConnect</title>" +

                "<link rel='stylesheet' href='style.css'>" +

                "</head>" +

                "<body>"
            );


            // NAVBAR

            response.getWriter().println(

                "<nav class='navbar'>" +

                    "<div class='logo'>" +
                        "Career<span>Connect</span>" +
                    "</div>" +

                    "<div class='nav-links'>" +

                        "<a href='index.html'>" +
                            "Home" +
                        "</a>" +

                        "<a href='jobs'>" +
                            "Jobs" +
                        "</a>" +

                        "<a href='postjob.html'>" +
                            "Post Job" +
                        "</a>" +

                        "<a href='login.html'>" +
                            "Login" +
                        "</a>" +

                    "</div>" +

                "</nav>"
            );


            // HERO

            response.getWriter().println(

                "<section class='jobs-hero'>" +

                    "<div>" +

                        "<p class='section-label'>" +
                            "RECRUITER DASHBOARD" +
                        "</p>" +

                        "<h1>" +
                            "Find your next " +
                            "<span>great hire.</span>" +
                        "</h1>" +

                        "<p>" +
                            "Review candidates who have applied " +
                            "to your job opportunities." +
                        "</p>" +

                    "</div>" +

                "</section>"
            );


            // APPLICANTS

            response.getWriter().println(

                "<main class='jobs-container'>" +

                    "<div class='jobs-heading'>" +

                        "<div>" +

                            "<p class='section-label'>" +
                                "APPLICATIONS" +
                            "</p>" +

                            "<h2>" +
                                "Job Applicants" +
                            "</h2>" +

                        "</div>" +

                    "</div>" +

                    "<div class='jobs-grid'>"
            );


            boolean found = false;


            while (rs.next()) {

                found = true;


                response.getWriter().println(

                    "<div class='job-card'>" +

                        "<div class='job-top'>" +

                            "<div class='company-icon'>" +
                                "👤" +
                            "</div>" +

                            "<span class='job-type'>" +
                                rs.getString("status") +
                            "</span>" +

                        "</div>" +


                        "<h2>" +
                            rs.getString("name") +
                        "</h2>" +


                        "<p class='company-name'>" +
                            rs.getString("title") +
                        "</p>" +


                        "<div class='job-details'>" +

                            "<span>" +
                                "📧 " +
                                rs.getString("email") +
                            "</span>" +

                            "<span>" +
                                "🆔 Application ID: " +
                                rs.getInt("application_id") +
                            "</span>" +

                        "</div>" +


                        "<p class='job-description'>" +

                            "Candidate has applied for this position. " +
                            "Contact the candidate using the registered email." +

                        "</p>" +

                    "</div>"
                );
            }


            if (!found) {

                response.getWriter().println(

                    "<div class='job-card'>" +

                        "<div class='company-icon'>" +
                            "📭" +
                        "</div>" +

                        "<h2>No Applicants Yet</h2>" +

                        "<p class='job-description'>" +

                            "Candidates who apply for your jobs " +
                            "will appear here." +

                        "</p>" +

                    "</div>"
                );
            }


            response.getWriter().println(

                    "</div>" +

                "</main>"
            );


            // FOOTER

            response.getWriter().println(

                "<footer>" +

                    "<div class='footer-logo'>" +
                        "Career<span>Connect</span>" +
                    "</div>" +

                    "<p>" +
                        "Connecting candidates with opportunities." +
                    "</p>" +

                    "<p class='copyright'>" +
                        "© 2026 CareerConnect. College Project." +
                    "</p>" +

                "</footer>" +

                "</body>" +

                "</html>"
            );


            rs.close();
            ps.close();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "<h1>Error loading applicants.</h1>"
            );
        }
    }
}