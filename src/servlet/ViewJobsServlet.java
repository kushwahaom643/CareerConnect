package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import dao.JobDAO;
import model.Job;

@WebServlet("/jobs")
public class ViewJobsServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        JobDAO dao = new JobDAO();

        List<Job> jobs = dao.getAllJobs();

        response.setContentType("text/html");

        response.getWriter().println(
            "<!DOCTYPE html>" +
            "<html lang='en'>" +

            "<head>" +

            "<meta charset='UTF-8'>" +

            "<meta name='viewport' " +
            "content='width=device-width, initial-scale=1.0'>" +

            "<title>Find Jobs | CareerConnect</title>" +

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

                    "<a href='index.html'>Home</a>" +

                    "<a href='jobs'>Jobs</a>" +

                    "<a href='login.html'>Login</a>" +

                    "<a href='register.html' class='nav-btn'>" +
                        "Get Started" +
                    "</a>" +

                "</div>" +

            "</nav>"
        );


        // HERO SECTION

        response.getWriter().println(

            "<section class='jobs-hero'>" +

                "<div>" +

                    "<p class='section-label'>" +
                        "CAREER OPPORTUNITIES" +
                    "</p>" +

                    "<h1>" +
                        "Find your next " +
                        "<span>opportunity.</span>" +
                    "</h1>" +

                    "<p>" +
                        "Explore available jobs and take the next " +
                        "step in your career." +
                    "</p>" +

                "</div>" +

            "</section>"
        );


        // JOBS SECTION

        response.getWriter().println(

            "<main class='jobs-container'>" +

                "<div class='jobs-heading'>" +

                    "<div>" +

                        "<p class='section-label'>" +
                            "AVAILABLE JOBS" +
                        "</p>" +

                        "<h2>" +
                            "Explore Opportunities" +
                        "</h2>" +

                    "</div>" +

                    "<div class='job-count'>" +
                        jobs.size() +
                        " Jobs Available" +
                    "</div>" +

                "</div>" +

                "<div class='jobs-grid'>"
        );


        // DISPLAY EACH JOB

        for (Job job : jobs) {

            response.getWriter().println(

                "<div class='job-card'>" +

                    "<div class='job-top'>" +

                        "<div class='company-icon'>" +
                            "💼" +
                        "</div>" +

                        "<span class='job-type'>" +
                            "Full Time" +
                        "</span>" +

                    "</div>" +


                    "<h2>" +
                        job.getTitle() +
                    "</h2>" +


                    "<p class='company-name'>" +
                        job.getCompany() +
                    "</p>" +


                    "<div class='job-details'>" +

                        "<span>" +
                            "📍 " +
                            job.getLocation() +
                        "</span>" +

                        "<span>" +
                            "💰 " +
                            job.getSalary() +
                        "</span>" +

                    "</div>" +


                    "<p class='job-description'>" +
                        job.getDescription() +
                    "</p>" +


                    "<a class='apply-btn' " +
                    "href='apply.html?jobId=" +
                    job.getJobId() +
                    "'>" +

                        "Apply Now →" +

                    "</a>" +

                "</div>"
            );
        }


        // CLOSE JOBS SECTION

        response.getWriter().println(

                "</div>" +

            "</main>"
        );


        // CTA SECTION

        response.getWriter().println(

            "<section class='jobs-cta'>" +

                "<div>" +

                    "<h2>" +
                        "Looking for your next opportunity?" +
                    "</h2>" +

                    "<p>" +
                        "Create your CareerConnect account " +
                        "and start applying." +
                    "</p>" +

                "</div>" +

                "<a href='register.html'>" +
                    "Create Account →" +
                "</a>" +

            "</section>"
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
    }
}