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

@WebServlet("/applications")
public class ViewApplicationsServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(
                request.getParameter("userId")
        );


        String sql =
                "SELECT applications.application_id, " +
                "jobs.title, jobs.company, jobs.location, " +
                "jobs.salary, applications.status " +
                "FROM applications " +
                "JOIN jobs ON applications.job_id = jobs.job_id " +
                "WHERE applications.user_id = ?";


        response.setContentType("text/html");


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();


            // HTML START

            response.getWriter().println(

                "<!DOCTYPE html>" +
                "<html lang='en'>" +

                "<head>" +

                "<meta charset='UTF-8'>" +

                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>" +

                "<title>My Applications | CareerConnect</title>" +

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
                            "CANDIDATE DASHBOARD" +
                        "</p>" +

                        "<h1>" +
                            "Track your " +
                            "<span>applications.</span>" +
                        "</h1>" +

                        "<p>" +
                            "View the jobs you have applied for " +
                            "and check your application status." +
                        "</p>" +

                    "</div>" +

                "</section>"
            );


            // APPLICATIONS

            response.getWriter().println(

                "<main class='jobs-container'>" +

                    "<div class='jobs-heading'>" +

                        "<div>" +

                            "<p class='section-label'>" +
                                "MY APPLICATIONS" +
                            "</p>" +

                            "<h2>" +
                                "Application History" +
                            "</h2>" +

                        "</div>" +

                    "</div>" +

                    "<div class='jobs-grid'>"
            );


            boolean found = false;


            while (rs.next()) {

                found = true;


                String status =
                        rs.getString("status");


                response.getWriter().println(

                    "<div class='job-card'>" +

                        "<div class='job-top'>" +

                            "<div class='company-icon'>" +
                                "📄" +
                            "</div>" +

                            "<span class='job-type'>" +
                                status +
                            "</span>" +

                        "</div>" +


                        "<h2>" +
                            rs.getString("title") +
                        "</h2>" +


                        "<p class='company-name'>" +
                            rs.getString("company") +
                        "</p>" +


                        "<div class='job-details'>" +

                            "<span>" +
                                "📍 " +
                                rs.getString("location") +
                            "</span>" +

                            "<span>" +
                                "💰 " +
                                rs.getString("salary") +
                            "</span>" +

                        "</div>" +


                        "<p class='job-description'>" +

                            "Application ID: " +
                            rs.getInt("application_id") +

                            "<br><br>" +

                            "Application Status: " +
                            status +

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

                        "<h2>No Applications Yet</h2>" +

                        "<p class='job-description'>" +

                            "You haven't applied for any jobs yet. " +
                            "Explore available opportunities and " +
                            "start applying." +

                        "</p>" +

                        "<a class='apply-btn' href='jobs'>" +
                            "Explore Jobs →" +
                        "</a>" +

                    "</div>"
                );
            }


            // CLOSE

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
                "<h1>Error loading applications.</h1>"
            );
        }
    }
}