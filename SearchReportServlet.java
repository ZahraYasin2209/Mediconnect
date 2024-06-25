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

@WebServlet("/SearchReportServlet")
public class SearchReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String reportId = request.getParameter("id");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Update query to join reports and patients tables to get the patient name
            String query = "SELECT r.*, p.name as patient_name FROM reports r JOIN patients p ON r.patient_id = p.id WHERE r.id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(reportId));
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
            out.println("<div class='container'><h2>Report Details</h2>");

            if (rs.next()) {
                out.println("<table class='table table-bordered'>");
                out.println("<thead><tr><th>Report ID</th><th>Patient Name</th><th>Patient ID</th><th>Report Date</th><th>Description</th></tr></thead>");
                out.println("<tbody><tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("patient_name") + "</td>");
                out.println("<td>" + rs.getInt("patient_id") + "</td>");
                out.println("<td>" + rs.getDate("report_date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr></tbody>");
                out.println("</table>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No report found with ID: " + reportId + "');");
                out.println("location='SearchReport.jsp';");
                out.println("</script>");
            }

            out.println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='SearchReport.jsp';");
            out.println("</script>");
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
