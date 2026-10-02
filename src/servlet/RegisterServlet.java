package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.User;
import dao.UserDAO;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
String name = request.getParameter("name");
String email = request.getParameter("email");
String password = request.getParameter("password");
String role = request.getParameter("role");

User user = new User(name, email, password, role);

UserDAO dao = new UserDAO();

boolean result = dao.registerUser(user);

if (result) {
    response.getWriter().println("Registration successful!");
} else {
    response.getWriter().println("Registration failed!");
}
}
}