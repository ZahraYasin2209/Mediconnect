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

@WebServlet("/ViewPatientReportsServlet")
public class ViewPatientReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Session is null. Please log in again.');");
            out.println("location='Unauthorized.jsp';");
            out.println("</script>");
            return;
        }

        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if (role == null || !"patient".equals(role) || userId == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Unauthorized access or missing userId. Please log in again.');");
            out.println("location='Unauthorized.jsp';");
            out.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Update query to join patients, users, and doctors tables
            String query = "SELECT r.id, r.report_date, r.description, d.username AS doctor_name " +
                           "FROM reports r " +
                           "JOIN patients p ON r.patient_id = p.id " +
                           "JOIN users u ON p.user_id = u.id " +
                           "JOIN users d ON r.doctor_id = d.id " +
                           "WHERE u.id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, userId);

            rs = pst.executeQuery();

            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("body { background-color: #f0f8ff; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 20px; }");
            out.println("h2 { text-align: center; color: #144848; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-weight: bolder; border-bottom: 2px solid #144848; padding-bottom: 10px; margin-bottom: 20px; }");
            out.println(".table { width: 100%; margin-top: 20px; border-collapse: collapse; }");
            out.println(".table th, .table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println(".table th { background-color: #144848; color: white; }");
            out.println(".table tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h2>My Reports</h2>");
            out.println("<table class='table table-bordered'>");
            out.println("<thead><tr><th>Report ID</th><th>Report Date</th><th>Description</th><th>Doctor Name</th></tr></thead>");
            out.println("<tbody>");

            boolean reportsFound = false;
            while (rs.next()) {
                reportsFound = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getDate("report_date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("<td>" + rs.getString("doctor_name") + "</td>");
                out.println("</tr>");
            }

            if (!reportsFound) {
                out.println("<tr><td colspan='4'>No reports found.</td></tr>");
            }

            out.println("</tbody></table>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='Patient.jsp';");
            out.println("</script>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error: " + e.getMessage() + "');");
                out.println("location='ErrorPage.jsp';");
                out.println("</script>");
            }
        }
    }
}
