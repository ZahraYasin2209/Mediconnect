import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateReportServlet")
public class UpdateReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if (role == null || !role.equals("doctor") || userId == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String reportIdStr = request.getParameter("report_id");
        String patientIdStr = request.getParameter("patient_id");
        String reportDate = request.getParameter("report_date");
        String description = request.getParameter("description");

        if (reportIdStr == null || patientIdStr == null || reportDate == null || description == null || 
            reportIdStr.isEmpty() || patientIdStr.isEmpty() || reportDate.isEmpty() || description.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: All fields are required.');");
            out.println("location='UpdateReport.jsp';");
            out.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;

        try {
            int reportId = Integer.parseInt(reportIdStr);
            int patientId = Integer.parseInt(patientIdStr);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");

            String query = "UPDATE reports SET patient_id = ?, report_date = ?, description = ? WHERE id = ? AND doctor_id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, patientId);
            pst.setDate(2, java.sql.Date.valueOf(reportDate));
            pst.setString(3, description);
            pst.setInt(4, reportId);
            pst.setInt(5, userId);

            int rs = pst.executeUpdate();
            
            if (rs > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Report updated successfully.');");
                out.println("location='Doctor.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Failed to update report.');");
                out.println("location='UpdateReport.jsp';");
                out.println("</script>");
            }
            
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='UpdateReport.jsp';");
            out.println("</script>");
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
