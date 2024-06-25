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

@WebServlet("/InsertPatientRecordServlet")
public class InsertPatientRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");

        if (role == null || !"patient".equals(role)) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String username = (String) session.getAttribute("username");

        Connection con = null;
        PreparedStatement userIdPst = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            int age = Integer.parseInt(ageStr);

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Retrieve user_id from users table
            String userIdQuery = "SELECT id FROM users WHERE username = ?";
            userIdPst = con.prepareStatement(userIdQuery);
            userIdPst.setString(1, username);
            rs = userIdPst.executeQuery();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                out.println("<script>alert('Error: User not found.'); window.location.href = 'InsertPatientRecord.jsp';</script>");
                return;
            }


            String query = "INSERT INTO patients(user_id, name, age, gender, contact, address, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.setString(4, gender);
            pst.setString(5, contact);
            pst.setString(6, address);
            pst.setString(7, email);
            int insertResult = pst.executeUpdate();

            if (insertResult > 0) {
                out.println("<script>alert('Record inserted successfully.'); window.location.href = 'Patient.jsp';</script>");
            } else {
                out.println("<script>alert('Failed to insert record.'); window.location.href = 'InsertPatientRecord.jsp';</script>");
            }


        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<script>alert('Error: " + e.getMessage() + "'); window.location.href = 'InsertPatientRecord.jsp';</script>");
        }finally {
            try {
                if (rs != null) rs.close();
                if (userIdPst != null) userIdPst.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
