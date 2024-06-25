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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM users WHERE username=? AND password=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int userId = rs.getInt("id");
                String email = rs.getString("email"); // Fetch the email
                HttpSession session = request.getSession();
                session.setAttribute("role", role);
                session.setAttribute("username", username);
                session.setAttribute("userId", userId);
                session.setAttribute("email", email); // Store email in session

                if (role == null) {
                    response.sendRedirect("Login.html");
                } else {
                    switch (role) {
                        case "admin":
                            response.sendRedirect("Admin.jsp");
                            break;
                        case "doctor":
                            response.sendRedirect("Doctor.jsp");
                            break;
                        case "patient":
                            response.sendRedirect("Patient.jsp");
                            break;
                        default:
                            response.sendRedirect("Login.html");
                            break;
                    }
                }
            } 
            else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login failed. Invalid username or password.');");
                out.println("location='Login.html';");
                out.println("</script>");
            }
        } catch (Exception e) {
                e.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error: " + e.getMessage() + "');");
                out.println("location='Login.html';");
                out.println("</script>");
            } 
        finally {
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
