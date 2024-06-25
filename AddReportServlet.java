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

@WebServlet("/AddReportServlet")
public class AddReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if (role == null || !role.equals("doctor") || userId == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String patientIdStr = request.getParameter("patient_id");
        String reportDate = request.getParameter("report_date");
        String description = request.getParameter("description");

        if (patientIdStr == null || reportDate == null || description == null || patientIdStr.isEmpty() || reportDate.isEmpty() || description.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: All fields are required.');");
            out.println("location='AddReport.jsp';");
            out.println("</script>");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdStr);

            try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");
                 PreparedStatement pst = con.prepareStatement("INSERT INTO reports (patient_id, report_date, description, doctor_id) VALUES (?, ?, ?, ?)")) {

                Class.forName("com.mysql.jdbc.Driver");
                pst.setInt(1, patientId);
                pst.setDate(2, java.sql.Date.valueOf(reportDate));
                pst.setString(3, description);
                pst.setInt(4, userId);

                int rs = pst.executeUpdate();

                if (rs > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Report added successfully.');");
                    out.println("location='Doctor.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed to add report.');");
                    out.println("location='AddReport.jsp';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                e.printStackTrace(out);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error: " + e.getMessage() + "');");
                out.println("location='AddReport.jsp';");
                out.println("</script>");
            }
        } catch (NumberFormatException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Patient ID is not a valid number.');");
            out.println("location='AddReport.jsp';");
            out.println("</script>");
            e.printStackTrace(out);
        }
    }
}
