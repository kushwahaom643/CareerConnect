package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.JobDAO;

@WebServlet("/postjob")
public class PostJobServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");

        String company = request.getParameter("company");

        String location = request.getParameter("location");

        String salary = request.getParameter("salary");

        String description = request.getParameter("description");

        JobDAO dao = new JobDAO();

        boolean result = dao.addJob(
                title,
                company,
                location,
                salary,
                description
        );

        response.setContentType("text/html");

        if (result) {

            response.getWriter().println(
                    "<h1>Job posted successfully!</h1>"
            );

        } else {

            response.getWriter().println(
                    "<h1>Failed to post job.</h1>"
            );
        }
    }
}