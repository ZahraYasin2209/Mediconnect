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

@WebServlet("/ViewAllPatientRecordsServlet")
public class ViewAllPatientRecordsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");

        if (role == null || (!"admin".equals(role) && !"doctor".equals(role))) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Retrieve all patient records
            String query = "SELECT * FROM patients";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("body { background-color: #f0f8ff; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 20px; }");
            out.println("h2 { text-align: center; color: #144848; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-weight: bold; font-size: 25px; border-bottom: 2px solid #144848; padding-bottom: 10px; margin-bottom: 20px; }");
            out.println(".table { width: 100%; margin-top: 20px; border-collapse: collapse; }");
            out.println(".table th, .table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println(".table th { background-color: #144848; color: white; }");
            out.println(".table tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h2>All Patients Records</h2>");
            out.println("<table class='table table-bordered'>");
            out.println("<thead><tr><th>ID</th><th>Name</th><th>Age</th><th>Gender</th><th>Contact</th><th>Address</th><th>Email</th></tr></thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("contact") + "</td>");
                out.println("<td>" + rs.getString("address") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody></table>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
