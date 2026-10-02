import dao.UserDAO;
import model.User;

public class TestUserDAO {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(
                "candidate@gmail.com",
                "1234"
        );

        if (user != null) {

            System.out.println("LOGIN SUCCESS!");
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());

        } else {

            System.out.println("LOGIN FAILED!");
        }
    }
}