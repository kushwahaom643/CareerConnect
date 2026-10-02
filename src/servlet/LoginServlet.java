package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.User;
import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(email, password);

        response.setContentType("text/html");

        if (user != null) {

            response.getWriter().println("<h1>Login successful!</h1>");
            response.getWriter().println("<p>Name: " + user.getName() + "</p>");
            response.getWriter().println("<p>Role: " + user.getRole() + "</p>");

        } else {

            response.getWriter().println("<h1>Login failed!</h1>");
            response.getWriter().println("<p>Invalid email or password.</p>");
        }
    }
}