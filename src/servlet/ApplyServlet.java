package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.ApplicationDAO;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(
                request.getParameter("userId")
        );

        int jobId = Integer.parseInt(
                request.getParameter("jobId")
        );

        ApplicationDAO dao = new ApplicationDAO();

        boolean result = dao.applyForJob(userId, jobId);

        response.setContentType("text/html");

        if (result) {

            response.getWriter().println(
                    "<h1>Application submitted successfully!</h1>"
            );

        } else {

            response.getWriter().println(
                    "<h1>Application failed.</h1>"
            );
        }
    }
}