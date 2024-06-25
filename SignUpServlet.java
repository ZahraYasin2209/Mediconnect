import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        
     // Validate if passwords match
        if (!password.equals(confirmPassword)) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Passwords do not match!');");
            out.println("location='SignUp.html';");
            out.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Check if email already exists
            String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
            pst = con.prepareStatement(checkEmailQuery);
            pst.setString(1, email);
            rs = pst.executeQuery();

            
            if (rs.next()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email already exists!');");
                out.println("location='SignUp.html';");
                out.println("</script>");
                return;
            }

            // Insert into users table
            String userQuery = "INSERT INTO users(username, password, role, email) VALUES(?, ?, ?, ?)";
            pst = con.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setString(4, email);
            int userRs = pst.executeUpdate();

            if (userRs > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("role", role); 
                    session.setAttribute("userId", userId); // Add userId to session
                    session.setAttribute("email", email);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sign up Successful!');");
                    out.println("location='Login.html';"); // Redirect to login or dashboard
                    out.println("</script>");
                    return;
                }
            } 
            else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Sign up failed. Invalid username or password.');");
                out.println("location='SignUp.html';");
                out.println("</script>");
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='SignUp.html';");
            out.println("</script>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
